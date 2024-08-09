package com.jonathantippy.data;

import java.util.ArrayList;

public class Tokenizer {

    public static String preProcess(String expression) {
        String returned = "";
        returned = expression.replaceAll("\\s+", "");
        while (returned)
    }

    public static Evaluable tokenize(String expression) {

        String message = "";

        
        preProcessedExpression = preProcess(expression);
        message+=("expression: " + preProcessedExpression + "\n");
        Evaluable tree = RecursiveDescentTokenize(preProcessedExpression);
        String detokenizedTree = tree.detokenize();
        message+=("detokenized tree: " + detokenizedTree);
        System.out.println(message);
        return tree;


    }

    public static Evaluable RecursiveDescentTokenize(String expression) {

        if (expression.contains("+")) {

            int plusPos = expression.lastIndexOf("+");

            String part0 = expression.substring(0, plusPos);
            String part1 = expression.substring(plusPos + 1, expression.length());

            return new Plus(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            
        } else if (expression.contains("*") || expression.contains("/")) {

            int timesPos = expression.lastIndexOf("*");
            int dividePos = expression.lastIndexOf("/");
            int pos = Math.max(timesPos, dividePos);

            String part0 = expression.substring(0, pos);
            String part1 = expression.substring(pos + 1, expression.length());

            if (pos == timesPos) {
                return new Times(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            } else {
                return new Divide(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            }
            
        } else {
            return new Value(Double.parseDouble(expression));
        }

    }
}

