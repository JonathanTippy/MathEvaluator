package com.jonathantippy.MathEvaluator;

abstract class ComparisonEvaluable {
    public abstract boolean evaluate();
    public abstract String detokenize();

    public Evaluable[] expressions;

    public ComparisonEvaluable(Evaluable... expressions) {this.expressions = expressions;}
}

class Equals extends ComparisonEvaluable {
    public Equals(Evaluable... expressions) {
        super(expressions);
    }

    public boolean evaluate() {
	
	int count = 0;
	double previousResult = 0;
	boolean equal = true;
        for (Evaluable expression : expressions) {
	    double result = expression.evaluate();
	    if (count > 0) {
                equal = (equal && result==previousResult);
	    }
	    previousResult = result;
	    count++;
        }
	return equal;
    }

    public String detokenize() {
        String returned = "";
	    for (Evaluable expression : expressions) {
            returned+=expression.detokenize();
            returned+="=";
	    }	
        returned = returned.substring(0, returned.length()-2);
        return returned;
    }
}
/*
class GreaterThan extends ComparisonEvaluable {
    public Equals (Evaluable... expressions) {
        super(expressions);

        public boolean evaluate() {
            
        }
    }
}
*/
abstract class Evaluable {
    public abstract double evaluate();
    public abstract String detokenize();
}

abstract class UnaryEvaluable extends Evaluable {
    public abstract double evaluate();
    public abstract String detokenize();

    public Evaluable term;
}

abstract class BinaryEvaluable extends Evaluable {
    public abstract double evaluate();
    public abstract String detokenize();

    public Evaluable leftTerm;
    public Evaluable rightTerm;
}

class Value extends Evaluable {

    public double value;

    public Value(double value) {
        this.value = value;
    }

    public final double evaluate() {
        return value;
    }

    public final String detokenize() {
        return "" + value;
    }

}

class Plus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Plus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public final double evaluate() {
        return leftTerm.evaluate() +rightTerm.evaluate();
    }

    public final String detokenize() {
        return leftTerm.detokenize() + "+" + rightTerm.detokenize();
    }

}

class Minus extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Minus(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public final double evaluate() {
        return leftTerm.evaluate()-rightTerm.evaluate();
    }

    public final String detokenize() {
        return leftTerm.detokenize() + "-" + rightTerm.detokenize();
    }

}

class Times extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Times(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public final double evaluate() {
        return leftTerm.evaluate()*rightTerm.evaluate();
    }

    public final String detokenize() {
        return leftTerm.detokenize() + "*" + rightTerm.detokenize();
    }

}

class Divide extends BinaryEvaluable {

    public Evaluable leftTerm;
    public Evaluable rightTerm;

    public Divide(Evaluable leftTerm, Evaluable rightTerm) {
        this.leftTerm = leftTerm; this.rightTerm = rightTerm;
    }

    public final double evaluate() {
        return leftTerm.evaluate()/rightTerm.evaluate();
    }

    public final String detokenize() {
        return leftTerm.detokenize() + "/" + rightTerm.detokenize();
    }

}

class Parenthesis extends UnaryEvaluable {
    public Evaluable term;

    public Parenthesis(Evaluable term) {
        this.term = term;
    }

    public final double evaluate() {
        return term.evaluate();
    }

    public final String detokenize() {
        return "(" + term.detokenize() + ")";
    }
}

class Negative extends UnaryEvaluable {
    public Evaluable term;

    public Negative(Evaluable term) {
        this.term = term;
    }

    public double evaluate() {
        return (-term.evaluate());
    }

    public final String detokenize() {
        return "-" + term.detokenize();
    }
}
