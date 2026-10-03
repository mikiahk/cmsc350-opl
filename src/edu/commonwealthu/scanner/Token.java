package edu.commonwealthu.scanner;

/**
 * contract
 *
 * @author Mikiah Kline
 */
public class Token {
    private final TokenType type;
    private final String tape;

    public Token(TokenType type, String tape){
        this.type = type;
        this.tape = tape;
    }

    public TokenType getType(){return type;}

    public String getTape(){return tape;}

    @Override
    public String toString(){return type + " : " + tape;}
}
