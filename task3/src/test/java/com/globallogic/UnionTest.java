package com.globallogic;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class UnionTest {

    private static List<Barcelona> barcelona1List;
    private static List<Barcelona> barcelona2List;
    private static final int expectedBarcelona1Size = 4844;
    private static final int expectedBarcelona2Size = 5023;

    @BeforeClass
    public static void parse() {
        Parser barcelonaParser = new BarcelonaParser();

        barcelona1List = barcelonaParser.parseBarcelona1("Barcelona1.csv")
                .collect(toList());
        assertThat(barcelona1List).hasSize(expectedBarcelona1Size);

        barcelona2List = barcelonaParser.parseBarcelona2("barcelona2.csv")
                .peek(barcelona2 -> {
                    String[] splitUrl = barcelona2.getListing_url().split("/");
                    Objects.requireNonNull(splitUrl[splitUrl.length - 1]);
                    barcelona2.setId(splitUrl[splitUrl.length - 1]);
                })
                .collect(toList());
        assertThat(barcelona2List).hasSize(expectedBarcelona2Size);
    }

    @Test
    public void unionAll() {
        List<Barcelona> unionAll = Stream.concat(barcelona1List.stream(), barcelona2List.stream())
                .collect(toList());
        assertThat(unionAll).hasSize(expectedBarcelona1Size + expectedBarcelona2Size);
    }

    @Test
    public void union() {
        int expectedUnionSize = 8485;

        Set<Barcelona> union = Stream.concat(
                barcelona1List.stream(),
                barcelona2List.stream())
                .collect(toSet());

        assertThat(union).hasSize(expectedUnionSize);
    }

}
