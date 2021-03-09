package com.globallogic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class Task3Benchmark {

    private static List<Barcelona> barcelona1List;
    private static List<Barcelona> barcelona2List;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Task3Benchmark.class.getSimpleName() + ".*")
                .mode(Mode.Throughput)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(10)
                .threads(1)
                .measurementIterations(10)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() {
        Parser barcelonaParser = new BarcelonaParser();

        barcelona1List = barcelonaParser.parseBarcelona1("Barcelona1.csv")
                .collect(toList());

        barcelona2List = barcelonaParser.parseBarcelona2("barcelona2.csv")
                .peek(barcelona2 -> {
                    String[] splitUrl = barcelona2.getListing_url().split("/");
                    Objects.requireNonNull(splitUrl[splitUrl.length - 1]);
                    barcelona2.setId(splitUrl[splitUrl.length - 1]);
                })
                .collect(toList());
    }

    @Benchmark
    public void unionAll() {
        List<Barcelona> unionAll = Stream.concat(barcelona1List.stream(), barcelona2List.stream())
                .collect(toList());
    }

    @Benchmark
    public void union() {
        Set<Barcelona> union = Stream.concat(barcelona1List.stream(), barcelona2List.stream())
                .collect(toSet());
    }

}
