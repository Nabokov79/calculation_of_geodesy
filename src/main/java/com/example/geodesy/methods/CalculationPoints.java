package com.example.geodesy.methods;

import com.example.geodesy.model.ControlPoints;
import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ObjectsByValues;
import com.example.geodesy.storage.StorageInterface;

import java.util.*;

public class CalculationPoints extends ReadToFile {

    private final Map<Integer, Integer> deviationControlPoint = new HashMap<>();
    private ObjectData object;

    private String key;
    private int minNumber;
    private int maxNumber;

    private int minPoint;
    private int minControlPoint;

    public void setKey(String key) {
        this.key = key;
    }

    public LinkedList<ObjectData> createListObject(StorageInterface repository, ObjectsByValues objects, MethodsForCount method) {
        LinkedList<ObjectData> values = new LinkedList<>();
        for (ObjectData value : repository.getNewValues().get(key)) {
            object = value;
            objects.setFlag(value.getFlag());
            if (objects.getAddress() == null) {
                objects.setAddress(object.getAddress());
            }
            objects.setNumber(object.getNumber());
            setMinMaxNumber(repository);
            setValuePoint(objects);
            setValueControlPoint(repository, objects);
            values.addLast(objects.createObjects());
        }
        return values;
    }

    private void setValuePoint(ObjectsByValues objects) {
        objects.setDate(object.getDate());
        objects.setPoints(object.getPoint());
        objects.setOldDeviation(object.getOldDeviation());
        objects.setNewDeviation(minPoint - object.getPoint());
        objects.setPrecipitation(objects.getNewDeviation() - objects.getOldDeviation());
    }

    protected void setValueControlPoint(StorageInterface repository, ObjectsByValues objects) {
        addDeviationControlPoint(repository);
        objects.setControlPoint(object.getControlPoint());
        if (object.getNumber() == maxNumber && minNumber != maxNumber) {
            getValuesNeighboringPoints(objects);
            objects.setDiagonal(getValuesDiagonalPoints());
        }
        objects.setControlPointDeviation(deviationControlPoint.get(object.getNumber()));
    }

    public void setMinValues(StorageInterface repository) {
        setMinPoint(0);
        setMinControlPoint(0);
        for (ObjectData values : repository.getNewValues().get(key)) {
           setMinPoint(values.getPoint());
            setMinControlPoint(values.getControlPoint());
        }
    }

    private void addDeviationControlPoint(StorageInterface repository) {
        for (ObjectData data : repository.getNewValues().get(key)) {
            deviationControlPoint.put(data.getNumber(), minControlPoint - data.getControlPoint());
        }
    }

    private void getValuesNeighboringPoints(ObjectsByValues objects) {
        LinkedList<ControlPoints> neighboring = new LinkedList<>();
        for (Integer value : deviationControlPoint.keySet()) {
            if (value == maxNumber) {
                neighboring.addLast(new ControlPoints(getKey(minNumber, maxNumber),
                        Math.abs(deviationControlPoint.get(maxNumber) - deviationControlPoint.get(minNumber))));
            }
            if (value < maxNumber) {
                int number = value++;
                neighboring.addLast(new ControlPoints(getKey(number, value),
                        Math.abs(deviationControlPoint.get(value) - deviationControlPoint.get(number))));
            }
        }
        objects.setNeighboring(getString(neighboring));
    }

    private String getValuesDiagonalPoints() {
        LinkedList<ControlPoints> diagonal = new LinkedList<>();
        int difference = (int) Math.floor(maxNumber / 2.0);
        for (Integer key : deviationControlPoint.keySet()) {
            if (key <= maxNumber - difference) {
                diagonal.addLast(new ControlPoints(getKey(key, key + difference),
                        Math.abs(deviationControlPoint.get(key) - deviationControlPoint.get(key + difference))));
            }
        }
        return getString(diagonal);
    }

    private String getKey(int first, int second) {
        return String.join(",", String.valueOf(first), String.valueOf(second));
    }

    private String getString(LinkedList<ControlPoints> value) {
        List<String> string = new ArrayList<>();
        for (ControlPoints val : value) {
            string.add(String.join(",", val.getKey(), String.valueOf(val.getValue())));
        }
        return String.join(";", string);
    }

    private void setMinMaxNumber(StorageInterface repository) {
        for (ObjectData values : repository.getNewValues().get(key)) {
            if (minNumber == 0 || maxNumber == 0) {
                if (minNumber < values.getNumber()) {
                    minNumber = values.getNumber();
                    maxNumber = values.getNumber();
                }
            } else {
                if (minNumber > values.getNumber()) {
                    minNumber = values.getNumber();
                } else {
                    maxNumber = values.getNumber();
                }
            }
        }
    }

    public void setMinPoint(int valuePoint) {
        if (valuePoint == 0) {
            minPoint = valuePoint;
        } else {
            minPoint = setMinValue(minPoint, valuePoint);
        }
    }


    public void setMinControlPoint(int valueControlPoint) {
        if (valueControlPoint == 0) {
            minControlPoint = 0;
        } else {
            minControlPoint = setMinValue(minControlPoint, valueControlPoint);
        }
    }

    private int setMinValue(int valueFirst, int valueSecond) {
        if (valueFirst == 0 || valueFirst > valueSecond) {
            return valueSecond;
        } else {
            return valueFirst;
        }
    }
}
