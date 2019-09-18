package de.somePackage.commands;

/**
 *
 * @param <I> - Input
 * @param <O> - Output
 */
public interface Command<I,O> {

    /**
     *
     * @return
     */
    O result();

    /**
     * Process to get a result
     * @param data
     * @return
     */
    Command process(I data);
}
