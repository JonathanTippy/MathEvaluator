package com.jonathantippy.data;

public class Evaluator {
	public static double evaluate(String expression) {
		Evaluable evaluableExpression = 
			Tokenizer.tokenize(expression);
		return evaluableExpression.evaluate();
	}
}
