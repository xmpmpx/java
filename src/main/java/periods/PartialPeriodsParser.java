package periods;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.base.AbstractInterval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartialPeriodsParser {

    public static final String INTERVAL_PATTERN = "dd.MM.yyyy HH:mm:ss";
    public static final DateTimeFormatter INTERVAL_FORMATTER = DateTimeFormat.forPattern(INTERVAL_PATTERN);
    public static final String COMMA = ";";
    public static final String DASH = "-";

    public List<Interval> publish(List<Interval> existingIntervals, Interval newInterval) {
        List<Interval> resultIntervals = new ArrayList<>();
        for (Interval existingInterval : existingIntervals) {
            if (newInterval.overlaps(existingInterval)) {
                Interval overlap = newInterval.overlap(existingInterval);
                if (overlap.getStart().isAfter(existingInterval.getStart())) {
                    resultIntervals.add(new Interval(existingInterval.getStart(), overlap.getStart().minusSeconds(1)));
                }
                if (overlap.getEnd().isBefore(existingInterval.getEnd())) {
                    resultIntervals.add(new Interval(overlap.getEnd().plusSeconds(1), existingInterval.getEnd()));
                }
            } else {
                resultIntervals.add(existingInterval);
            }
        }
        resultIntervals.sort(Comparator.comparing(AbstractInterval::getStart));
        return resultIntervals;
    }

    public List<Interval> unpublish(List<Interval> existingIntervals, Interval newInterval) {
        Predicate<Interval> overlappingIntervalsPredicate = existingInterval -> existingInterval.overlaps(newInterval) || existingInterval.abuts(newInterval);
        List<Interval> overlappingIntervals = filerIntervals(existingIntervals, overlappingIntervalsPredicate);
        Interval overlappingIntervalsMerged = mergeIntervals(overlappingIntervals, newInterval);
        List<Interval> notOverlappingIntervals = filerIntervals(existingIntervals, overlappingIntervalsPredicate.negate());
        notOverlappingIntervals.add(overlappingIntervalsMerged);
        notOverlappingIntervals.sort(Comparator.comparing(AbstractInterval::getStart));
        return notOverlappingIntervals;
    }

    private Interval mergeIntervals(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);

        DateTime earliestStart = intervals.get(0).getStart();
        DateTime latestEnd = intervals.get(0).getEnd();

        for (Interval interval : intervals) {
            if (interval.getStart().isBefore(earliestStart)) {
                earliestStart = interval.getStart();
            }

            if (interval.getEnd().isAfter(latestEnd)) {
                latestEnd = interval.getEnd();
            }
        }
        return new Interval(earliestStart, latestEnd);
    }

    private List<Interval> filerIntervals(List<Interval> existingIntervals, Predicate<Interval> predicate) {
        return existingIntervals.stream().filter(predicate).collect(Collectors.toList());
    }

    private String formatIntervals(List<Interval> mergedIntervals) {
        return mergedIntervals.stream().map(interval -> formatInterval(interval) + COMMA).collect(Collectors.joining());
    }

    private String formatInterval(Interval interval) {
        return interval.getStart().toString(INTERVAL_PATTERN) + DASH + interval.getEnd().toString(INTERVAL_PATTERN);
    }

    private List<Interval> parseIntervals(String intervals) {
        return Arrays.stream(intervals.split(COMMA)).map(this::parseInterval).collect(Collectors.toList());
    }

    private Interval parseInterval(String intervalString) {
        String[] parts = intervalString.split(DASH);
        return new Interval(DateTime.parse(parts[0], INTERVAL_FORMATTER), DateTime.parse(parts[1], INTERVAL_FORMATTER));
    }
}
