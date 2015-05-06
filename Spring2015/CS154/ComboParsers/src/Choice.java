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
public class Choice extends Result{
    protected Result choice;
    
    @Override
    public String toString(){
        return choice.toString();
    }
}
