package pdftester;

import de.somePackage.beans.BahnReise;
import de.somePackage.commands.BahnTicketCommand;
import de.somePackage.commands.CSVWriterCommand;
import de.somePackage.commands.ExcelWriterCommand;
import de.somePackage.commands.PDFReaderCommand;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
     *
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
     * <p>
     * Code from Test1 now implemented as call in our Java Package.
     * Just a test that our implementation works.
     *
     * @throws IOException
     */
    @Test
    public void testeImplementation() throws IOException {
        PDFReaderCommand reader = new PDFReaderCommand();
        Assert.assertNotNull(reader.process(testFile).result());
    }

    /**
     * TEST 3
     * <p>
     * Command Pattern takes place. We now want to create
     * some Command which can handle our PDF printed from Deutsche Bahn
     *
     * @throws IOException
     */
    @Test
    public void testBahn() throws IOException {
        PDFReaderCommand reader = new PDFReaderCommand();
        String text = (String) reader.process(testFile).result();

        BahnTicketCommand cmd = new BahnTicketCommand();
        cmd.process(text);
        BahnReise reise = cmd.result();
        reise.setOriginalPDF(testFile);


        Assert.assertNotNull(reise);
        Assert.assertNotNull(reise.getBahncode());
        Assert.assertNotNull(reise.getPreis());
        Assert.assertNotNull(reise.getOriginalPDF());

    }

    @Test
    public void testMassenRead() throws IOException {
        PDFReaderCommand reader = new PDFReaderCommand();
        File pdfFiles = new File("C:\\Users\\mib\\Downloads\\bahn");

        List<BahnReise> reisen = new ArrayList<>();

        for (File f : pdfFiles.listFiles()) {
            reisen.add(
                    (BahnReise) new BahnTicketCommand().process(
                            (String) new PDFReaderCommand().process(f).result()
                    ).result()
            );
        }


        Assert.assertTrue(!reisen.isEmpty());
    }

    @Test
    public void testCSVWriter() throws IOException {
        File pdfFiles = new File("C:\\Users\\mib\\Downloads\\bahn");

        List<BahnReise> reisen = new ArrayList<>();

        for (File f : pdfFiles.listFiles()) {
            reisen.add(
                    (BahnReise) new BahnTicketCommand().process(
                            (String) new PDFReaderCommand().process(f).result()
                    ).result()
            );
        }

        File out = (File) new CSVWriterCommand().process(reisen).result();

        Assert.assertNotNull(out);
        Assert.assertTrue(out.exists());

    }

    @Test
    public void testExcelWriter() throws IOException {
        File pdfFiles = new File("C:\\Users\\mib\\Downloads\\bahn");

        List<BahnReise> reisen = new ArrayList<>();

        for (File f : pdfFiles.listFiles()) {
            reisen.add(
                    (BahnReise) new BahnTicketCommand().process(
                            (String) new PDFReaderCommand().process(f).result()
                    ).result()
            );
        }

        File out = (File) new ExcelWriterCommand().process(reisen).result();

        Assert.assertNotNull(out);
        Assert.assertTrue(out.exists());

    }
}
