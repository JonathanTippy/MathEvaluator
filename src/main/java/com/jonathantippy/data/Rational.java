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

    public multiply(Rational leftTerm, Rational rightTerm) {
        Rational returned = new Rational();
        returned.numerator = leftTerm.numerator * rightTerm.numerator;
        returned.denomenator = leftTerm.denomenator * rightTerm.denomenator;
        return returned;
    }

    public add(Rational leftTerm, Rational rightTerm) {
        Rational returned = new Rational();
        returned.denomenator = leftTerm.denomenator * rightTerm.denomenator;
        returned.numerator = leftTerm.numerator * rightTerm.denomenator 
        + rightTerm.numerator * leftTerm.denomenator;
    }

    public divide(Rational leftTerm, Rational rightTerm) {
        Rational returned = new Rational();
        returned.denomenator = 
    }



}