package com.jonathantippy.data;

import java.util.ArrayList;

public class Tokenizer {
    public static Evaluable tokenize(String expression) {

        if (expression.contains("+") || expression.contains("-")) {

            int plusPos = expression.lastIndexOf("+");
            int minusPos = expression.lastIndexOf("-");
            int pos = Math.max(plusPos, minusPos);

            String part0 = expression.substring(0, pos);
            String part1 = expression.substring(pos + 1, expression.length());

            if (pos == plusPos) {
                return new Plus(tokenize(part0), tokenize(part1));
            } else {
                return new Minus(tokenize(part0), tokenize(part1));
            }
            
        } else if (expression.contains("*") || expression.contains("/")) {

            int timesPos = expression.lastIndexOf("*");
            int dividePos = expression.lastIndexOf("/");
            int pos = Math.max(timesPos, dividePos);

            String part0 = expression.substring(0, pos);
            String part1 = expression.substring(pos + 1, expression.length());

            if (pos == timesPos) {
                return new Times(tokenize(part0), tokenize(part1));
            } else {
                return new Divide(tokenize(part0), tokenize(part1));
            }
            
        } else {
            return new Value(Rational.parseRational(expression));
        }

    }
}

