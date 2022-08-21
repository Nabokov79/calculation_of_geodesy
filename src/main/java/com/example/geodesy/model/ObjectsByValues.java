package com.example.geodesy.model;

public class ObjectsByValues {

    private String key;
    private int flag;
    private String address;
    private String date;
    private int number;
    private int points;
    private int controlPoint;
    private int transition;
    private int oldDeviation;
    private int newDeviation;
    private int precipitation;
    private int controlPointDeviation;
    private String neighboring;
    private String diagonal;

    public void setKey(String key) {
        this.key = key;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setControlPoint(int controlPoint) {
        this.controlPoint = controlPoint;
    }

    public void setTransition(int transition) {
        this.transition = transition;
    }

    public void setOldDeviation(int oldDeviation) {
        this.oldDeviation = oldDeviation;
    }

    public void setNewDeviation(int newDeviation) {
        this.newDeviation = newDeviation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public void setControlPointDeviation(int controlPointDeviation) {
        this.controlPointDeviation = controlPointDeviation;
    }

    public void setNeighboring(String neighboring) {
        this.neighboring = neighboring;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public String getKey() {
        return key;
    }

    public int getFlag() {
        return flag;
    }

    public String getAddress() {
        return address;
    }

    public int getOldDeviation() {
        return oldDeviation;
    }

    public int getNewDeviation() {
        return newDeviation;
    }

    public ObjectData createObjects() {
        switch (flag) {
            case 1:
               return new ObjectData.ObjectDataBuilder(flag, address, number)
                        .data(date)
                        .point(points)
                        .controlPoint(controlPoint)
                        .oldDeviation(oldDeviation)
                        .transition(transition)
                        .newDeviation(newDeviation)
                        .precipitation(precipitation)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();
            case 2:
                return new ObjectData.ObjectDataBuilder(flag, address, number)
                        .data(date)
                        .point(points)
                        .controlPoint(controlPoint)
                        .oldDeviation(oldDeviation)
                        .transition(transition)
                        .newDeviation(newDeviation)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();
            case 3:
                return new ObjectData.ObjectDataBuilder(flag, address, number)
                        .data(date)
                        .point(points)
                        .oldDeviation(oldDeviation)
                        .transition(transition)
                        .newDeviation(newDeviation)
                        .precipitation(precipitation)
                        .build();
            case 4:
                return new ObjectData.ObjectDataBuilder(flag, address, number)
                        .data(date)
                        .point(points)
                        .transition(transition)
                        .newDeviation(newDeviation)
                        .build();
            default:
                return new ObjectData.ObjectDataBuilder(flag, address, number)
                        .controlPoint(controlPoint)
                        .transition(transition)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();
        }
    }
}