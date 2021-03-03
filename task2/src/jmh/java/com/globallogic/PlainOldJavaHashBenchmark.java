package com.globallogic;

import com.globallogic.model.BaseEntity;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

import static com.globallogic.HashType.PLAIN_OLD_JAVA_HASH;

public class PlainOldJavaHashBenchmark extends MainBenchmark {

    @Benchmark
    public void addToContaineMaxInitialCapacityWithoutGap() {
        JsLikeContainer jsLikeContainer = new JsLikeContainer(10, MAX_CAPACITY, PLAIN_OLD_JAVA_HASH);
        for (int i = 0; i < MAX_CAPACITY; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }
    }

    @Benchmark
    public void addToContainerDefaultInitialCapacityWithoutGap() {
        JsLikeContainer jsLikeContainer = new JsLikeContainer(PLAIN_OLD_JAVA_HASH);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }
    }

    @Benchmark
    public void addToContainerDefaulInitialCapacityWithGap() {
        int gap = 100;
        JsLikeContainer jsLikeContainer = new JsLikeContainer(PLAIN_OLD_JAVA_HASH);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            int key = i * gap;
            jsLikeContainer.add(key, new BaseEntity(key));
        }
    }

}
