package com.example.geodesy.count;

import java.io.File;
import java.util.List;

public interface CountInterface {

    void readFromFile(List<File> fileList);
    void countValuesFromFiles();
    void countValuesFromStorage();
    public void readValuesToFile(CountInterface count);
    void createFileSample();
}
