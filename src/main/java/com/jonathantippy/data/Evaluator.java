package com.jonathantippy.data;

public class Evaluator {
	public static Rational evaluate(String expression) {
		Evaluable evaluableExpression = 
			Tokenizer.tokenize(expression.replaceAll("\\s+", ""));
		return evaluableExpression.evaluate();
	}
}
