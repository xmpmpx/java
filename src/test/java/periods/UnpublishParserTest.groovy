package periods

import periods.UnpublishParser
import spock.lang.Specification

class UnpublishParserTest extends Specification {

    def "Unpublish"() {

        given: "partialPeriodsParser"
        def partialPeriodsParser = new UnpublishParser()

        when: "unpublish"
        def result = partialPeriodsParser.unpublish(existingIntervals, newInterval)

        then: "result is as expected"
        result == expectedResult

        where:
        desc               | existingIntervals                                                                                                          | newInterval                               | expectedResult
        "separated"        | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "14.01.2024 00:00:00-15.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;14.01.2024 00:00:00-15.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapFirst"     | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "09.01.2024 00:00:00-15.01.2024 23:59:59" | "01.01.2024 00:00:00-15.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapSecond"    | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "15.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;15.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapBoth"      | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "09.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-30.01.2024 23:59:59;"
        "fullPeriod"       | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "01.01.2024 00:00:00-30.01.2024 23:59:59" | "01.01.2024 00:00:00-30.01.2024 23:59:59;"
        "entirelyIncluded" | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"                                         | "01.01.2024 00:00:00-03.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapThree"     | "01.01.2024 00:00:00-10.01.2024 23:59:59;12.01.2024 00:00:00-18.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "02.01.2024 00:00:00-28.01.2024 23:59:59" | "01.01.2024 00:00:00-30.01.2024 23:59:59;"
        "separatedInThree" | "01.01.2024 00:00:00-10.01.2024 23:59:59;12.01.2024 00:00:00-18.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "11.01.2024 00:00:00-11.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;11.01.2024 00:00:00-11.01.2024 23:59:59;12.01.2024 00:00:00-18.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
    }

    def "publish"() {

        given: "partialPeriodsParser"
        def partialPeriodsParser = new UnpublishParser()

        when: "unpublish"
        def result = partialPeriodsParser.publish(existingIntervals, newInterval)

        then: "result is as expected"
        result == expectedResult

        where:
        desc            | existingIntervals                                                                  | newInterval                               | expectedResult
        "separated"     | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "15.01.2024 00:00:00-16.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapFirst"  | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "05.01.2024 00:00:00-12.01.2024 23:59:59" | "01.01.2024 00:00:00-04.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapSecond" | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "15.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;23.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapBoth"   | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "05.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-04.01.2024 23:59:59;23.01.2024 00:00:00-30.01.2024 23:59:59;"
    }
}