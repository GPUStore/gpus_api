package ru.mephi.gpus_api;


@FunctionalInterface
public interface TwoArgConsumer<T> {

    void accept(T t, T u);
}
