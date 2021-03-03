package com.globallogic;

public interface PairContainer {

    boolean add(int key, Object value);

    Object get(int key);

    Object remove(int key);

}
