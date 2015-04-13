/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author CuTs
 */
public class Concatenation extends Result{
    protected Result choice1;
    protected Result choice2;
    
    @Override
    public String toString(){
        return choice1.toString()+ " + " +choice2.toString();
    }
}

