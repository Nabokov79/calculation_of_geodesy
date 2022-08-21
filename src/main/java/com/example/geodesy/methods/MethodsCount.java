package com.example.geodesy.methods;

import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ObjectsByValues;
import com.example.geodesy.storage.StorageInterface;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.LinkedList;

public class MethodsCount extends MethodsForInitialValues implements MethodsForCount {

    @Override
    public String processCell(Cell cell) {
        return super.processCell(cell);
    }

    @Override
    public void setInputValues(Row row, ObjectsByValues objects) {
        super.setInputValues(row, objects);
    }

    @Override
    public void setFlag(Row row, ObjectsByValues objects) {
        super.setFlag(row, objects);
    }

    @Override
    public Workbook loadWorkbook(File path) {
        return super.loadWorkbook(path);
    }

    @Override
    public LinkedList<ObjectData> createObjectsList(String key, StorageInterface repository, ObjectsByValues objects) {
        return super.createObjectsList(key, repository, objects);
    }

    @Override
    public LinkedList<ObjectData> createListObject(StorageInterface repository, ObjectsByValues objects, MethodsForCount method) {
        return super.createListObject(repository, objects, method);
    }

    @Override
    public void setMinValues(StorageInterface repository) {
        super.setMinValues(repository);
    }

    @Override
    public void setKey(String key) {
        super.setKey(key);
    }
}
