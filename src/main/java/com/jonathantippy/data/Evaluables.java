package com.jonathantippy.data;

public abstract class Evaluable {
    public int Evaluate() {}
}

public abstract class UnaryEvaluable : Evaluable {
    public int Evaluate() {}
    public Evaluable term;
}

public abstract class BinaryEvaluable : Evaluable {
    public int Evaluate() {}
    public Evaluable leftTerm;
    public Evaluable rightTerm;
}

public class Value : Evaluable {

    public int value;

    public Value(value) {
        this.value = value;
    }

    public int Evaluate() {
        return value;
    }

}

public class Plus : BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Plus(leftTerm, rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public int Evaluate() {
        return leftTerm.Evaluate() + rightTerm.Evaluate();
    }

}