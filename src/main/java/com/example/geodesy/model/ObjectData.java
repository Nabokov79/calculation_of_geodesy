package com.example.geodesy.model;

public class ObjectData {

    private final int flag;
    private final String date;
    private final String address;
    private final int number;
    private final int point;
    private final int controlPoint;
    private final int transition;
    private final int oldDeviation;
    private final int newDeviation;
    private final int precipitation;
    private final int controlPointDeviation;
    private final String neighboring;
    private final String diagonal;


    private ObjectData(ObjectDataBuilder data) {
        this.flag = data.flag;
        this.date = data.date;
        this.address = data.address;
        this.number = data.number;
        this.point = data.point;
        this.controlPoint = data.controlPoint;
        this.transition = data.transition;
        this.oldDeviation = data.oldDeviation;
        this.newDeviation = data.newDeviation;
        this.precipitation = data.precipitation;
        this.neighboring = data.neighboring;
        this.diagonal = data.diagonal;
        this.controlPointDeviation = data.controlPointDeviation;
    }

    public int getFlag() {
        return flag;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public int getPoint() {
        return point;
    }

    public int getControlPoint() {
        return controlPoint;
    }

    public int getTransition() {
        return transition;
    }

    public int getOldDeviation() {
        return oldDeviation;
    }

    public int getNewDeviation() {
        return newDeviation;
    }

    public int getControlPointDeviation() {
        return controlPointDeviation;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public String getNeighboring() {
        return neighboring;
    }

    public String getDiagonal() {
        return diagonal;
    }

    @Override
    public String toString() {
        return "ObjectData{" +
                "flag=" + flag +
                ", data=" + date +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", point=" + point +
                ", controlPoint=" + controlPoint +
                ", transition=" + transition +
                ", oldDeviation=" + oldDeviation +
                ", newDeviation=" + newDeviation +
                ", precipitation=" + precipitation +
                ", controlPointDeviation=" + controlPointDeviation +
                ", neighboring='" + neighboring + '\'' +
                ", diagonal='" + diagonal + '\'' +
                '}';
    }

    public static class ObjectDataBuilder {

        private final int flag;
        private final String address;
        private final int number;
        private  String date;
        private int transition;
        private int point;
        private int controlPoint;
        private int oldDeviation;
        private int newDeviation;
        private int precipitation;
        private int controlPointDeviation;
        private String neighboring;
        private String diagonal;

        public ObjectDataBuilder(int flag, String address, int number) {
            this.flag = flag;
            this.address = address;
            this.number = number;
        }

        public ObjectDataBuilder point(int point) {
            this.point = point;
            return this;
        }

        public ObjectDataBuilder data(String date) {
            this.date = date;
            return this;
        }

        public ObjectDataBuilder controlPoint(int controlPoint) {
            this.controlPoint = controlPoint;
            return this;
        }

        public ObjectDataBuilder transition(int transition) {
            this.transition = transition;
            return this;
        }

        public ObjectDataBuilder oldDeviation(int oldDeviation) {
            this.oldDeviation = oldDeviation;
            return this;
        }

        public ObjectDataBuilder newDeviation(int newDeviation) {
            this.newDeviation = newDeviation;
            return this;
        }

        public ObjectDataBuilder precipitation(int precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public ObjectDataBuilder controlPointDeviation(int controlPointDeviation) {
            this.controlPointDeviation = controlPointDeviation;
            return this;
        }

        public ObjectDataBuilder neighboring(String neighboring) {
            this.neighboring = neighboring;
            return this;
        }

        public ObjectDataBuilder diagonal(String diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public ObjectData build() {
            return new ObjectData(this);
        }
    }
}

