package com.globallogic.model;

public class BaseEntity {

    protected final int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + '}';
    }

}
