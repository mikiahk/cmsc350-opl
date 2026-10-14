package edu.commonwealthu.scanner;

/**
 * contract
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

    // Identifiers
    IDENTIFIER,

    // Values
    INTEGER,
    FLOATING_POINT,
    BOOLEAN,

    // Operators
    ASSIGN,

    // Symbols
    LPAREN,
    RPAREN,
    LBRACKET,
    RBRACKET,
    COMMA,

    //built in functions
    DET,
    SQRT,
    TRANS,
    INV,
    LEN,
    RAND,

    // End of input
    EOF
}
