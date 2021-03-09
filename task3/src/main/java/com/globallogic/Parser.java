package com.globallogic;

import java.util.stream.Stream;

public interface Parser {

    Stream<Barcelona> parseBarcelona1(String fileName);

    Stream<Barcelona> parseBarcelona2(String fileName);

}
