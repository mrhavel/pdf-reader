package de.somePackage;

import de.somePackage.beans.BahnReise;
import de.somePackage.commands.BahnTicketCommand;
import de.somePackage.commands.CSVWriterCommand;
import de.somePackage.commands.PDFReaderCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * I started with the intend to build a library.
 */
public class ReisekostenReaderApplication {

    public static void main(String[] args) throws IOException {
        File pathToReisekosten = new File(args[0]);
        if (!pathToReisekosten.exists()) {
            System.err.println("Path to Reisekosten is missing.");
            return;
        }

        PDFReaderCommand reader = new PDFReaderCommand();
        List<BahnReise> readReisen = new ArrayList<>();


        for (File f : pathToReisekosten.listFiles()) {
            readReisen.add(
                    (BahnReise) new BahnTicketCommand().process(
                            (String) new PDFReaderCommand().process(f).result()
                    ).result());
        }
        CSVWriterCommand cmd = new CSVWriterCommand();
        File out = (File) cmd.process(readReisen).result();
    }
}
