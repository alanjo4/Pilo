package com.pilo.types;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TokenList {
    
    public enum Type {
        /* START PROPERTY OR CALL */
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
        /* END PROPERTY OR CALL */
        RPAREN("punctuactor", ")", 0),
        RBRACK("punctuactor", "]", 0),
        LBRACE("punctuactor", "{", 0),
        COLON("punctuactor", ":", 0),
        ELLIPSIS("punctuactor", "...", 0),
        CONDITIONAL("punctuactor", "?", 3),
        /* CONSIDER THAT JS WILL HAVE AN AUTOSEMICOLON BEHAVIOR */
        /* START AutoSemi Colon */
        SEMICOLON("punctuactor", ";", 0),
        RBRACE("punctuactor", "}", 0),
        /* This is just end of source indicator */
        EOS("punctuactor", "EOS", 0),
        /* END AutoSemicolon */
        /* START Arrow or Assignment operator */
        ARROW("punctuactor", "=>", 0),
        /* START Assignment operator */
        INIT("punctuactor", "=init", 2), // Esto lo debería usar para hacer el ast solamente
        ASSIGN("punctuactor", "=", 2), // This chould also be a binary operator
        /* END Assignment op */
        /* END Arrow or Assignment operator */
        COMMA("punctuactor", ",", 1),
        NOT("punctuactor", "!", 0),
        BIT_NOT("punctuactor", "~", 0),
        DELETE("punctuactor", "delete", 0),
        TYPEOF("punctuactor", "typeof", 0),
        VOID("punctuactor", "void", 0),
        /* START Count operator */
        INC("punctuactor", "++", 0),
        DEC("punctuactor", "--", 0),
        /* END Count operator */
        /* UNARY AND COUNT OPERATORS FINISH HERE */
        /* Comparissong operators sorted by precedence */
        EQ("punctuactor", "==", 9),
        EQ_STRICT("punctuactor", "===", 9),
        NE("punctuactor", "!=", 9),
        NE_STRICT("punctuactor", "!==", 0),
        LT("punctuactor", "--", 0),
        GT("punctuactor", "--", 0),
        LTE("punctuactor", "--", 0),
        GTE("punctuactor", "--", 0),
        INSTANCEOF("punctuactor", "--", 0),
        IN("punctuactor", "--", 0),
        BREAK("punctuactor", "--", 0),
        CASE("punctuactor", "--", 0),
        CATCH("punctuactor", "--", 0),
        CONTINUE("punctuactor", "--", 0),
        DEBUGGER("punctuactor", "--", 0), // This will be a pain...
        DEFAULT("punctuactor", "--", 0),
        DO("punctuactor", "--", 0),
        ELSE("punctuactor", "--", 0),
        FINALLY("punctuactor", "--", 0),
        FOR("punctuactor", "--", 0),
        FUNCTION("punctuactor", "--", 0),
        IF("punctuactor", "--", 0),
        NEW("punctuactor", "--", 0),
        RETURN("punctuactor", "--", 0),
        SWITCH("punctuactor", "--", 0),
        THROW("punctuactor", "--", 0),
        TRY("punctuactor", "--", 0),
        VAR("punctuactor", "--", 0),
        WHILE("punctuactor", "--", 0),
        WITH("punctuactor", "--", 0),
        THIS("punctuactor", "--", 0),
        NULL_LITERAL("punctuactor", "--", 0),
        TRUE_LITERAL("punctuactor", "--", 0),
        FALSE_LITERAL("punctuactor", "--", 0),
        NUMBER("punctuactor", "--", 0),
        SMI("punctuactor", "--", 0),
        BIGINT("punctuactor", "--", 0),
        STRING("punctuactor", "--", 0),
        SUPER("punctuactor", "--", 0),
        IDENTIFIER("punctuactor", "--", 0),
        GET("punctuactor", "--", 0),
        SET("punctuactor", "--", 0),
        ASYNC("punctuactor", "--", 0),
        AWAIT("punctuactor", "--", 0),
        YIELD("punctuactor", "--", 0),
        LET("punctuactor", "--", 0),
        STATIC("punctuactor", "--", 0),
        FUTURE_STRICT_RESERVERD_WORD("punctuactor", "--", 0), // This is related to the dead zone
        ESCAPED_STRICT_RESERVERD_WORD("punctuactor", "--", 0),
        ENUM("punctuactor", "--", 0),
        CLASS("punctuactor", "--", 0),
        CONST("punctuactor", "--", 0),
        EXPORT("punctuactor", "--", 0),
        IMPORT("punctuactor", "--", 0),
        PRIVATE_NAME("punctuactor", "--", 0),
        /* These tokens will be only for the scanner */
        ILLEGAL("punctuactor", "--", 0),
        ESCAPED_KEYWORD("punctuactor", "--", 0),
        WHITESPACE("punctuactor", "--", 0),
        UNINITIALIZED("punctuactor", "--", 0), // This will be very important for hoisting, dead zone and scope
        REGEXP_LITERAL("punctuactor", "--", 0);

        private final String category;
        private final String symbol;
        private final int precedence;

        Type(String category, String symbol, int precedence) {
            this.category = category;
            this.symbol = symbol;
            this.precedence = precedence;
        }
    }

    private static final Map<Type, Type> TypeMap = new EnumMap<>(Type.class);

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

    public static Type createBinaryType(String name, String symbol, int precedence) {
        try {
            if(Type.valueOf(name) != null) {
                throw new IllegalArgumentException("El tipo " + name + " ya existe en el enum Type.");
            }
        } catch (IllegalArgumentException e) {
            // Type does not existe, then whe can create
            Type newType = Type.valueOf(name.toUpperCase());
            ((EnumMap<Type, Type>) TypeMap).put(newType, newType);
    
            BinaryOperator binaryOperator = new BinaryOperator(newType, symbol, precedence);
            BINARY_OPERATORS.add(binaryOperator);
    
            return newType;
        }
        return null;
    }

    private static final List<BinaryOperator> BINARY_OPERATORS = new ArrayList<>();

    public static void addBinaryOperator(String name, String symbol, int precedence) {
        Type newType = createBinaryType(name, symbol, precedence);
        BinaryOperator binaryOperator = new BinaryOperator(newType, symbol, precedence);
        BINARY_OPERATORS.add(binaryOperator);
    }

    static {
        addBinaryOperator("NULLISH", "??", 3);
        addBinaryOperator("OR", "||", 4);
        addBinaryOperator("AND", "&&", 5);
        addBinaryOperator("BIT_OR", "|", 6);
        addBinaryOperator("BIT_XOR", "^", 7);
        addBinaryOperator("BIT_AND", "&", 8);
        addBinaryOperator("SHL", "<<", 11);
        addBinaryOperator("SAR", ">>", 11);
        addBinaryOperator("MUL", "*", 11);
        addBinaryOperator("DIV", "/", 11);
        addBinaryOperator("MOD", "%", 11);
        addBinaryOperator("EXP", "**", 11);
        addBinaryOperator("ADD", "+", 11);
        addBinaryOperator("SUB", "-", 11);
    };

    public static String getBinaryOperatorSymbol(Type type) {
        for (BinaryOperator operator : BINARY_OPERATORS) {
            if(operator.getType() == type) {
                return operator.getSymbol();
            }
        }
        return null;
    }

    public static int getBinaryOperatorPrecedence(Type type) {
        for (BinaryOperator operator : BINARY_OPERATORS) {
            if(operator.getType() == type) {
                return operator.getPrecedence();
            }
        }
        return 0;
    }

    // TODO: add various helpers methods to know what type of token is, example: IsCompareOperator()

    

}