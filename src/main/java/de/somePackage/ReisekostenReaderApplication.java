package de.somePackage;

import de.somePackage.beans.BahnReise;
import de.somePackage.commands.CSVWriterCommand;
import de.somePackage.reader.PDFReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * I started with the intend to build a library.
 */
public class ReisekostenReaderApplication {

    public static void main(String [] args) throws IOException {
        File pathToReisekosten = new File(args[0]);
        if (!pathToReisekosten.exists()) {
            System.err.println("Path to Reisekosten is missing.");
            return;
        }

        PDFReader reader = new PDFReader();
        List<BahnReise> readReisen = reader.readDirectory(pathToReisekosten);
        CSVWriterCommand cmd = new CSVWriterCommand();
        File out = (File) cmd.process(readReisen).result();
    }
}
