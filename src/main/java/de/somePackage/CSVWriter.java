package de.somePackage;

import java.io.File;
import java.util.List;

/**
 *
 */
public class CSVWriter implements Command<List<BahnReise>, File> {

    File csvOutFIle;

    @Override
    public File result() {
        return csvOutFIle;
    }

    @Override
    public Command process(List<BahnReise> data) {
        csvOutFIle = new File("C:/temp/out.csv");
        return this;
    }
}
