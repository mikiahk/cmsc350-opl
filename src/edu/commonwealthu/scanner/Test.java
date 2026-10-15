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
                int i = 1
                float pi = 3.14 
                bool flag
                cmplx num
                det
                sqrt
                trans
                inv
                len
                rand
                mydet
                mat x = [0 -1 0, -1 2 -1,0 -1 0]
                int i = 10
                float f = 3.14
                bool b = true
                if(x==y)
                {
                    mat p = [208 145 15 58]
                }
                
                for(i=1...n)
                {
                    cmplx p = (0,1)
                }
                
                while(x>10)
                {
                }
                """;
        String equality = """
                if(x==10){
                    // do something
                }
                """;
        String notequal = """
                if(y!=5){
                    // do something
                }
                """;
        String lessequal = """
                if(x <= 20){
                    // do something
                }
                """;
        String greaterequal = """
                if(y >= 3){
                    /**
                        do something
                    */
                }
                """;
        String asign = """
                if(x = 1){
                    // parser should break this
                }
                """;
        String range = """
                for(i=1..end_range)
                {
                    cmplx p = (0,1)
                }
                """;

                // [int id = num]

        TokenScanner scanner = new TokenScanner(greaterequal);
        List<Token> tokens = scanner.scanTokens();

        for(Token t: tokens){
            System.out.println(t);
        }
    }
}
