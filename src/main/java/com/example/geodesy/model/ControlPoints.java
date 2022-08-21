package com.example.geodesy.model;

import java.util.Objects;

public class ControlPoints {

    private final String key;
    private final int value;

    public ControlPoints(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ControlPoints{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlPoints that = (ControlPoints) o;
        return value == that.value && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
