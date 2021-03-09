package com.globallogic;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class BarcelonaParser implements Parser {

    @Override
    public Stream<Barcelona> parseBarcelona1(String fileName) {
        ColumnPositionMappingStrategy<Barcelona> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(Barcelona.class);
        mappingStrategy.setColumnMapping("id", "name", "zipcode", "smart_location", "country", "latitude", "longitude");

        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));

        CsvToBean<Barcelona> csvToBean = new CsvToBeanBuilder<Barcelona>(reader)
                .withType(Barcelona.class)
                .withMappingStrategy(mappingStrategy)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();

        return csvToBean.stream();
    }

    @Override
    public Stream<Barcelona> parseBarcelona2(String fileName) {
        RFC4180Parser rfc4180Parser = new RFC4180Parser();

        ColumnPositionMappingStrategy<Barcelona> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(Barcelona.class);
        mappingStrategy.setColumnMapping("listing_url", "name", "zipcode", "smart_location", "country", "latitude", "longitude");

        CSVReader reader = new CSVReaderBuilder(
                new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName))))
                .withCSVParser(rfc4180Parser)
                .withSkipLines(1)
                .build();

        CsvToBean<Barcelona> csvToBean = new CsvToBeanBuilder<Barcelona>(reader)
                .withMappingStrategy(mappingStrategy)
                .withIgnoreEmptyLine(true)
                .build();

        return csvToBean.stream();
    }

}
