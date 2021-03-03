package com.globallogic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsLikeContainer implements PairContainer {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_GAP = 10;
    private final HashType hashType;


    private int gap;
    private Object[] arrElementData;
    private Map<Integer, Object> hashElementData;


    public JsLikeContainer(HashType hashType) {
        this.gap = DEFAULT_GAP;
        arrElementData = new Object[DEFAULT_CAPACITY];
        this.hashType = hashType;
    }

    public JsLikeContainer(int gap, HashType hashType) {
        this.gap = gap;
        arrElementData = new Object[DEFAULT_CAPACITY];
        this.hashType = hashType;

    }

    public JsLikeContainer(int gap, int initialCapacity, HashType hashType) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity should be positive");
        }
        this.gap = gap;
        arrElementData = new Object[initialCapacity];
        this.hashType = hashType;
    }

    @Override
    public boolean add(int key, Object value) {
        if (key < arrElementData.length
                && hashElementData == null) {
            arrElementData[key] = value;
            return true;
        }

        if (key >= arrElementData.length
                && ((key - arrElementData.length) < gap)
                && hashElementData == null) {

            Object[] newArrElementData = new Object[key + DEFAULT_CAPACITY];

            System.arraycopy(arrElementData, 0, newArrElementData, 0, arrElementData.length);

            newArrElementData[key] = value;

            arrElementData = newArrElementData;
            return true;
        }


        //use hashMap
        if (hashElementData == null) {
            hashElementData = new HashMap<>();
            for (int i = 0; i < arrElementData.length; i++) {
                if (arrElementData[i] != null) {
                    int hashKey = hash(i);
                    hashElementData.put(hashKey, arrElementData[i]);
                }
            }

        }

        int hashKey = hash(key);
        hashElementData.put(hashKey, value);

        return true;
    }

    @Override
    public Object get(int key) {
        if (hashElementData == null) {
            if (key >= arrElementData.length) {
                return null;
            }
            return arrElementData[key];
        }

        int hashKey = hash(key);
        return hashElementData.get(hashKey);
    }

    @Override
    public Object remove(int key) {
        if (hashElementData == null) {
            if (key >= arrElementData.length) {
                throw new IllegalArgumentException();
            }
            Object previousValue = arrElementData[key];
            arrElementData[key] = null;
            return previousValue;
        }

        return hashElementData.remove(key);
    }

    public long size() {
        if (hashElementData == null) {
            return Arrays.stream(arrElementData)
                    .filter(Objects::nonNull)
                    .count();
        }
        return hashElementData.size();
    }

    private int hash(int key) {
        int resultHash;
        switch (hashType) {
            case MS_HASH:
                resultHash = msHash(key);
                break;
            case SAD_HASH:
                resultHash = sadHash(key);
                break;
            case PLAIN_OLD_JAVA_HASH:
                resultHash = plainOldJavaHash(key);
                break;
            default:
                throw new IllegalArgumentException("HashType should be defined.");
        }
        return resultHash;
    }

    private int sadHash(int key) {
        return key;
    }

    private int plainOldJavaHash(int key) {
        return ((key >> 24) ^ key) * 0x45d9f3b;
    }

    private int msHash(int key) {
        return 101 * ((key >> 24) + 101 * ((key >> 16) + 101 * (key >> 8))) + key;
    }

}
