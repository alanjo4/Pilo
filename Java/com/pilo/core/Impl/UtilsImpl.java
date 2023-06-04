package com.pilo.core.Impl;

import com.pilo.core.Utils;

public class UtilsImpl implements Utils {
    public static <T extends Comparable<T>, U extends Comparable<U>> boolean isInRange(T value, U lowerLimit, U higherLimit) {
        assert lowerLimit.compareTo(higherLimit) <= 0;

        if (value instanceof Number && lowerLimit instanceof Number && higherLimit instanceof Number) {
            long valueLong = ((Number) value).longValue();
            long lowerLimitLong = ((Number) lowerLimit).longValue();
            long higherLimitLong = ((Number) higherLimit).longValue();
            return (valueLong - lowerLimitLong) <= (higherLimitLong - lowerLimitLong);
        } else {
            throw new IllegalArgumentException("Unsupported types for comparison");
        }
    }
}
