package com.example.geodesy.methods;

import com.example.geodesy.model.ObjectData;
import com.example.geodesy.model.ObjectsByValues;
import com.example.geodesy.storage.StorageInterface;
import java.util.LinkedList;

public class InitialValuesMethods extends CalculationPoints{

    private int transition;
    private int delta;

    public LinkedList<ObjectData> createObjectsList(String key, StorageInterface repository, ObjectsByValues objects) {
        delta = 0;
        transition = 0;
        LinkedList<ObjectData> valueList = new LinkedList<>();
        for (ObjectData values : repository.getInputData().get(key)) {
            objects.setFlag(values.getFlag());
            if (values.getTransition() != 0) {
                valueList.addLast(getNewObject(values));
                transition = values.getTransition();
                setDelta(values.getPoint(), values.getControlPoint());
            } else {
                valueList.addLast(getNewObject(values));
            }
        }
        return valueList;
    }

    private ObjectData getNewObject(ObjectData values) {
        return new ObjectData.ObjectDataBuilder(values.getFlag(), values.getAddress(), values.getNumber())
                .data(values.getDate())
                .point(values.getPoint() + delta)
                .controlPoint(values.getControlPoint() + delta)
                .oldDeviation(values.getOldDeviation())
                .build();
    }

    private void setDelta(int point, int controlPoint) {
        if (point != 0) {
            delta += point - transition;
        } else {
            delta += controlPoint - transition;
        }
    }
}
