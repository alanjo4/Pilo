package com.pilo.core;

public interface BitField<T, U extends Number> {
    boolean isValid(T value);

    U encode(T value);

    U update(U previous, T value);

    U decode(U value);
}