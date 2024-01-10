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

public class UnpublishParser {

    public static final String INTERVAL_PATTERN = "dd.MM.yyyy HH:mm:ss";
    public static final String COMMA = ";";
    public static final String DASH = "-";

    public String publish(String existingIntervalsString, String newIntervalString) {
        List<Interval> existingIntervals = parseIntervals(existingIntervalsString);
        Interval newInterval = parseInterval(newIntervalString);

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
        return formatIntervals(resultIntervals);
    }

    public String unpublish(String existingIntervalsString, String newIntervalString) {
        List<Interval> existingIntervals = parseIntervals(existingIntervalsString);
        Interval newInterval = parseInterval(newIntervalString);
        Predicate<Interval> overlappingIntervalsPredicate = existingInterval -> existingInterval.overlaps(newInterval) || existingInterval.abuts(newInterval);
        List<Interval> overlappingIntervals = filerIntervals(existingIntervals, overlappingIntervalsPredicate);
        Interval overlappingIntervalsMerged = mergeIntervals(overlappingIntervals, newInterval);
        List<Interval> notOverlappingIntervals = filerIntervals(existingIntervals, overlappingIntervalsPredicate.negate());
        notOverlappingIntervals.add(overlappingIntervalsMerged);
        notOverlappingIntervals.sort(Comparator.comparing(AbstractInterval::getStart));
        return formatIntervals(notOverlappingIntervals);
    }

    public Interval mergeIntervals(List<Interval> intervals, Interval newInterval) {
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

    private static List<Interval> parseIntervals(String intervals) {
        String[] intervalStrings = intervals.split(COMMA);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(INTERVAL_PATTERN);
        return Arrays.stream(intervalStrings).map(intervalString -> parseInterval(intervalString, formatter)).collect(Collectors.toList());
    }

    private static Interval parseInterval(String intervalString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(INTERVAL_PATTERN);
        return parseInterval(intervalString, formatter);
    }

    private static Interval parseInterval(String intervalString, DateTimeFormatter formatter) {
        String[] parts = intervalString.split(DASH);
        DateTime start = DateTime.parse(parts[0], formatter);
        DateTime end = DateTime.parse(parts[1], formatter);
        return new Interval(start, end);
    }
}
