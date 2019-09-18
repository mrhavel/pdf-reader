package de.somePackage.commands;

import de.somePackage.beans.BahnReise;

import java.io.*;
import java.util.List;

/**
 * Just write all Travel Beans into a csv-File.
 */
public class CSVWriterCommand extends Command<List<BahnReise>, File> {

    private File csvOutFile;

    @Override
    public File result() {
        return csvOutFile;
    }

    @Override
    public Command process(List<BahnReise> data) {
        csvOutFile = new File("C:/temp/out.csv");
        try {
            if (!csvOutFile.createNewFile()) {
                System.out.println("Not able to create " + csvOutFile.getAbsolutePath());
                return this;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }

        try (Writer fileWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvOutFile)))) {
            data.forEach((c) -> {
                try {
                    fileWrite.append(c.getTime()).append(",");
                    fileWrite.append(c.getBahncode()).append(",");
                    fileWrite.append(c.getReisender()).append(",");
                    fileWrite.append(c.getPreis()).append(",").append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
