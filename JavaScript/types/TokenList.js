// Yes, I will defenitly use typescript in this project.
class TokenList {
    static Type = {
      /* START Template literals */
      TEMPLATE_SPAN: { category: 'template', symbol: null, precedence: 0 },
      TEMPLATE_TAIL: { category: 'template', symbol: null, precedence: 0 },
      /* END Template literals */
      /* Punctuactors */
      /* START Property */
      PERIOD: { category: 'punctuactor', symbol: '.', precedence: 0 },
      LBRACK: { category: 'punctuactor', symbol: '[', precedence: 0 },
      /* END Property */
      QUESTION_PERIOD: { category: 'punctuactor', symbol: '?.', precedence: 0 },
      LPAREN: { category: 'punctuactor', symbol: '(', precedence: 0 },
      RPAREN: { category: 'punctuactor', symbol: ')', precedence: 0 },
      /* TO DO ADD THE REST OF TOKEN LIST, THERE ARE MULTIPLE OF THERE */
    };
  
    static BinaryOperator = class {
      constructor(type, symbol, precedence) {
        this.type = type;
        this.symbol = symbol;
        this.precedence = precedence;
      }
  
      getType() {
        return this.type;
      }
  
      getSymbol() {
        return this.symbol;
      }
  
      getPrecedence() {
        return this.precedence;
      }
    };
  
    // TODO: Add binary operators: static BINARY_OPERATORS = {};
  
    // TODO: Add method to get symbol from BinaryOperator
  
    // TODO: Add method to get precedence from BinaryOperator
  }