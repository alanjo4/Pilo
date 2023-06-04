package com.pilo.core.Impl;

import java.lang.reflect.Modifier;

import com.pilo.core.BitField;

public final class BitFieldImpl<T, U extends Number> implements BitField<T, U> {
    private final int SHIFT;
    private final int SIZE;
    private final U MASK;
    private final U MAX;

    public BitFieldImpl(int shift, int size) {
        assert shift < 8 * Integer.SIZE;
        assert size < 8 * Integer.SIZE;
        assert shift + size <= 8 * Integer.SIZE;
        assert size > 0;

        this.SHIFT = shift;
        this.SIZE = size;
        this.MASK = computeMask(shift, size);
        this.MAX = computeMax(MASK);
    }

    private U computeMask(int shift, int size) {
        int mask = ((1 << shift) << size) - (1 << shift);
        return createNumber(mask);
    }

    private U computeMax(U mask) {
        int max = mask.intValue() >> SHIFT;
        return createNumber(max);
    }

    private U createNumber(int value) {
        Class<U> clazz = (Class<U>) Integer.class;
        try {
            return clazz.getDeclaredConstructor(int.class).newInstance(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isValid(T value) {
        return ((Number) value).intValue() >= 0 && ((Number) value).intValue() <= MAX.intValue();
    }

    public U encode(T value) {
        assert isValid(value);
        int intValue = ((Number) value).intValue() << SHIFT;
        return createNumber(intValue);
    }

    public U update(U previous, T value) {
        int intValue = ((Number) value).intValue() << SHIFT;
        int updatedValue = (previous.intValue() & ~MASK.intValue()) | intValue;
        return createNumber(updatedValue);
    }

    public U decode(U value) {
        int decodedValue = (value.intValue() & MASK.intValue()) >> SHIFT;
        return createNumber(decodedValue);
    }
}