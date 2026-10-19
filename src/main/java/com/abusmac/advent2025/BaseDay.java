package com.abusmac.advent2025;

import com.abusmac.math.MathUtils;
import com.abusmac.types.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class BaseDay implements Callable<Number> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day3.class);

    public static long runWithProfiling(Callable<? extends Number> callable) throws Exception {
        long before = System.nanoTime();

        Number result = callable.call();

        long after = System.nanoTime();

        return after - before;
    }

    public static void runDay(String input, int samplesCount) throws Exception {
        List<Long> executionDurations = new ArrayList<>();

        for(int i = 0; i < samplesCount; i++) {
            executionDurations.add(runWithProfiling(getCallable(input, false)));
        }

        Triplet<String, String, String> stats = MathUtils.calculateStats(executionDurations);
        LOGGER.info("Min: {}; Max: {}; Median: {}", stats.f, stats.s, stats.t);
    }

    protected static Callable<? extends Number> getCallable(String input, boolean enableLogs) {
        return null;
    }
}
