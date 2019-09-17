package de.somePackage;

import de.somePackage.commands.BahnTicketCommand;
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
public class PDFReader {

    private FileFilter onlyPDFFiles = pathname -> pathname.getName().endsWith("pdf");

    /**
     * ReadPDF ... ready pdfs
     * @param f - some PDF File
     * @return contents of the pdf file, null if encrypted
     * @throws IOException - somethings wrong with the file
     */
    public String readPDF(File f) throws IOException {
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
     * @param f
     * @return
     * @throws IOException
     */
    public List<BahnReise> readDirectory(File f) throws IOException {
        if (!f.isDirectory())
            return new LinkedList<>();

        List<BahnReise> reisen = new LinkedList<>();

        for (File pdf: f.listFiles(onlyPDFFiles)) {
            reisen.add(
                    (BahnReise) new BahnTicketCommand().process(readPDF(pdf)).result()
            );
        }

        return reisen;
    }
}
