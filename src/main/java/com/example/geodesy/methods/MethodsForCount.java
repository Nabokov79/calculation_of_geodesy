package com.example.geodesy.methods;

import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ObjectsByValues;
import com.example.geodesy.storage.StorageInterface;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.LinkedList;

public interface MethodsForCount {

    String processCell(Cell cell);
    void setInputValues(Row row, ObjectsByValues objects);
    void setFlag(Row row, ObjectsByValues objects);
    Workbook loadWorkbook(File path);
    LinkedList<ObjectData> createObjectsList(String key, StorageInterface repository, ObjectsByValues objects);
    LinkedList<ObjectData> createListObject(StorageInterface repository, ObjectsByValues objects, MethodsForCount method);
    void setMinValues(StorageInterface repository);
    void setKey(String key);
}
