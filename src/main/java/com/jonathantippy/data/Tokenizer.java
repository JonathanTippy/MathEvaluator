package com.jonathantippy.data;

import java.util.ArrayList;

public class Tokenizer {



    public static final String preProcess(String expression) {
        String returned = "";
        returned = expression.replaceAll("\\s+", "");
        return returned;
    }






    public static final Evaluable tokenize(String expression) {

        String message = "";

        
        String preProcessedExpression = preProcess(expression);
        //message+=("expression: " + preProcessedExpression + "\n");
        Evaluable tree = RecursiveDescentTokenize(preProcessedExpression);
        String detokenizedTree = tree.detokenize();
        //message+=("detokenized tree: " + detokenizedTree);
        //System.out.println(message);
        return tree;


    }






    public static final Evaluable RecursiveDescentTokenize(String expression) {

        String message = "";

        message+="Expression:";
        message+=expression;

        String maskedExpression = maskExpression(expression);

        if (maskedExpression.contains("+") || maskedExpression.contains("-")) {

            int plusPos = maskedExpression.lastIndexOf("+");
            int minusPos = maskedExpression.lastIndexOf("-");

            int pos = Math.max(plusPos, minusPos);

            String part0 = expression.substring(0, pos);
            String part1 = expression.substring(pos + 1, expression.length());

            if (pos == plusPos) {
                return new Plus(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            } else {
                return new Minus(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            }
            
        } else if (maskedExpression.contains("*") || maskedExpression.contains("/")) {

            int timesPos = maskedExpression.lastIndexOf("*");
            int dividePos = maskedExpression.lastIndexOf("/");

            int pos = Math.max(timesPos, dividePos);

            String part0 = expression.substring(0, pos);
            String part1 = expression.substring(pos + 1, expression.length());

            if (pos == timesPos) {
                return new Times(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            } else {
                return new Divide(RecursiveDescentTokenize(part0), RecursiveDescentTokenize(part1));
            }
            
        } else if (expression.contains("-")) {
            return new Negative(RecursiveDescentTokenize(expression.substring(1, expression.length())));
        } else if (expression.contains("(")) {
            int length = expression.length();
            if (expression.substring(length - 1, length).equals(")")) {;} else {
                System.out.println("ERROR: mismatched parenthises or unrecognized characters");
                System.out.println(message);
            }
            String contents = expression.substring(1, expression.length()-1);
            return new Parenthesis(RecursiveDescentTokenize(contents));
        } 
        return new Value(Double.parseDouble(expression));
    }





    public static final String maskExpression(String expression) {

        String returned = "";

        if (expression.contains("(")) {
            String searchArea = "";

            int openingPos = expression.indexOf("(");

            searchArea += expression.substring(0, openingPos);

            int closingPos = posOfClosingParen(expression, openingPos);

            for (int i = 0; i<closingPos-openingPos + 1;i++) {
                searchArea+=" ";
            }

            searchArea += expression.substring(closingPos+1, expression.length());

            returned = searchArea;
        } else {
            returned = expression;
        }

        String returned2 = "";

        for (int i =0; i<returned.length(); i++) {
            if (returned.substring(i, i+1).equals("-")) {
                if (i == 0) {
                    returned2+=" ";
                    continue;
                } else if (
                    (!returned.substring(i-1, i).equals(")"))
                    && (!returned.substring(i-1, i).matches("\\d"))
                    ) {
                        returned2+=" ";
                        continue;
                    }
            }
            returned2+=returned.substring(i, i+1);
        }



        return returned2;
        

        
    }





    public static final int posOfClosingParen(String expression, int openingPos) {
        int parenParity = 1;
        int parenSearchIndex = openingPos;

        while (parenParity > 0) {
            parenSearchIndex++;

            String currentCharacter = expression.substring(parenSearchIndex, parenSearchIndex + 1);
            if (currentCharacter.equals("(")) {
                parenParity++;
            } else if (currentCharacter.equals(")")) {
                parenParity--;
            }

            
        }
        return parenSearchIndex;
    }

}






class EmbeddedEvaluable {
    private int index;
    private Evaluable evaluable;

    public EmbeddedEvaluable(int index, Evaluable evaluable) {
        this.index = index; this.evaluable = evaluable;
    }

    public final int getIndex() {
        return index;
    }

    public final Evaluable getEvaluable() {
        return evaluable;
    }

    public final void setIndex(int index) {
        this.index = index;
    }
}

