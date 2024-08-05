package com.jonathantippy.data;

import java.util.ArrayList;

public class Tokenizer {
    public static Evaluable tokenize(String expression) {

        if (expression.contains("+")) {
            String[] parts = expression.split("\\+", 2);
            return new Plus(tokenize(parts[0]), tokenize(parts[1]));
        } else if (expression.contains("*")) {
            String[] parts = expression.split("\\*", 2);
            return new Times(tokenize(parts[0]), tokenize(parts[1]));   
        } else {
            return new Value(Integer.parseInt(expression));
        }

    }
}

