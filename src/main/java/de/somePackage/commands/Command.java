package de.somePackage.commands;

import java.util.Map;

/**
 *
 * @param <I> - Input
 * @param <O> - Output
 */
public abstract class Command<I, O> {

    protected Map<String, Object> config;

    public void config(Map<String, Object> config) {
        this.config = config;
    }

    /**
     *
     * @return
     */
    abstract O result();

    /**
     * Process to get a result
     * @param data
     * @return
     */
    abstract Command process(I data);
}
