package de.somePackage;

public interface Command<T> {

    T result();

    Command process(String test);
}
