/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CuTs
 */
public class Literal implements RegEx{
    //the literal used to check patterns
    private String token;
    
    Literal(String string) {
        token = string;
    }

    @Override
    public boolean matches(String s) {
        return (s == token);
    }
}
