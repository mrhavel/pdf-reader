package pdftester;

import de.somePackage.BahnReise;
import de.somePackage.BahnTicketCommand;
import de.somePackage.PDFReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PDFReaderTest {


    File testFile;

    @Before
    public void loadFile() throws URISyntaxException {
        testFile = new File(this.getClass().getClassLoader().getResource("test.pdf").toURI());
    }

    /**
     * TEST 1
     * Just a simple Read Test....just plain stackoverflow code
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


    /**
     * TEST2
     *
     * Code from Test1 now implemented as call in our Java Package.
     * Just a test that our implementation works.
     *
     * @throws IOException
     */
    @Test
    public void testeImplementartion() throws IOException {
        PDFReader reader = new PDFReader();
        Assert.assertNotNull(reader.readPDF(testFile));
    }

    /**
     * TEST 3
     *
     * Command Pattern takes place. We now want to create
     * some Command which can handle our PDF printed from Deutsche Bahn
     *
     * @throws IOException
     */
    @Test
    public void testBahn() throws IOException {
        PDFReader reader = new PDFReader();
        String text = reader.readPDF(testFile);

        BahnTicketCommand cmd = new BahnTicketCommand();
        cmd.process(text);
        BahnReise reise = cmd.result();

        Assert.assertNotNull(reise);
        Assert.assertNotNull(reise.getBahncode());
        Assert.assertNotNull(reise.getPreis());

    }

    @Test
    public void testMassenRead() throws IOException {
        PDFReader reader = new PDFReader();
        File pdfFiles = new File("C:\\Users\\mib\\Downloads\\bahn");

        List<BahnReise> reisen = reader.readDirectory(pdfFiles);

        Assert.assertTrue(!reisen.isEmpty());
    }
}
