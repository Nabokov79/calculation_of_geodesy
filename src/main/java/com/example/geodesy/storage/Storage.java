package com.example.geodesy.storage;

import com.example.geodesy.model.ObjectData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Storage implements StorageInterface {

    private final Map<String, LinkedList<ObjectData>> inputData = new HashMap<>();
    private final Map<String, LinkedList<ObjectData>> newValuesPoint = new HashMap<>();
    private final Map<String, LinkedList<ObjectData>> finalValue = new HashMap<>();

    @Override
    public Map<String, LinkedList<ObjectData>> getInputData() {
        return inputData;
    }

    @Override
    public void addInputData(String key, LinkedList<ObjectData> value) {
        inputData.put(key, value);
    }

    @Override
    public Map<String, LinkedList<ObjectData>> getNewValues() {
        return newValuesPoint;
    }

    @Override
    public void addNewValues(String key, LinkedList<ObjectData> value) {
        newValuesPoint.put(key, value);
    }

    @Override
    public Map<String, LinkedList<ObjectData>> getFinalValue() {
        return finalValue;
    }

    @Override
    public void addFinalValue(String key, LinkedList<ObjectData> value) {
        finalValue.put(key, value);
    }
}
