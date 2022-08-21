package com.example.geodesy.model;


import org.apache.poi.ss.util.CellRangeAddress;

public class FileSample extends ReadParameters {

    public void createFileSample() {
        this.directory = "Файлы для рассчета";
        this.fileName = "Образец.xlsx";
        createWorkbook("исходные данные");
        setWidth();
        writeHeader();
        writeStart();
    }
    protected void writeHeader() {
        row = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 4));
        setCellHeaderParameters(0, "вместо отсутствующих данных ввсести - 0");
        row = sheet.createRow(++rowNum);
        setCellHeaderParameters(0, "Адресс - ");
        row = sheet.createRow(++rowNum);
        setCellHeaderParameters(0, "АБ № ");
        row = sheet.createRow(++rowNum);
        setCellHeaderParameters(0, "Введите год для определения осадки");
        row = sheet.createRow(rowNum += 2);
        setCellHeaderParameters(0, "Номер точки");
        setCellHeaderParameters(1, "Значение репера");
        setCellHeaderParameters(2, "Значение контрольной точки");
        setCellHeaderParameters(3, "Значение перехода");
        setCellHeaderParameters(4, "Значение отклонений предыдущих измерений");
    }
}