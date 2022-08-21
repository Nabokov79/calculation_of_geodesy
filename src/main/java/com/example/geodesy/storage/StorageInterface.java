package com.example.geodesy.storage;

import com.example.geodesy.model.ObjectData;

import java.util.LinkedList;
import java.util.Map;

public interface StorageInterface {

    void addInputData(String key, LinkedList<ObjectData> value);
    Map<String, LinkedList<ObjectData>> getInputData();

    void addNewValues(String key, LinkedList<ObjectData> value);
    Map<String, LinkedList<ObjectData>> getNewValues();
    Map<String, LinkedList<ObjectData>> getFinalValue();
    void addFinalValue(String key, LinkedList<ObjectData> value);

}
