package com.globallogic;

import com.globallogic.model.BaseEntity;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import static com.globallogic.HashType.MS_HASH;
import static org.assertj.core.api.Assertions.assertThat;

public class MsHashObjectTest {

    private static final int MAX_CAPACITY = 1_000_000;

    @Test
    public void shouldAddToContaineMaxInitialCapacityWithoutGap() {

        JsLikeContainer jsLikeContainer = new JsLikeContainer(10, MAX_CAPACITY, MS_HASH);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }

        assertThat(jsLikeContainer.size()).isEqualTo(MAX_CAPACITY);
    }

    /*@Test
    public void shouldAddToContainerDefaultInitialCapacityWithoutGap() {
        int expectedCapacity = MAX_CAPACITY;

        JsLikeContainer jsLikeContainer = new JsLikeContainer(MS_HASH);

        for (int i = 0; i < expectedCapacity; i++) {
            jsLikeContainer.add(i, new BaseEntity(i));
        }

        assertThat(jsLikeContainer.size()).isEqualTo(expectedCapacity);
    }*/

    @Test
    public void shouldAddToContainerDefaulInitialCapacityWithGap() {
        int gap = 100;
        int expectedCapacity = MAX_CAPACITY;

        JsLikeContainer jsLikeContainer = new JsLikeContainer(MS_HASH);

        for (int i = 0; i < expectedCapacity; i++) {
            int key = i * gap;
            jsLikeContainer.add(key, new BaseEntity(key));
        }

        assertThat(jsLikeContainer.size()).isEqualTo(expectedCapacity);

        int randomKey = RandomUtils.nextInt(0, 900_000) * gap;
        assertThat(
                ((BaseEntity) jsLikeContainer.get(randomKey)).getId()
        ).isEqualTo(randomKey);

        randomKey = RandomUtils.nextInt(0, 900_000) * gap;
        assertThat(
                ((BaseEntity) jsLikeContainer.get(randomKey)).getId()
        ).isEqualTo(randomKey);

        assertThat(
                ((BaseEntity) jsLikeContainer.get((expectedCapacity - 1) * gap)).getId()
        ).isEqualTo((expectedCapacity - 1) * gap);
    }

}
