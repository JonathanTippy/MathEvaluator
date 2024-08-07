package com.jonathantippy.data;

import java.util.ArrayList;

public class BalloonInteger {
    public ArrayList<Integer> integerList = new ArrayList<Integer>();

    public BalloonInteger(int val) {
        integerList.add(val);
    }

    public BalloonInteger(long val) {
        integerList.add((int) (val & 0xFFFFFFFFL));
        integerList.add((int) (val >> 32));
    }

    public BalloonInteger() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        BalloonInteger bint = (BalloonInteger) obj;
        return (integerList.equals(bint.integerList));
    }

    @Override
    public String toString() {
        //TODO: make gooder
        if (integerList.size() < 2) {
            return "" + integerList.get(0);
        } else {
            return (integerList.size() - 1) + " * " + Integer.MAX_VALUE + " + " + integerList.get(0);
        }
    }

    public int get(int index) {
        if (integerList.size() > index) {
            return integerList.get(index);
        } else {
            return 0;
        }
    }

    public void add(int value) {
        integerList.add(value);
    }

    public int size() {
        return integerList.size();
    }

    public static BalloonInteger parseBalloonInt(String parsing) {
        BalloonInteger ten = new BalloonInteger(10);
        BalloonInteger returned = new BalloonInteger();
        for (int i = 0; i < parsing.length(); i++) {
            int localDigitValue = Integer.parseInt(parsing.substring(i, i+1));
            BalloonInteger digitValue = new BalloonInteger(localDigitValue);
            for (int j = 0; j<i; j++) {
                digitValue = BalloonInteger.multiply(digitValue, ten);
            }
            returned = BalloonInteger.add(returned, digitValue);
        }
        return returned;
    }


    public static BalloonInteger add(BalloonInteger leftTerm, BalloonInteger rightTerm) {
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        for (int i = 0; i<longerLength;i++) {
            long localSum = (long) (leftTerm.get(i)) + (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localSum & 0xFFFFFFFFL));
            carryTerm = (int) (localSum >> 32);
        }

        return returned;
    }

    public static BalloonInteger subtract(BalloonInteger leftTerm, BalloonInteger rightTerm) {
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        for (int i = 0; i<longerLength;i++) {
            long localDifference = (long) (leftTerm.get(i)) - (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localDifference & 0xFFFFFFFFL));
            carryTerm = (int) (localDifference >> 32);
        }

        return returned;
    }

    public static BalloonInteger multiply(BalloonInteger leftTerm, BalloonInteger rightTerm) {
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        for (int i = 0; i<longerLength;i++) {
            long localProduct = (long) (leftTerm.get(i)) * (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localProduct & 0xFFFFFFFFL));
            carryTerm = (int) (localProduct >> 32);
        }

        return returned;
    }
}