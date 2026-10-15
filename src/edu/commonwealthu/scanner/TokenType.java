package edu.commonwealthu.scanner;

/**
 * All token types in language
 *
 * @author Mikiah Kline
 */
public enum TokenType {

    // Data types
    MAT,
    INT,
    FLOAT,
    BOOL,
    CMPLX,

    // Built-in Functions
    DET,
    SQRT,
    TRANS,
    INV,
    LEN,
    RAND,

    // Identifiers
    ID,

    // Values
    INTEGER,
    FLOATING_POINT,
    TRUE,
    FALSE,

    // Control Structures
    IF,
    FOR,
    WHILE,

    // Operators
    ASSIGN,
    EQUAL_EQUAL,
    NOT_EQUAL,
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    MINUS,
    PLUS,
    DIVIDE,
    MULTIPLY,
    RANGE,

    // Symbols
    LPAREN,
    RPAREN,
    LBRACKET,
    RBRACKET,
    LBRACE,
    RBRACE,
    COMMA,

    // End of input
    EOF
}
