package com.example.geodesy.methods;

import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ReadFile;
import com.example.geodesy.storage.StorageInterface;

import java.util.*;

public class ReadToFile {

    private final ReadFile readFile = new ReadFile();

    public final Set<ObjectData> objectList = new TreeSet<>(new NumberComparator());
    private final Map<String, Integer> neighboring = new HashMap<>();
    private final Map<Integer, String> diagonal = new HashMap<>();
    private int flag;

    public void readToFile(String key, StorageInterface repository) {
        objectList.addAll(repository.getFinalValue().get(key));
        readFile.createBook(key);
        addValues();
        readFile.writeValue(flag, objectList, neighboring, diagonal);
        objectList.clear();
    }

    static class NumberComparator implements Comparator<ObjectData> {
        @Override
        public int compare(ObjectData o1, ObjectData o2) {
            return Integer.compare(o1.getNumber(), o2.getNumber());
        }
    }

    public void addValues() {
        for (ObjectData data : objectList) {
            flag = data.getFlag();
            if (data.getFlag() == 1 || data.getFlag() ==3) {
               readFile.setDate(data.getDate());
            }
            if (data.getNeighboring() != null) {
                String[] line = data.getNeighboring().split(";");
                for (String value : line) {
                    String[] values = value.split(",");
                    neighboring.put(String.join(",", values[0], values[1]), Integer.parseInt(values[2]));
                }
            }
            if (data.getDiagonal() != null) {
                String[] line = data.getDiagonal().split(";");
                for (int i = 0; i < line.length; i++) {
                    diagonal.put(i + 1, line[i]);
                }
            }
        }
    }
}
