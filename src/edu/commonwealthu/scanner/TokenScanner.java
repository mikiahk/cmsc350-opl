package edu.commonwealthu.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * contract
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
    }

    private void identifier(){
        // continue reading letters and numbers until eof
        while(!isAtEnd() && Character.isLetterOrDigit(peek())){
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
            default -> TokenType.IDENTIFIER;
        };

        tokens.add(new Token(type, text));
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
