package de.somePackage.commands;

import java.util.Map;

/**
 *
 * @param <I> - Input
 * @param <O> - Output
 */
public abstract class Command<I, O> {

    protected Map<String, Object> config = null;

    public Command config(Map<String, Object> config) {
        if (this.config == null) {
            // One Command, one secret Configuration, one Processing....
            this.config = config;
        }
        return this;
    }

    /**
     *
     * @return
     */
    public abstract O result();

    /**
     * Process to get a result
     * @param data
     * @return
     */
    public abstract Command process(I data);
}
