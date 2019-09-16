package de.somePackage;

public interface Command<T> {

    T result();

    void process(String test);
}
