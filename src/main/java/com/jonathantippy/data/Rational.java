/*
    SHELFED
    thoughts:
    To solve the problem of these balloons getting too big, 
    shift both the numerator and denomenator right by the same amount to drop some precision.
    These will help a LOT with testing for equality
*/


package com.jonathantippy.data;


class Rational {

    public BalloonInteger numerator = new BalloonInteger(0);
    public BalloonInteger denomenator = new BalloonInteger(0);

    public Rational(BalloonInteger numerator, BalloonInteger denomenator) {
        this.numerator = numerator; this.denomenator = denomenator;
    }

    public Rational(BalloonInteger in) {
        this.numerator = in;
        this.denomenator = new BalloonInteger(1);
    }

    public Rational(int numerator, int denomenator) {
        this.numerator = new BalloonInteger(numerator); this.denomenator = new BalloonInteger(denomenator);
    }

    public Rational(long numerator, long denomenator) {
        this.numerator = new BalloonInteger(numerator); this.denomenator = new BalloonInteger(denomenator);
    }

    public Rational(int in) {
        this.numerator = new BalloonInteger(in); this.denomenator = new BalloonInteger(1);
    }

    public Rational(long in) {
        this.numerator = new BalloonInteger(in); this.denomenator = new BalloonInteger(1);
    }

    public Rational() {}

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Rational rat = (Rational) obj;
        return (
            (BalloonInteger.multiply(numerator, rat.denomenator))
            .equals(BalloonInteger.multiply(rat.numerator, denomenator))
            );
    }

    @Override
    public String toString() {
        return numerator + "/" + denomenator;
    }

    public static Rational parseRational(String str) {
        Rational returned = new Rational();
        if (str.contains(".")) {
            String[] parts = str.split("\\.");
            BalloonInteger part0 = BalloonInteger.parseBalloonInt(parts[0]);
            BalloonInteger part1 = BalloonInteger.parseBalloonInt(parts[1]);
            BalloonInteger fac = new BalloonInteger(parts[1].length());
            returned.numerator = BalloonInteger.add(BalloonInteger.multiply(part0, fac), part1);
            returned.denomenator = fac;
        } else {
            returned.numerator = BalloonInteger.parseBalloonInt(str); 
            returned.denomenator = new BalloonInteger(1);
        }
        return returned;

    }



    public static Rational multiply(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            BalloonInteger.multiply(leftTerm.numerator, rightTerm.numerator)
            , BalloonInteger.multiply(leftTerm.denomenator, rightTerm.denomenator)
        );
    }

    public static Rational divide(Rational leftTerm, Rational rightTerm) throws ArithmeticException {
        
        if ( ! rightTerm.numerator.equals(new BalloonInteger(0))) {
            return new Rational(
                BalloonInteger.multiply(leftTerm.numerator, rightTerm.denomenator)
                , BalloonInteger.multiply(leftTerm.denomenator, rightTerm.numerator)
            );
        } else {
            throw new ArithmeticException("Exception: Cannot divide by zero because it is undefined");
        }
    }

    public static Rational add(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            BalloonInteger.add
            (
            BalloonInteger.multiply(leftTerm.numerator, rightTerm.denomenator) 
            , BalloonInteger.multiply(rightTerm.numerator , leftTerm.denomenator)
         )
        , BalloonInteger.multiply(leftTerm.denomenator, rightTerm.denomenator)
        );
    }

    public static Rational subtract(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            BalloonInteger.subtract
            (
            BalloonInteger.multiply(leftTerm.numerator, rightTerm.denomenator) 
            , BalloonInteger.multiply(rightTerm.numerator , leftTerm.denomenator)
         )
        , BalloonInteger.multiply(leftTerm.denomenator, rightTerm.denomenator)
        );
    }
}