package com.jonathantippy.data;

import java.util.ArrayList;

public class Evaluator {

	public record Operator(short position, char codeStart, char codeEnd) {}

	public ArrayList<Operator> operators = new ArrayList<>();
	operators.add(new Operator(0, '+', ''));

	public record Token(short type, int value) {}

	public static int evaluate(String stuff) {
		List<Token> tokens = tokenize(stuff);
		return 0;
	}

	

	public static List<Token> tokenize(String stuff) {
		List<Token> tokens = new ArrayList<>();
		for ()
	}

}
