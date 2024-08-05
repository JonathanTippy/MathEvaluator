package com.jonathantippy.data;

abstract class Evaluable {
    public abstract int evaluate();
}

abstract class UnaryEvaluable extends Evaluable {
    public abstract int evaluate();

    public Evaluable term;
}

abstract class BinaryEvaluable extends Evaluable {
    public abstract int evaluate();

    public Evaluable leftTerm;
    public Evaluable rightTerm;
}

class Value extends Evaluable {

    public int value;

    public Value(int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

}

class Plus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Plus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public int evaluate() {
        //System.out.println("Evaluate: Adding: " + leftTerm.evaluate() + " + " + rightTerm.evaluate());
        return leftTerm.evaluate() + rightTerm.evaluate();
    }

}

class Minus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Minus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public int evaluate() {
        //System.out.println("Evaluate: Subtracting: " + leftTerm.evaluate() + " - " + rightTerm.evaluate());
        return leftTerm.evaluate() - rightTerm.evaluate();
    }

}

class Times extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Times(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public int evaluate() {
        return leftTerm.evaluate() * rightTerm.evaluate();
    }

}

class Divide extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Divide(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public int evaluate() {
        return leftTerm.evaluate() / rightTerm.evaluate();
    }

}