package com.globallogic;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class MainBenchmark {

    protected static final int MAX_CAPACITY = 1_000_000;

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(MsHashBenchmark.class.getSimpleName() + ".*")
                .include(SadHashBenchmark.class.getSimpleName() + ".*")
                .include(PlainOldJavaHashBenchmark.class.getSimpleName() + ".*")
                .mode(Mode.Throughput)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(10)
                .threads(2)
                .measurementIterations(10)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(options).run();
    }

}
