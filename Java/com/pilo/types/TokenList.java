package com.pilo.types;

public class TokenList {
    public enum Type {
        /* START Template literals */
        TEMPLATE_SPAN("template", null,0),
        TEMPLATE_TAIL("template", null, 0),
        /* END Template literals */
        /* Punctuactors */
        /* START Property */
        PERIOD("punctuactor", ".", 0),
        LBRACK("punctuactor", "[", 0),
        /* END Property */
        QUESTION_PERIOD("punctuactor", "?.", 0),
        LPAREN("punctuactor", "(", 0),
        RPAREN("punctuactor", ")", 0);
        /* TO DO ADD THE REST OF TOKEN LIST, THERE ARE MULTIPLE OF THERE */
        
        private final String category;
        private final String symbol;
        private final int precedence;

        Type(String category, String symbol, int precedence) {
            this.category = category;
            this.symbol = symbol;
            this.precedence = precedence;
        }


    }

    public static class BinaryOperator {
        private final Type type;
        private final String symbol;
        private final int precedence;

        public BinaryOperator(Type type, String symbol, int precedence) {
            this.type = type;
            this.symbol = symbol;
            this.precedence = precedence;
        }

        public Type getType() {
            return type;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getPrecedence() {
            return precedence;
        }
    }

    // TODO: Add binary opereators BinaryOperator[] BINARY_OPERATORS = {}

    // TODO: Add method to get symbol from BinaryOperator

    // TODO: Add method to get precedence from BinaryOperator

}