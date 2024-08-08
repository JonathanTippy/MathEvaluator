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

    public BalloonInteger(BalloonInteger other) {
        integerList = other.integerList;
    }

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
        String returned = "";
        returned+=integerList.get(0);
        for (int i = 1; i<integerList.size();i++) {
            returned+=(" and " + integerList.get(i));
        }
        return returned;
    }

    public int get(int index) {
        if (integerList.size() > index) {
            return integerList.get(index);
        } else {
            return 0;
        }
    }

    public void set(int index, int value) {
        if (integerList.size() > index) {
            integerList.set(index, value);
        } else {
            this.inflate(index);
            integerList.set(index, value);
        }
    }

    public void inflate(int size) {
        while (integerList.size() < size) {
            integerList.add(0);
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
        BalloonInteger returned = new BalloonInteger(0);
        for (int i = 0; i < parsing.length(); i++) {
            int tenIndex = (parsing.length() - 1) - i;
            int localDigitValue = Integer.parseInt(parsing.substring(i, i+1));
            BalloonInteger digitValue = new BalloonInteger(localDigitValue);
            for (int j = 0; j<tenIndex; j++) {
                digitValue = BalloonInteger.multiply(digitValue, ten);
            }
            returned = BalloonInteger.add(returned, digitValue);
        }
        return returned;
    }


    public static BalloonInteger add(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN ADD");
        
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        long localSum;
        for (int i = 0; i < longerLength || carryTerm != 0; i++) {
            localSum = (long) (leftTerm.get(i)) + (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localSum & 2147483647L));
            carryTerm = (int) (localSum >> 31);
        }

        System.out.println("END ADD");
        return returned;
    }

    public static BalloonInteger subtract(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN SUBTRACT");
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        long localDifference;
        for (int i = 0; i < longerLength || carryTerm != 0; i++) {
            localDifference = (long) (leftTerm.get(i)) - (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localDifference & 2147483647L));
            carryTerm = (int) (localDifference >> 31);
        }

        System.out.println("END SUBTRACT");

        return returned;
    }

    public static BalloonInteger multiply(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN MULTIPLY");
        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.size() >= rightTerm.size()) {
            longerLength = leftTerm.size();
        } else {
            longerLength = rightTerm.size();
        }

        int carryTerm = 0;
        long localProduct;
        for (int i = 0; i < longerLength || carryTerm != 0; i++) {
            localProduct = (long) (leftTerm.get(i)) * (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.add((int) (localProduct & 2147483647L));
            carryTerm = (int) (localProduct >> 31);
        }

        System.out.println("END MULTIPLY");

        return returned;
    }

    public static DivisionAnswer divideByTen(BalloonInteger input) {
        BalloonInteger returned = new BalloonInteger();
        BalloonInteger remaining = new BalloonInteger(input);

        long localDividend;
        long localQuotient;
    
        for (int i = input.size()-1; i>=0; i = i-1) {
            localDividend = ((long) (remaining.get(i + 1) << 32)) + ((long) (remaining.get(i)));
            localQuotient = localDividend / 10;
            returned.add((int) localQuotient);
            remaining = BalloonInteger.subtract(
                remaining
                , new BalloonInteger(localQuotient * 10)
            );
        }

        return new DivisionAnswer(returned, remaining);
    }
}

class DivisionAnswer {
    public BalloonInteger answer;
    public BalloonInteger remainder;

    public DivisionAnswer(BalloonInteger answer, BalloonInteger remainder) {
        this.answer = answer; this.remainder = remainder;
    }
}