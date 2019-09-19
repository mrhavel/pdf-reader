package de.somePackage.commands;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {


    public Command createExcelFileCommand(File outfile) {
        Map<String, Object> config = new HashMap<>();
        config.put("outFile", new File("C:/Temp/out.xls"));
        return new ExcelWriterCommand().config(config);
    }

    public Command createCSVFileCommand(File outfile) {
        Map<String, Object> config = new HashMap<>();
        config.put("outFile", "C:/Temp/out.csv");
        return new CSVWriterCommand().config(config);
    }

    public Command createBahnTicketCommand() {
        return new BahnTicketCommand();
    }
}
