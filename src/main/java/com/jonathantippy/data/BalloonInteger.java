package com.jonathantippy.data;

import java.util.ArrayList;

public class BalloonInteger {

    private ArrayList<Integer> integerList;
    private boolean twosCompliment;
    private int usedSize;

    public static final BalloonInteger BINT_ONE;
    public static final BalloonInteger BINT_TEN;
    public static final BalloonInteger BINT_ZERO;

    static {
        BINT_ONE = new BalloonInteger(1);
        BINT_TEN = new BalloonInteger(10);
        BINT_ZERO = new BalloonInteger(0);
    }

    public BalloonInteger(int val) {
        integerList = new ArrayList<Integer>();
        integerList.add(val & 0x7FFFFFFF); // the last 31 bits
        twosCompliment = ((val >> 31) == 1); // the high bit
        usedSize = 1;
    }

    public BalloonInteger(long val) {
        integerList = new ArrayList<Integer>();
        integerList.add((int) (val & 0xFFFFFFFFL)); // all 32 low bits
        integerList.add((int) ((val >> 32) & 0x7FFFFFFFL)); // the 31 bits below the high bit
        twosCompliment = ((val >> 63) == 1); // the high bit
        usedSize = 2;
    }

    public BalloonInteger() {
        integerList = new ArrayList<Integer>();
        twosCompliment = false;
        usedSize = 0;
    }

    public BalloonInteger(BalloonInteger other) {
        integerList = new ArrayList(other.integerList);
        twosCompliment = other.twosCompliment;
        usedSize = other.usedSize;
    }

    public ArrayList<Integer> getIntegerList() {
        return integerList;
    }

    public boolean getTwosCompliment() {
        return twosCompliment;
    }

    public int size() {
        return integerList.size();
    }

    public int usedSize() {
        return usedSize;
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

    public void append(int value) {
        integerList.add(value);
        usedSize++;
    }

    public static BalloonInteger parseBalloonInt(String parsing) {
        boolean positive = (! parsing.contains("-"));

        BalloonInteger ten = new BalloonInteger(10);
        BalloonInteger returned = new BalloonInteger(0);
        for (int i = 0; i < parsing.length(); i++) {
            int localDigitValue = Integer.parseInt(parsing.substring(i, i+1));
            BalloonInteger digitValue = new BalloonInteger(localDigitValue);
            
            int tenIndex = (parsing.length() - 1) - i;
            for (int j = 0; j<tenIndex; j++) {
                digitValue = multiply(digitValue, ten);
            }
            returned = add(returned, digitValue);
        }

        if (positive) {
            ;
        } else {
            negate(returned);
        }
                
        return returned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        BalloonInteger bint = (BalloonInteger) obj;
        return (integerList.equals(bint.integerList)); //TODO: fix that this responds to length
    }

    @Override
    public String toString() {
        //TODO: make gooder
        String returned = "";
        if (twosCompliment) {
            returned+= "negative ";
        }
        returned+=integerList.get(0);
        for (int i = 1; i<integerList.size();i++) {
            returned+=(" and ");
            if (twosCompliment) {
                returned+= "negative ";
            }
            returned += integerList.get(i);
        }
        return returned;
    }

    public static BalloonInteger negate(BalloonInteger input) {
        BalloonInteger returned = new BalloonInteger();
        returned.twosCompliment = (! input.twosCompliment);
        for (int i = 0; i < input.usedSize(); i++) {
            returned.integerList.add(~ (input.integerList.get(i)));
            returned.usedSize++;
        }
        returned = add(returned, BINT_ONE);
        return returned;
    }


    public static BalloonInteger add(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN ADD");

        System.out.println("left term:" + leftTerm);
        System.out.println("right term:" + rightTerm);

        BalloonInteger returned = new BalloonInteger();

        int longerLength = 0;
        if (leftTerm.usedSize() >= rightTerm.usedSize()) {
            longerLength = leftTerm.usedSize();
        } else {
            longerLength = rightTerm.usedSize();
        }

        byte carryTerm = 0;
        long localSum;
        for (int i = 0; i < longerLength; i++) {
            localSum = (((long) (leftTerm.get(i))) & 0xFFFFFFFFL) 
            + (((long) (rightTerm.get(i))) & 0xFFFFFFFFL) 
            + (((long) carryTerm) & 0xFFFFFFFFL);
            returned.integerList.add((int) (localSum & 0xFFFFFFFFL));
            returned.usedSize++;
            carryTerm = (byte) (localSum >> 32);
        }

        System.out.println("end carry:" + carryTerm);

        boolean l = leftTerm.twosCompliment;
        boolean r = rightTerm.twosCompliment;
        byte c = carryTerm;

        returned.twosCompliment = ((l ^ r) ^ ((l && r) ^ (c==1)));

        carryTerm = (((c == 1) && (!(l || r))) ? (byte) 1 : (byte) 0);

        System.out.println("final carry:" + carryTerm);

        if (carryTerm != 0) {
            returned.integerList.add((int) carryTerm);
            returned.usedSize++;
        }

        System.out.println("add result:" + returned);
        System.out.println("END ADD");
        return returned;
    }

    public static BalloonInteger subtract(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN SUBTRACT");
        BalloonInteger returned = add(leftTerm, negate(rightTerm));
        System.out.println("END SUBTRACT");
        return returned;
    }

    public static BalloonInteger multiply(BalloonInteger leftTerm, BalloonInteger rightTerm) {

        System.out.println("BEGIN MULTIPLY");

        System.out.println("left term:" + leftTerm);
        System.out.println("right term:" + rightTerm);

        BalloonInteger returned = new BalloonInteger();

        boolean l = leftTerm.twosCompliment;
        boolean r = rightTerm.twosCompliment;

        boolean negative = (l || r);

        BalloonInteger leftTermPositive;
        BalloonInteger rightTermPositive;

        if (!l) {
            leftTermPositive = new BalloonInteger(leftTerm);
        } else {
            leftTermPositive = negate(leftTerm);
        }

        if (!r) {
            rightTermPositive = new BalloonInteger(rightTerm);
        } else {
            rightTermPositive = negate(rightTerm);
        }

        int longerLength = 0;
        if (leftTermPositive.usedSize() >= rightTermPositive.usedSize()) {
            longerLength = leftTermPositive.usedSize();
        } else {
            longerLength = rightTermPositive.usedSize();
        }

        int carryTerm = 0;
        long localProduct;
        for (int i = 0; i < longerLength; i++) {
            localProduct = (long) (leftTerm.get(i)) * (long) (rightTerm.get(i)) + (long) carryTerm;
            returned.integerList.add((int) (localProduct & 0xFFFFFFFFL));
            returned.usedSize++;
            carryTerm = (int) (localProduct >> 32);
        }

        if (carryTerm != 0) {
            returned.integerList.add(carryTerm);
            returned.usedSize++;
        }

        if (!negative) {
            ;
        } else {
            returned = negate(returned);
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
            returned.append((int) localQuotient);
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