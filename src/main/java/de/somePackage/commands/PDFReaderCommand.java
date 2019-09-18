package de.somePackage.commands;

import de.somePackage.beans.BahnReise;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Just a simple PDF Reader
 */
public class PDFReaderCommand extends Command<File, String> {

    private FileFilter onlyPDFFiles = pathname -> pathname.getName().endsWith("pdf");

    private String contents;

    /**
     * ReadPDF ... ready pdfs
     *
     * @param f - some PDF File
     * @return contents of the pdf file, null if encrypted
     * @throws IOException - somethings wrong with the file
     */
    private String readPDF(File f) throws IOException {
        PDDocument document = PDDocument.load(f);
        String text = null;
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document);
        }
        document.close();
        return text;
    }

    /**
     * Alle Tickets einlesen
     *
     * @param f
     * @return
     * @throws IOException
     */
    private List<BahnReise> readDirectory(File f) throws IOException {
        if (!f.isDirectory())
            return new LinkedList<>();

        List<BahnReise> reisen = new LinkedList<>();

        for (File pdf : f.listFiles(onlyPDFFiles)) {
            BahnReise reise = (BahnReise) new BahnTicketCommand().process(readPDF(pdf)).result();
            reise.setOriginalPDF(f);
            reisen.add(
                    reise
            );
        }

        return reisen;
    }

    @Override
    public String result() {
        return contents;
    }

    @Override
    public Command process(File data) {
        if (data.isDirectory()) {
            throw new NullPointerException("Nur Dateien werden eingelesen");
        }
        try {
            contents = readPDF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
