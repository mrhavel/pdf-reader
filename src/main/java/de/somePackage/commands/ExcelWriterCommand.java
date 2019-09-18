package de.somePackage.commands;

import de.somePackage.beans.BahnReise;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriterCommand extends Command<List<BahnReise>, File> {

    private File file;

    @Override
    public File result() {
        return file;
    }

    @Override
    public Command process(List<BahnReise> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reisekosten");

        file = new File("c:/temp/out.xlsx");

        int rowCount = 0;

        for (BahnReise reise : data) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            Cell cell = row.createCell(++columnCount);
            cell.setCellValue(reise.getTime());

            cell = row.createCell(++columnCount);
            cell.setCellValue(reise.getBahncode());

            cell = row.createCell(++columnCount);
            cell.setCellValue(reise.getReisender());

            cell = row.createCell(++columnCount);
            cell.setCellValue(reise.getPreis());
        }


        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }
}
