package com.jonathantippy.data;

class Fraction {
    public long numerator;
    public long denomenator;

    public Fraction(long numerator, long denomenator) {
        this.numerator = numerator; this.denomenator = denomenator;
    }

    public Fraction(long in) {
        this.numerator = in; this.denomenator = 1;
    }

    public Fraction(int in) {
        this.numerator = (long) in; this.denomenator = 1;
    }

    public Fraction(String str) {
        if (str.contains(".")) {
            String[] parts = str.split("\\.")
            int part0 = Integer.parseInt(parts[0]);
            int part1 = Integer.parseInt(parts[1]);

            this.numerator = part0 * 100 + part1;
            this.denomenator = 100;
        } else {
            this.numerator = Integer.parseInt(str); this.denomenator = 1;
        }
    }

    public add() {
        
    }
}