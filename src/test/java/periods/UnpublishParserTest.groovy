package periods

import periods.UnpublishParser
import spock.lang.Specification

class UnpublishParserTest extends Specification {

    def "Unpublish"() {

        given: "periods.UnpublishParser"
        def unpublishParser = new UnpublishParser()

        when: "unpublish"
        def result = unpublishParser.unpublish(existingIntervals, newInterval)

        then: "result is as expected"
        result == expectedResult
        println "result = ${result}"

        where:
        desc            | existingIntervals                                                                  | newInterval                               | expectedResult
        "separated"     | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "14.01.2024 00:00:00-15.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;14.01.2024 00:00:00-15.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapFirst"  | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "09.01.2024 00:00:00-15.01.2024 23:59:59" | "01.01.2024 00:00:00-15.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapSecond" | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "15.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-10.01.2024 23:59:59;15.01.2024 00:00:00-30.01.2024 23:59:59;"
        "overlapBoth"   | "01.01.2024 00:00:00-10.01.2024 23:59:59;20.01.2024 00:00:00-30.01.2024 23:59:59;" | "09.01.2024 00:00:00-22.01.2024 23:59:59" | "01.01.2024 00:00:00-30.01.2024 23:59:59;"
    }
}
