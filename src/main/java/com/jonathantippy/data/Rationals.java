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

            this.numerator = part0 * 100 + part1;
            this.denomenator = 100;
        } else {
            this.numerator = Integer.parseInt(str); this.denomenator = 1;
        }
    }

}