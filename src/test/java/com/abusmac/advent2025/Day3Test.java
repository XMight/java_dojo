package com.abusmac.advent2025;

import com.abusmac.io.InputOutputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class Day3Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day3Test.class);

    @Test
    void testDay3() throws IOException {
        String fileData = InputOutputUtils.readResourceFileData("day3/day3input.txt");

        Day3 day3 = new Day3(fileData, true);
        Long result = day3.call();

        LOGGER.info("Day3 result: {}", result);

        Assertions.assertEquals(17321L, result);
    }

    @Test
    void testDay3WithProfiling() throws Exception {
        String fileData = InputOutputUtils.readResourceFileData("day3/day3input.txt");

        Day3.runDay(fileData, 100);
    }

    @Test
    void testDay3Demo() throws IOException {
        String input = "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111";

        Day3 day3 = new Day3(input);
        Long result = day3.call();
        LOGGER.info("Day3 result: {}", result);
        Assertions.assertEquals(357L, result);
    }

    @Test
    void testDay3_1() throws IOException {
        String input = "3465793544554539453556366463344563446545344434374421565553674754454364545353445746344674866324626454\n" +
                "5245519416772346223526354314328255235753314533349425533231263525232272412553451431424512533492434633\n" +
                "2323322222622242333342233232235654332273233736622333323428122236431322752232333222323322273723224333\n" +
                "1225213326242322232212132222222321425122331122112255265222222121221526223422423425122233123222221212\n" +
                "4242212334524244436421494224465443243224434456244522212434385243222434423642226222516446522642152253\n" +
                "2232939252252513212222222265225322265564434312664293123522432122262244262623631922727278656241128723\n" +
                "1442163245413423443433323444243222414433441334344343135154445122433232424441232441323223434332233442\n" +
                "6433215372332433633313333433333223523634263352344644632333532422332372533532335333332199342333233341\n" +
                "3567366666636726574553542144373545521474247931628685351338363327746637433272661177242733856754264235\n" +
                "2442436623324232261364445231242421233527222321233322445344622414623546212422342214282243222222343522\n" +
                "3544455525356348342232423332243433444345445345534136345343333753534933142353443323336251434353334442\n" +
                "3433642721442432262322833122223434443244241444432243124524242421124462323442541343442214444122249324\n" +
                "2311434441414442211431241413342114324444211442113414141232222341411442321114441343213223214432156789\n" +
                "141119\n" +
                "98\n" +
                "12221114";

        Day3 day3 = new Day3(input, true);
        Long result = day3.call();
        LOGGER.info("Day3 result: {}", result);
        Assertions.assertEquals(285L + 66L + 98L + 99L + 65L + 99L + 98L + 85L + 96L + 94L + 89L + 49L + 98L + 24L, result);
    }
}