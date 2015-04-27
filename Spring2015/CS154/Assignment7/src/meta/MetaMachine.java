package meta;

import java.lang.reflect.*;

public class MetaMachine {
    public String execute(String ... args) throws Exception {
        try{
        Class c = Class.forName(args[0]);
        Method methods[] = c.getMethods();
        Method meth = c.getMethod(args[1], String[].class);        
        Object blob = c.newInstance();
        String param[] = new String[args.length-2];
        for(int i = 2, j = 0; i < args.length; i++, j++){
           param[j] = args[i];
        }
        meth.invoke(blob,(Object) param);
        } catch(Exception e){
            System.out.println("not found");
        }
        
        return "";
    }
    
    
}
