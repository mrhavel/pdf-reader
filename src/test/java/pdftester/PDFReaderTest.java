package pdftester;

import de.somePackage.PDFReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class PDFReaderTest {


    File testFile;

    @Before
    public void loadFile() throws URISyntaxException {
        testFile = new File(this.getClass().getClassLoader().getResource("test.pdf").toURI());
    }

    /**
     * Just a simple Read Test
     * @throws IOException
     */
    @Test
    public void testeRead() throws IOException, URISyntaxException {

        PDDocument document = PDDocument.load(testFile);
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            Assert.assertNotNull(stripper.getText(document));
        }
        document.close();
    }

    @Test
    public void testeImplementartion() throws IOException {
        PDFReader reader = new PDFReader();
        Assert.assertNotNull(reader.readPDF(testFile));
    }
}
