package pdftester;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFReaderTest {


    /**
     * Just a simple Read Test
     * @throws IOException
     */
    public void testeRead() throws IOException {
        PDDocument document = PDDocument.load(new File("test.pdf"));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("Text:" + text);
        }
        document.close();
    }
}
