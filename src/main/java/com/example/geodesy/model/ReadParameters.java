package com.example.geodesy.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadParameters {

    protected static final String DIR = System.getProperty("user.dir");
    protected String directory;
    protected String fileName;
    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;
    protected Row row;
    protected int rowNum = 0;

    void createWorkbook(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public void setCellHeaderParameters(int number, String value) {
        Cell cell = row.createCell(number);
        cell.setCellStyle(setHeaderStyle());
        cell.setCellValue(value);
    }


    private XSSFCellStyle setHeaderStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }
    void setWidth() {
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 16 * 256);
    }

     protected void writeStart() {
        try (FileOutputStream out = new FileOutputStream(createFile())) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFile() {
        new File(DIR + File.separator + directory).mkdir();
        File file = new File(directory + File.separator + fileName);
        return file.toString();
    }
}