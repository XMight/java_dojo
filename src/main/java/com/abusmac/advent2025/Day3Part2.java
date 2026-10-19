package com.abusmac.advent2025;

import com.abusmac.string.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * vThe escalator doesn't move. The Elf explains that it probably needs more joltage to overcome the static friction of the system and hits the big red "joltage limit safety override" button. You lose count of the number of times she needs to confirm "yes, I'm sure" and decorate the lobby a bit while you wait.
 * Now, you need to make the largest joltage by turning on exactly twelve batteries within each bank.
 * The joltage output for the bank is still the number formed by the digits of the batteries you've turned on; the only difference is that now there will be 12 digits in each bank's joltage output instead of two.
 * Consider again the example from before:
 * 987654321111111
 * 811111111111119
 * 234234234234278
 * 818181911112111
 * Now, the joltages are much larger:
 *     In 987654321111111, the largest joltage can be found by turning on everything except some 1s at the end to produce 987654321111.
 *     In the digit sequence 811111111111119, the largest joltage can be found by turning on everything except some 1s, producing 811111111119.
 *     In 234234234234278, the largest joltage can be found by turning on everything except a 2 battery, a 3 battery, and another 2 battery near the start to produce 434234234278.
 *     In 818181911112111, the joltage 888911112111 is produced by turning on everything except some 1s near the front.
 * The total output joltage is now much larger: 987654321111 + 811111111119 + 434234234278 + 888911112111 = 3121910778619.
 * What is the new total output joltage?
 * Your puzzle answer was 171989894144198.
 */
public class Day3Part2 extends Day3 {
    private static final int JOLTAGE_LENGTH = 12;

    public Day3Part2(String input) {
        super(input);
    }

    public Day3Part2(String input, boolean enableLogs) {
        super(input, enableLogs);
    }

    protected static Callable<? extends Number> getCallable(String input, boolean enableLogs) {
        return new Day3Part2(input, enableLogs);
    }

    /**
     * Monotonic stack increase algorithm
     * @param line Input string to extract joltage from
     * @return the joltage result
     */
    @Override
    protected long getBatteryJoltage(String line) {
        long result = 0;
        List<String> digits = StringUtils.splitIntoSameLengthNumbers(line, line.length(), 1);
        ArrayList<Integer> dgts = digits.stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

        Stack<Integer> stack = new Stack<>();
        stack.push(dgts.get(0));

        int currentIndex = 1;
        int lastIndex = digits.size() - 1;
        int deletionsToPerform = dgts.size() - JOLTAGE_LENGTH;
        while (currentIndex <= lastIndex) {
            Integer digit = dgts.get(currentIndex);

            while (!stack.isEmpty() && stack.peek() < digit && deletionsToPerform > 0) {
                deletionsToPerform--;
                stack.pop();
            }

            stack.push(digit);

            currentIndex++;
        }

        String number = stack.subList(0, JOLTAGE_LENGTH).stream().map(String::valueOf).collect(Collectors.joining());
        result = Long.parseLong(number);

        return result;
    }
}
