package de.somePackage.commands;

public interface Command<I,O> {

    O result();

    Command process(I data);
}
