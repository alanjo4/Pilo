package com.pilo.core;

public interface Utils {
    public interface RangeUtils {
        <T extends Comparable<T>, U extends Comparable<U>> boolean isInRange(T value, U lowerLimit, U higherLimit);
    }
}
