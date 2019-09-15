package de.somePackage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Just a simple PDF Reader
 */
public class PDFReader {

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
}
