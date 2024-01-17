package periods;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class PartialPeriodsParserUnitTest {

    @Test
    void unpublish_testSeparated() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-14T00:00:00"), dt("2024-01-15T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-14T00:00:00"), dt("2024-01-15T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testOverlapFirst() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-09T00:00:00"), dt("2024-01-15T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-15T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testOverlapSecond() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-22T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testOverlapBoth() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-09T00:00:00"), dt("2024-01-22T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testFullPeriod() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testEntirelyIncluded() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-03T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testOverlapThree() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-02T00:00:00"), dt("2024-01-28T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void unpublish_testSeparatedInThree() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.unpublish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testSeparated() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-16T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testOverlapFirst() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-12T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testOverlapSecond() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-22T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testOverlapBoth() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-22T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testEntirelyIncludedFirst() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testEntirelyIncludedSecond() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testFullPeriod() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59"));

        List<Interval> expectedResult = List.of();

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testOverlapThree() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-22T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testSeparatedInThree() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void publish_testInsideSplit() {
        PartialPeriodsParser partialPeriodsParser = new PartialPeriodsParser();

        List<Interval> existingIntervals = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        Interval newInterval = new Interval(dt("2024-01-02T00:00:00"), dt("2024-01-09T23:59:59"));

        List<Interval> expectedResult = List.of(
                new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-01T23:59:59")),
                new Interval(dt("2024-01-10T00:00:00"), dt("2024-01-10T23:59:59")),
                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")));

        List<Interval> result = partialPeriodsParser.publish(existingIntervals, newInterval);

        Assertions.assertEquals(expectedResult, result);
    }

    private DateTime dt(String dateTimeString) {
        return DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").parseDateTime(dateTimeString);
    }
}