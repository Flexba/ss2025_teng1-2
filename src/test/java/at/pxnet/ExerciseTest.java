package at.pxnet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    @CsvSource({
            "1, 2, 3",
            "1, 0, 1",
            "0, 0, 0",
            "-1, 2, 1",
            "-7, -3, -10"
    })
    @ParameterizedTest
    void add(int a, int b, int expected) {
        assertEquals(expected, Exercise.add(a, b));
    }

    @CsvSource({
            "0, The temperature is 32.0 F",
            "-17.78, The temperature is 0.0 F",
            "21, The temperature is 69.8 F",
    })
    @ParameterizedTest
    void printTemperatureFahrenheit(float input, String expected) {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Exercise.printTemperatureFahrenheit(input);

        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @MethodSource("maxNumberSource")
    @ParameterizedTest
    void maxNumber(int[] numbers, int expected) {
        assertEquals(expected, Exercise.maxNumber(numbers));
    }

    public static Stream<Arguments> maxNumberSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 3),
                Arguments.of(new int[]{-1, 0, 1}, 1),
                Arguments.of(new int[]{1}, 1),
                Arguments.of(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, Integer.MAX_VALUE),
                Arguments.of(new int[]{-1, -2, -3}, -1),
                Arguments.of(new int[]{0}, 0)
        );
    }

    @CsvSource({
            "4, 2, +, 6",
            "-2, -1, +, -3",
            "3, 0, +, 3",

            "4, 2, -, 2",
            "-2, -1, -, -1",
            "3, 0, -, 3",

            "4, 2, *, 8",
            "-2, -1, *, 2",
            "3, 0, *, 0",

            "4, 2, /, 2",
            "-2, -1, /, 2",
            "3, 0, /, -1"
    })
    @ParameterizedTest
    void calculate(int a, int b, char operator, int expected) {
        assertEquals(expected, Exercise.calculate(a, b, operator));
    }

    @CsvSource({
            "2, true",
            "3, true",
            "4, false",
            "79, true",
            "99, false",
            "1234, false"
    })
    @ParameterizedTest
    void isPrime(int number, boolean expected) {
        assertEquals(expected, Exercise.isPrime(number));
    }
}