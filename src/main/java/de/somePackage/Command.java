package de.somePackage;

public interface Command<I,O> {

    O result();

    Command process(I data);
}
