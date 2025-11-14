package algorytmy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static algorytmy.FirstNonDuplicate.NOT_FOUND;

class FirstNonDuplicateTest {

    @ParameterizedTest
    @CsvSource({
            "''," + NOT_FOUND,
            "'abcdefacdef', b",
            "'Aabcdefcdef', A",
            "'abcdebcadef', f",
            "'bbb'," + NOT_FOUND
    })
    void findFirstNonDuplicate(String letters, String expectedResult) {
        //when
        String firstNonDuplicate = FirstNonDuplicate.findFirstNonDuplicate(letters);

        //then
        Assertions.assertEquals(expectedResult, firstNonDuplicate, "A wrong result!");
    }
}