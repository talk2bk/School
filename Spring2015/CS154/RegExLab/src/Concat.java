/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CuTs
 */
public class Concat implements RegEx{
    // gattai r1 and r2
    private RegEx first;
    private RegEx second;
    
    Concat(RegEx r1, RegEx r2) {
        first = r1;
        second = r2;
    }

    @Override
    public boolean matches(String s) {
        String currentString = first.toString();
        String secondString = second.toString();
        boolean stringChanged = false;
        boolean result = false;
        for(int i = 0; i+currentString.length() < s.length(); i++){
            String testString = s.substring(i, i+currentString.length());
            if(testString.equalsIgnoreCase(currentString)){result = true;}
            else if(!stringChanged){currentString = secondString;i--;}
            else {
                return false;
            }
        }
        
        
        return result;
    }
    
}
