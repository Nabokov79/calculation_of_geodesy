package com.example.geodesy.methods;

import com.example.geodesy.model.ObjectsByValues;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MethodsForInitialValues extends InitialValuesMethods {


    public void setInputValues(Row row, ObjectsByValues objects) {
        objects.setNumber(getValueCellByType(row.getCell(0)));
        objects.setPoints(getValueCellByType(row.getCell(1)));
        objects.setControlPoint(getValueCellByType(row.getCell(2)));
        objects.setTransition(getValueCellByType(row.getCell(3)));
        objects.setOldDeviation(getValueCellByType(row.getCell(4)));
    }

    public void setFlag(Row row, ObjectsByValues objects) {
        if (row.getRowNum() > 6) {
            if (row.getCell(4) == null || row.getCell(4).getCellType() == CellType.BLANK) {
                if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                    objects.setFlag(4);
                } else {
                    if (row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK) {
                        objects.setFlag(5);
                    } else {
                        objects.setFlag(2);
                    }
                }
            } else {
                if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                    objects.setFlag(3);
                } else {
                    objects.setFlag(1);
                }
            }
        }
    }

    public String processCell(Cell cell) {
        String result = "";
        switch (cell.getCellType()) {
            case STRING:
                result = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getLocalDateTimeCellValue().toString();
                } else {
                    result = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;
            default:
                break;
        }
        return result;
    }


    private Integer getValueCellByType(Cell cell) {
        String value = processCell(cell);
        if (value.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }
     public Workbook loadWorkbook(File path) {
        var extension = path.getName().substring(path.getName().lastIndexOf(".") + 1).toLowerCase();
        try (BufferedInputStream file = new BufferedInputStream(new FileInputStream(path))) {
            switch (extension) {
                case "xls":
                    return new HSSFWorkbook(file);
                case "xlsx":
                    return new XSSFWorkbook(file);
                default:
                    throw new RuntimeException("Неизвестный файл Excel: " + extension);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}