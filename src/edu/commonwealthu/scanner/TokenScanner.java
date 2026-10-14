package edu.commonwealthu.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Scanner
 *
 * @author Mikiah Kline
 */
public class TokenScanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0; // where current token starts
    private int current = 0; // current position in source


    public TokenScanner(String source){
        this.source = source;
    }

    public List<Token> scanTokens(){
        while (!isAtEnd()){
            scanToken();
        }

        // end of source code
        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }

    private void scanToken(){
        // mark start of current token
        start = current;
        char c = advance();

        // ignore whitespaces
        if(Character.isWhitespace(c)){
            return;
        }

        if(Character.isLetter(c)){
            identifier();
            return;
        }

        if(Character.isDigit(c)){
            number();
            return;
        }

        switch(c){
            case '=' -> tokens.add(new Token(TokenType.ASSIGN, "="));
            case '(' -> tokens.add(new Token(TokenType.LPAREN, "("));
            case ')' -> tokens.add(new Token(TokenType.RPAREN, ")"));
            case '[' -> tokens.add(new Token(TokenType.LBRACKET, "["));
            case ']' -> tokens.add(new Token(TokenType.RBRACKET, "]"));
            case ',' -> tokens.add(new Token(TokenType.COMMA, ","));
            default -> System.out.println("Unexpected character: " + c);
        }
    }



    private void identifier(){
        // continue reading letters and numbers until eof
        while(!isAtEnd() && Character.isLetterOrDigit(peek()) || peek() == '_'){
            advance();
        }
        // get full substring of what we just read
        String text = source.substring(start, current);

        // determine what the text is
        TokenType type = switch (text) {
            case "mat" -> TokenType.MAT;
            case "int" -> TokenType.INT;
            case "float" -> TokenType.FLOAT;
            case "bool" -> TokenType.BOOL;
            case "cmplx" -> TokenType.CMPLX;

            case "true", "false" -> TokenType.BOOLEAN;

            case "det" -> TokenType.DET;
            case "sqrt" -> TokenType.SQRT;
            case "trans" -> TokenType.TRANS;
            case "inv" -> TokenType.INV;
            case "len" -> TokenType.LEN;
            case "rand" -> TokenType.RAND;

            default -> TokenType.IDENTIFIER;
        };

        tokens.add(new Token(type, text));
    }

    private void number(){
        // Read all before decimal
        while(!isAtEnd() && Character.isDigit(peek())){
            advance();
        }

        // Read decimal and advance
        if(!isAtEnd() && peek() == '.' && current + 1 < source.length() && Character.isDigit(source.charAt(current + 1))){
            advance(); // Read decimal

            while(!isAtEnd() && Character.isDigit(peek())){
                advance();
            }

            tokens.add(new Token(TokenType.FLOATING_POINT, source.substring(start, current)));
        }else{
            tokens.add(new Token(TokenType.INTEGER, source.substring(start, current)));
        }


    }

    // returns the current char and moves forward
    private char advance(){
        return source.charAt(current++);
    }

    // returns the current char without advancing
    private char peek(){
        if(isAtEnd()){
            return '\0'; //eof
        }

        return source.charAt(current);
    }

    // determines if end of file
    private boolean isAtEnd(){
        return current >= source.length();
    }
}
