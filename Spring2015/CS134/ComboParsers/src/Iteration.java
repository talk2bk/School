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
public class Iteration extends Result{
    protected ArrayList<Result> kids  = new ArrayList<Result>();
    
    @Override
    public String toString(){
        return kids.toString();
    }
}
