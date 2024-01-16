package periods

import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.format.DateTimeFormat
import spock.lang.Specification

class PartialPeriodsParserTest extends Specification {

    def "Unpublish"() {

        given: "partialPeriodsParser"
        def partialPeriodsParser = new PartialPeriodsParser()

        when: "unpublish"
        def result = partialPeriodsParser.unpublish(existingIntervals, newInterval)

        then: "result is as expected"
        result == expectedResult

        where:
        desc               | existingIntervals                                                    | newInterval                                                        | expectedResult
        "separated"        | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-14T00:00:00"), dt("2024-01-15T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-14T00:00:00"), dt("2024-01-15T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapFirst"     | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-09T00:00:00"), dt("2024-01-15T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-15T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapSecond"    | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-22T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapBoth"      | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-09T00:00:00"), dt("2024-01-22T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59"))]

        "fullPeriod"       | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59"))]

        "entirelyIncluded" | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-03T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapThree"     | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-02T00:00:00"), dt("2024-01-28T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59"))]

        "separatedInThree" | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                              new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                              new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                                                                                                                                                                          new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
    }

    def "publish"() {

        given: "partialPeriodsParser"
        def partialPeriodsParser = new PartialPeriodsParser()

        when: "publish"
        def result = partialPeriodsParser.publish(existingIntervals, newInterval)

        then: "result is as expected"
        result == expectedResult

        where:
        desc                     | existingIntervals                                                    | newInterval                                                        | expectedResult
        "separated"              | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-16T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapFirst"           | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-12T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapSecond"          | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-15T00:00:00"), dt("2024-01-22T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59"))]
        "overlapBoth"            | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-22T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59"))]
        "entirelyIncludedFirst"  | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")) | [new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]

        "entirelyIncludedSecond" | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59"))]

        "fullPeriod"             | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-30T23:59:59")) | []

        "overlapThree"           | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-05T00:00:00"), dt("2024-01-22T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-04T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-23T00:00:00"), dt("2024-01-30T23:59:59"))]
        "separatedInThree"       | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-11T00:00:00"), dt("2024-01-11T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-12T00:00:00"), dt("2024-01-18T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
        "insideSplit"            | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-10T23:59:59")),
                                    new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))] | new Interval(dt("2024-01-02T00:00:00"), dt("2024-01-09T23:59:59")) | [new Interval(dt("2024-01-01T00:00:00"), dt("2024-01-01T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-10T00:00:00"), dt("2024-01-10T23:59:59")),
                                                                                                                                                                                new Interval(dt("2024-01-20T00:00:00"), dt("2024-01-30T23:59:59"))]
    }

    static DateTime dt(String dateTimeString) {
        return DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").parseDateTime(dateTimeString)
    }
}
