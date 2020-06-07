package com.sample.test.model.function;

@FunctionalInterface
public interface TriFunction<A,B,C,R> {

    public R apply(A a, B b, C c);

}