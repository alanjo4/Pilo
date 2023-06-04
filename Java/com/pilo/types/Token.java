package com.pilo.types;

import com.pilo.core.Impl.UtilsImpl;



    public class Token {
        enum Type {
            /* START PROPERTY OR CALL */
            /* START Template literals */
            TEMPLATE_SP((byte) 0, "null", 0),
            TEMPLATE_TA((byte) 1, "null", 0),
            PERIOD((byte) 2, ".", 0),
            LBRACK((byte) 3, "[", 0),
            QUESTION_PERIOD((byte) 4, "?.", 0),
            LPAREN((byte) 5, "(", 0),
            RPAREN((byte) 6, ")", 0),
            RBRACK((byte) 7, "]", 0),
            LBRACE((byte) 8, "{", 0),
            COLON((byte) 9, ":", 0),
            ELLIPSIS((byte) 10, "...", 0),
            CONDITIONAL((byte) 11, "?", 3),
            SEMICOLON((byte) 12, ";", 0),
            RBRACE((byte) 13, "}", 0),
            EOS((byte) 14, "EOS", 0),
            ARROW((byte) 15, "=>", 0),
            INIT((byte) 16, "=init", 2),
            ASSIGN((byte) 17, "=", 2),
            COMMA((byte) 18, ",", 1),
            NOT((byte) 19, "!", 0),
            BIT_NOT((byte) 20, "~", 0),
            DELETE((byte) 21, "delete", 0),
            TYPEOF((byte) 22, "typeof", 0),
            VOID((byte) 23, "void", 0),
            INC((byte) 24, "++", 0),
            DEC((byte) 25, "--", 0),
            EQ((byte) 26, "==", 9),
            EQ_STRICT((byte) 27, "===", 9),
            NE((byte) 28, "!=", 9),
            NE_STRICT((byte) 29, "!==", 0),
            LT((byte) 30, "<", 0),
            GT((byte) 31, ">", 0),
            LTE((byte) 32, "<=", 0),
            GTE((byte) 33, ">=", 0),
            INSTANCEOF((byte) 34, "instanceof", 0),
            IN((byte) 35, "in", 0),
            BREAK((byte) 36, "break", 0),
            CASE((byte) 37, "case", 0),
            CATCH((byte) 38, "catch", 0),
            CONTINUE((byte) 39, "continue", 0),
            DEBUGGER((byte) 40, "debugger", 0),
            DEFAULT((byte) 41, "default", 0),
            DO((byte) 42, "do", 0),
            ELSE((byte) 43, "else", 0),
            FINALLY((byte) 44, "finally", 0),
            FOR((byte) 45, "for", 0),
            FUNCTION((byte) 46, "function", 0),
            IF((byte) 47, "if", 0),
            NEW((byte) 48, "new", 0),
            RETURN((byte) 49, "return", 0),
            SWITCH((byte) 50, "switch", 0),
            THROW((byte) 51, "throw", 0),
            TRY((byte) 52, "try", 0),
            VAR((byte) 53, "var", 0),
            WHILE((byte) 54, "while", 0),
            WITH((byte) 55, "with", 0),
            THIS((byte) 56, "this", 0),
            NULL_LITERAL((byte) 57, "null", 0),
            TRUE_LITERAL((byte) 58, "true", 0),
            FALSE_LITERAL((byte) 59, "false", 0),
            NUMBER((byte) 60, "number", 0),
            SMI((byte) 61, "smi", 0),
            BIGINT((byte) 62, "bigint", 0),
            STRING((byte) 63, "string", 0),
            SUPER((byte) 64, "super", 0),
            IDENTIFIER((byte) 65, "identifier", 0),
            GET((byte) 66, "get", 0),
            SET((byte) 67, "set", 0),
            ASYNC((byte) 68, "async", 0),
            AWAIT((byte) 69, "await", 0),
            YIELD((byte) 70, "yield", 0),
            LET((byte) 71, "let", 0),
            STATIC((byte) 72, "static", 0),
            FUTURE_STRICT_RESERVED_WORD((byte) 73, "future_strict_reserved_word", 0),
            ESCAPED_STRICT_RESERVED_WORD((byte) 74, "escaped_strict_reserved_word", 0),
            ENUM((byte) 75, "enum", 0),
            CLASS((byte) 76, "class", 0),
            CONST((byte) 77, "const", 0),
            EXPORT((byte) 78, "export", 0),
            IMPORT((byte) 79, "import", 0),
            PRIVATE_NAME((byte) 80, "private_name", 0),
            ILLEGAL((byte) 81, "illegal", 0),
            ESCAPED_KEYWORD((byte) 82, "escaped_keyword", 0),
            WHITESPACE((byte) 83, "whitespace", 0),
            UNINITIALIZED((byte) 84, "uninitialized", 0),
            REGEXP_LITERAL((byte) 85, "regexp_literal", 0),
            NULLISH((byte) 86, "??", 3),
            OR((byte) 87, "||", 4),
            AND((byte) 88, "&&", 5),
            BIT_OR((byte) 89, "|", 6),
            BIT_XOR((byte) 90, "^", 7),
            BIT_AND((byte) 91, "&", 8),
            SHL((byte) 92, "<<", 11),
            SAR((byte) 93, ">>", 11),
            MUL((byte) 94, "*", 11),
            DIV((byte) 95, "/", 11),
            MOD((byte) 96, "%", 11),
            EXP((byte) 97, "**", 11),
            ADD((byte) 98, "+", 11),
            SUB((byte) 99, "-", 11);
        
            private final String symbol;
            private final byte value;
            private final int precedence;
            private final int NUM_TOKENS = Type.values().length;
            private final short[] token_flags = new short[NUM_TOKENS];
            private final byte[][] precedence_ = new byte[2][NUM_TOKENS];
        
            Type(byte value, String symbol, int precedence) {
                this.value = value;
                this.symbol = symbol;
                this.precedence = precedence;
            }
        }

        // TODO: please implement strict and non-strict mode behavior

        public static Boolean isValidIdentifier(Type token, Boolean isGenerator, Boolean disallowAwait) {
            if(UtilsImpl.isInRange(token.value, Type.IDENTIFIER.value, Type.ASYNC.value)) return true;
            if(token.value == Type.AWAIT.value) return !disallowAwait;
            if(token.value == Type.YIELD.value) return !isGenerator;
            return true;
        }

        public static Boolean isCallable(Type token) {
            return UtilsImpl.isInRange(token.value, Type.SUPER.value, Type.ESCAPED_STRICT_RESERVED_WORD.value);
        }

        public static Boolean isAutoSemicolon(Type token) {
            return UtilsImpl.isInRange(token.value, Type.SEMICOLON.value, Type.EOS.value);
        }

        public static Boolean IsAnyIdentifier(Type token) {
            return UtilsImpl.isInRange(token.value, Type.IDENTIFIER.value, Type.ESCAPED_STRICT_RESERVED_WORD.value);
        }

        public static Boolean isStrictReservedWord(Type token) {
            return UtilsImpl.isInRange(token.value, Type.YIELD.value, Type.ESCAPED_STRICT_RESERVED_WORD.value);
        }

        public static Boolean isLiteral(Type token) {
            return UtilsImpl.isInRange(token.value, Type.NULL_LITERAL.value, Type.STRING.value);
        }
        // TODO: add various helpers methods to know what type of token is, example: IsCompareOperator()
    }




