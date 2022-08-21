package com.example.geodesy.count;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {
    private final CountInterface count = new InMemoryManager();
    private final List<File> fileList = new ArrayList<>();

    public List<File> getFileList() {
        return fileList;
    }

    public void startReading() {
        count.readFromFile(fileList);
        count.countValuesFromFiles();
        count.countValuesFromStorage();
        count.readValuesToFile(count);
    }

    public void addFileNames(String directory) {
        File dir = new File(directory);
        File[] file = dir.listFiles();
        if (file != null) {
            fileList.addAll(Arrays.asList(file));
        }
    }

    public void createFileSample() {
        count.createFileSample();
    }
}