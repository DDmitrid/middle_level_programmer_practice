package com.globallogic;

import com.globallogic.model.BaseEntity;
import org.openjdk.jmh.annotations.Benchmark;

import static com.globallogic.HashType.SAD_HASH;

public class SadHashBenchmark extends MainBenchmark {

    @Benchmark
    public void addToContaineMaxInitialCapacityWithoutGap() {
        JsLikeContainer jsLikeContainer = new JsLikeContainer(10, MAX_CAPACITY, SAD_HASH);
        for (int i = 0; i < MAX_CAPACITY; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }
    }

    @Benchmark
    public void addToContainerDefaultInitialCapacityWithoutGap() {
        JsLikeContainer jsLikeContainer = new JsLikeContainer(SAD_HASH);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }
    }

    @Benchmark
    public void addToContainerDefaulInitialCapacityWithGap() {
        int gap = 100;
        JsLikeContainer jsLikeContainer = new JsLikeContainer(SAD_HASH);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            int key = i * gap;
            jsLikeContainer.add(key, new BaseEntity(key));
        }
    }

}
