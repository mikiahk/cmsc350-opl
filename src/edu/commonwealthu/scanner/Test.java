package edu.commonwealthu.scanner;

import java.util.List;

/**
 * contract
 *
 * @author Mikiah Kline
 */
public class Test {
    public static void main(String[] args){
        String source = """
                mat x
                int i
                float pi
                bool flag
                cmplx num
                """;

        Scanner scanner = new Scanner( source );
        List<Token> tokens = scanner.scanTokens();

        for(Token t: tokens){
            System.out.println(t);
        }
    }
}
