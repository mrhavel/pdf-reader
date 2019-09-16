package de.somePackage;

import java.io.File;
import java.util.List;

/**
 *
 */
public class CSVWriter implements Command<File, List<BahnReise>> {

    @Override
    public List<BahnReise> result() {
        return null;
    }

    @Override
    public Command process(File data) {
        return null;
    }
}
