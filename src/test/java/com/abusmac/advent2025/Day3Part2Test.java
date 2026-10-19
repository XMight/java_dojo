package com.abusmac.advent2025;

import com.abusmac.io.InputOutputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Day3Part2Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day3Part2Test.class);

    @Test
    void testDay3Part2ExampleData() {
        String input = "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111";

        Day3Part2 day3Part2 = new Day3Part2(input);
        Long result = day3Part2.call();
        LOGGER.info("Day3Part2 example data result: {}", result);
        Assertions.assertEquals(3121910778619L, result);
    }

    @Test
    void testDay3Part2() throws IOException {
        String fileData = InputOutputUtils.readResourceFileData("day3/day3input.txt");

        Day3Part2 day3Part2 = new Day3Part2(fileData, true);
        Long result = day3Part2.call();

        LOGGER.info("Day3Part2 result: {}", result);
    }
}
