package edu.commonwealthu.scanner;

/**
 * Token arrays
 *
 * @author Mikiah Kline
 */
public class Token {
    private final TokenType type;
    private final String source;

    public Token(TokenType type, String tape){
        this.type = type;
        this.source = tape;
    }

    public TokenType getType(){return type;}

    public String getSource(){return source;}

    @Override
    public String toString(){return type + " ";}
}
