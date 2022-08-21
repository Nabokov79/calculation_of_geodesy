package com.example.geodesy.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;


import java.time.LocalDate;
import java.util.*;

public class ReadFile extends ReadParameters {

    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public void createBook(String key) {
        this.directory = "Результаты рассчетов";
        this.fileName = key + ".xlsx";;
        createWorkbook("Результат");
        setWidth();
    }

    public void setCellParameters(int number, int value) {
        Cell cell = row.createCell(number);
        cell.setCellStyle(setCellStyle());
        cell.setCellValue(value);
    }

    public void setHeaderParameters(int number, String value) {
        Cell cell = row.createCell(number);
        cell.setCellStyle(setCellStyle());
        cell.setCellValue(value);
    }

    public void writeHeaderPoint() {
        row = sheet.createRow(rowNum);
        setHeaderParameters(0, "Номер точки.");
        setHeaderParameters(1, "Значения реперов.");
        setHeaderParameters(2, "Отклонение от мин. значения за " +  LocalDate.now().getYear());
        setHeaderParameters(3, "Отклонение от мин. значения за " + date);
        setHeaderParameters(4, "Осадка.");
        ++rowNum;
    }

    public void writeHeaderControlPoint() {
        row = sheet.createRow(rowNum);
        setHeaderParameters(0, "Номер контрольной точки.");
        setHeaderParameters(1, "Значения контрольной точки.");
        setHeaderParameters(2, "Отклонение от мин. значения.");
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 4));
        setHeaderParameters(3, "Разность соседних точек.");
        setHeaderParameters(4, "");
        setHeaderParameters(5, "Разность диагональных точек.");
        ++rowNum;
    }

   private XSSFCellStyle setCellStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.index);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }


    public void writeValue(int flag, Set<ObjectData> objectList, Map<String, Integer> neighboring, Map<Integer, String> diagonal) {
        switch (flag) {
            case 1:
            case 2:
                row = sheet.createRow(rowNum);
                writeHeaderPoint();
                writePoint(objectList);
                ++rowNum;
                writeHeaderControlPoint();
                writeControlPoint(objectList,neighboring, diagonal);
                rowNum = 0;
                break;
            case 3:
            case 4:
                row = sheet.createRow(rowNum);
                writeHeaderPoint();
                writePoint(objectList);
                rowNum = 0;
                break;
            case 5:
                row = sheet.createRow(rowNum);
                writeHeaderControlPoint();
                writeControlPoint(objectList,neighboring, diagonal);
                rowNum = 0;
                break;
        }
        writeStart();
    }


    private void writePoint(Set<ObjectData> objectList) {
        for (ObjectData value : objectList) {
            row = sheet.createRow(rowNum);
            setCellParameters(0, value.getNumber());
            setCellParameters(1, getPointValue(value, value.getPoint()));
            setCellParameters(2, value.getNewDeviation());
            setCellParameters(3, value.getOldDeviation());
            setCellParameters(4, getPointValue(value, value.getPrecipitation()));
            ++rowNum;
        }
    }

    private int getPointValue(ObjectData val, int value) {
        if (val.getPoint() <= 0) {
            return 0;
        } else {
            return value;
        }
    }

    private void writeControlPoint(Set<ObjectData> objectList, Map<String, Integer> neighboring, Map<Integer, String> diagonal) {
        ObjectData val = null;
        for (ObjectData value : objectList) {
            if (value.getNumber() == 1) {
                val = value;
            }
            row = sheet.createRow(rowNum);
            setCellParameters(0, value.getNumber());
            setCellParameters(1, value.getControlPoint());
            setCellParameters(2, value.getControlPointDeviation());
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, getSellNumber(value.getNumber()), getSellNumber(value.getNumber())));
            setCellParameters(getSellNumber(value.getNumber()), getValueNeighboring(value.getNumber(),neighboring));
            setHeaderParameters(5, getValueDiagonal(value.getNumber(), diagonal));
            ++rowNum;
        }
        assert val != null;
        writeControlPointNumberOne(val);
    }


    private void writeControlPointNumberOne(ObjectData value) {
        row = sheet.createRow(rowNum);
        setCellParameters(0, value.getNumber());
        setCellParameters(1, value.getControlPoint());
        setCellParameters(2, value.getControlPointDeviation());
        setHeaderParameters(3, "");
        setHeaderParameters(4, "");
        setHeaderParameters(5, "");
    }



    private int getSellNumber(int number) {
        if ((number & 1) == 0) {
            return 4;
        } else {
            return 3;
        }
    }

    private int getValueNeighboring(int number, Map<String, Integer> neighboring) {
        for (String key : neighboring.keySet()) {
            String[] values = key.split(",");
            if (Integer.parseInt(values[0]) == number && Integer.parseInt(values[1]) - Integer.parseInt(values[0]) == 1) {
                return neighboring.get(key);
            }
            if (Integer.parseInt(values[1]) == number && Integer.parseInt(values[1]) - Integer.parseInt(values[0]) > 1) {
                row.createCell(neighboring.get(key));
                return neighboring.get(key);
            }
        }
        return 0;
    }

    private String getValueDiagonal(int number, Map<Integer, String> diagonal) {
        for (Integer key : diagonal.keySet()) {
            if (key == number) {
                String[] val = diagonal.get(key).split(",");
                return String.join(" = ", String.join(" - ", val[0], val[1]), val[2]);
            }
        }
        return "";
    }

}
