package com.jonathantippy.data;


class Rational {

    public long numerator;
    public long denomenator;

    public Rational(long numerator, long denomenator) {
        this.numerator = numerator; this.denomenator = denomenator;
    }

    public Rational(long in) {
        this.numerator = in; this.denomenator = 1;
    }

    public Rational(int in) {
        this.numerator = (long) in; this.denomenator = 1;
    }

    public Rational(String str) {
        if (str.contains(".")) {
            String[] parts = str.split("\\.");
            int part0 = Integer.parseInt(parts[0]);
            int part1 = Integer.parseInt(parts[1]);
            int fac = parts[1].length();
            this.numerator = part0 * fac + part1;
            this.denomenator = fac;
        } else {
            this.numerator = Integer.parseInt(str); this.denomenator = 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Rational rat = (Rational) obj;
        return (numerator * rat.denomenator == rat.numerator * denomenator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denomenator;
    }



    public static Rational multiply(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            leftTerm.numerator * rightTerm.numerator
            , leftTerm.denomenator * rightTerm.denomenator
        );
    }

    public static Rational divide(Rational leftTerm, Rational rightTerm) throws ArithmeticException {
        
        if (rightTerm.numerator != 0) {
            return new Rational(
                leftTerm.numerator * rightTerm.denomenator
                , leftTerm.denomenator * rightTerm.numerator
            );
        } else {
            throw new ArithmeticException("Exception: Cannot divide by zero because it is undefined");
        }
    }

    public static Rational add(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            leftTerm.numerator * rightTerm.denomenator 
        + rightTerm.numerator * leftTerm.denomenator
        , leftTerm.denomenator * rightTerm.denomenator
        );
    }

    public static Rational subtract(Rational leftTerm, Rational rightTerm) {
        return new Rational(
            leftTerm.numerator * rightTerm.denomenator 
        - rightTerm.numerator * leftTerm.denomenator
        , leftTerm.denomenator * rightTerm.denomenator
        );
    }
}