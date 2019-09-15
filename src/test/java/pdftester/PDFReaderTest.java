package pdftester;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class PDFReaderTest {


    /**
     * Just a simple Read Test
     * @throws IOException
     */
    @Test
    public void testeRead() throws IOException, URISyntaxException {

        File testFile = new File(this.getClass().getClassLoader().getResource("test.pdf").toURI());
        PDDocument document = PDDocument.load(testFile);
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("Text:" + text);
        }
        document.close();
    }
}
