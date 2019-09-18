package de.somePackage.commands;

import de.somePackage.beans.BahnReise;

import java.io.*;
import java.util.List;

/**
 * Just write all Travel Beans into a csv-File.
 */
public class CSVWriterCommand implements Command<List<BahnReise>, File> {

    private File csvOutFIle;

    @Override
    public File result() {
        return csvOutFIle;
    }

    @Override
    public Command process(List<BahnReise> data) {
        csvOutFIle = new File("C:/temp/out.csv");
        try {
            if (!csvOutFIle.createNewFile()) {
                System.out.println("Not able to create " + csvOutFIle.getName());
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Writer fileWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvOutFIle)))) {
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
