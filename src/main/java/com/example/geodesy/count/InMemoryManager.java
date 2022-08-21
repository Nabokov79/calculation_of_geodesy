package com.example.geodesy.count;

import com.example.geodesy.methods.MethodsCount;
import com.example.geodesy.methods.MethodsForCount;
import com.example.geodesy.methods.ReadToFile;
import com.example.geodesy.model.FileSample;
import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ObjectsByValues;
import com.example.geodesy.storage.Storage;
import com.example.geodesy.storage.StorageInterface;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class InMemoryManager implements CountInterface {
    private final StorageInterface repository = new Storage();
    private final FileSample file = new FileSample();
    private final ReadToFile read = new ReadToFile();

    private final MethodsForCount method = new MethodsCount();
    protected final ObjectsByValues objects = new ObjectsByValues();

    @Override
    public void readFromFile(List<File> fileList) {
        for (File path : fileList) {
            Workbook wb = method.loadWorkbook(path);
            objects.setAddress(method.processCell(wb.getSheetAt(0).getRow(1).getCell(1)));
            LinkedList<ObjectData> objectsList = new LinkedList<>();
            for (Row row : wb.getSheetAt(0)) {
                if (row.getRowNum() == 5) {
                    String[] data = method.processCell(wb.getSheetAt(0).getRow(row.getRowNum()).getCell(4)).split("-");
                    if (data.length == 2) {
                        objects.setDate(data[1]);
                    }
                }
                objects.setKey(method.processCell(wb.getSheetAt(0).getRow(1).getCell(1)) + "," +
                        method.processCell(wb.getSheetAt(0).getRow(2).getCell(0)) + "-" +
                        method.processCell(wb.getSheetAt(0).getRow(2).getCell(1)));
                objects.setDate(method.processCell(wb.getSheetAt(0).getRow(3).getCell(1)));
                method.setFlag(row, objects);
                if (row.getRowNum() > 5) {
                    method.setInputValues(row, objects);
                    objects.setNumber(Integer.parseInt(method.processCell(wb.getSheetAt(0).
                                        getRow(row.getRowNum()).getCell(0))));
                    objectsList.addLast(objects.createObjects());
                }
            }
            repository.addInputData(objects.getKey(), objectsList);
        }
    }

    @Override
    public void countValuesFromFiles() {
        for (String key : repository.getInputData().keySet()) {
            repository.addNewValues(key, method.createObjectsList(key, repository, objects));
        }
    }

    @Override
    public void countValuesFromStorage() {
        for (String key : repository.getNewValues().keySet()) {
            method.setKey(key);
            method.setMinValues(repository);
            repository.addFinalValue(key, method.createListObject(repository, objects,method));
        }
    }

    @Override
    public void readValuesToFile(CountInterface count) {
        for (String key : repository.getFinalValue().keySet()) {
            read.readToFile(key, repository);
        }
    }

    @Override
    public void createFileSample() {
        file.createFileSample();
    }
}
