package com.abusmac.advent2025;

import com.abusmac.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Day3 extends BaseDay {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day3.class);

    private final String input;
    private final boolean enableLogs;

    public Day3(String input, boolean enableLogs) {
        this.input = input;
        this.enableLogs = enableLogs;
    }

    public Day3(String input) {
        this(input, false);
    }

    @Override
    public Long call() {
        String[] lines = input.split("\n");
        long result = 0;

        for (String line : lines) {
            result += getBatteryJoltage(line);

            if(enableLogs) {
                LOGGER.info("TOTAL: {}", result);
            }
        }

        return result;
    }

    protected long getBatteryJoltage(String line) {
        List<String> digits = StringUtils.splitIntoSameLengthNumbers(line, line.length(), 1);
        ArrayList<Integer> dgts = digits.stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

        int lastIndex = line.length() - 1;

        int leftDigit = dgts.get(lastIndex - 1);
        int rightDigit = dgts.get(lastIndex);
        int leftDigitIndex = lastIndex - 1;
        int rightDigitIndex = lastIndex;

        for (int i = lastIndex - 1; i >= 0; i--) {
            int dgtLeft = dgts.get(i);
            int dgtRight = dgts.get(i + 1);

            if (dgtLeft >= leftDigit) {
                leftDigit = dgtLeft;
                leftDigitIndex = i;
            }

            if (dgtRight > rightDigit) {
                if (i + 1 > leftDigitIndex) {
                    rightDigit = dgtRight;
                }

                if(dgtRight > dgts.get(rightDigitIndex)) {
                    rightDigitIndex = i + 1;
                }
            }

            if (i + 1 > leftDigitIndex) {
                if (dgts.get(rightDigitIndex) > rightDigit) {
                    rightDigit = dgts.get(rightDigitIndex);
                }
            }
        }

        if (rightDigitIndex > leftDigitIndex) {
            rightDigit = dgts.get(rightDigitIndex);
        }

        if(enableLogs) {
            LOGGER.info("Battery Joltage: {}{} LDI: {};", leftDigit, rightDigit,leftDigitIndex);
            LOGGER.info("{}", line);

            boolean leftDigitAppended = false;
            boolean rightDigitAppended = false;

            StringBuilder pointerMessage = new StringBuilder();

            for(int i = 0; i < line.length(); i++) {
                if(dgts.get(i) > leftDigit && dgts.get(i) > rightDigit) {
                    pointerMessage.append("!");
                } else if(leftDigit == dgts.get(i) && i != leftDigitIndex && dgts.get(i) != rightDigit) {
                    pointerMessage.append("x");
                } else if(leftDigitIndex == i) {
                    pointerMessage.append("^");
                    leftDigitAppended = true;
                } else if(leftDigitAppended && !rightDigitAppended && dgts.get(i) == rightDigit) {
                    pointerMessage.append("^");
                    rightDigitAppended = true;
                } else {
                    if(dgts.get(i) == leftDigit) {
                        pointerMessage.append("x");
                    } else if(dgts.get(i) == rightDigit) {
                        pointerMessage.append("y");
                    } else {
                        pointerMessage.append(" ");
                    }
                }
            }

            LOGGER.info(pointerMessage.toString());
        }

        return Integer.valueOf(leftDigit + String.valueOf(rightDigit));
    }

    protected static Callable<? extends Number> getCallable(String input, boolean enableLogs) {
        return new Day3(input, enableLogs);
    }
}
