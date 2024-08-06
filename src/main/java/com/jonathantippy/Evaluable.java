package com.jonathantippy;

abstract class Evaluable {
    public abstract Rational evaluate();
}

abstract class UnaryEvaluable extends Evaluable {
    public abstract Rational evaluate();

    public Evaluable term;
}

abstract class BinaryEvaluable extends Evaluable {
    public abstract Rational evaluate();

    public Evaluable leftTerm;
    public Evaluable rightTerm;
}

class Value extends Evaluable {

    public Rational value;

    public Value(Rational value) {
        this.value = value;
    }

    public Rational evaluate() {
        return value;
    }

}

class Plus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Plus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public Rational evaluate() {
        return Rational.add(leftTerm.evaluate(), rightTerm.evaluate());
    }

}

class Minus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Minus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public Rational evaluate() {
        return Rational.subtract(leftTerm.evaluate(), rightTerm.evaluate());
    }

}

class Times extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Times(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public Rational evaluate() {
        return Rational.multiply(leftTerm.evaluate(), rightTerm.evaluate());
    }

}

class Divide extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Divide(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public Rational evaluate() {
        return Rational.divide(leftTerm.evaluate(), rightTerm.evaluate());
    }

}