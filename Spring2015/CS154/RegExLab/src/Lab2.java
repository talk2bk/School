import java.io.*;
import java.util.regex.*;


//lab 2
public class Lab2 {
        
        public static void main(String[] args) throws Exception {
		//lab1
		Lab2.interpreter();
	}
        
	public static void interpreter()
	{
	boolean reading = true;
	while(reading){
		String input = "";
		try{
			//Console console = System.console();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("-> ");
			input = reader.readLine();
			
			if(input.equalsIgnoreCase("quit")){
				System.out.println("Bye Felicia");
				reading = false;
			}
			else{
			String mathEquation = "([-+]?[0-9]*\\.?[0-9]+)[ +]?(\\+|\\-|\\/|\\*)+[ +]?([-+]?[0-9]*\\.?[0-9]+)";
			String numberString = "([-+]?[0-9]*\\.?[0-9]+)";
			String operatorString = "[\\+\\-\\/\\*]+";
			
			Matcher equationMatch = (Pattern.compile(mathEquation)).matcher(input);
			Matcher numberMatch = (Pattern.compile(numberString)).matcher(input);
			Matcher operatorMatch = (Pattern.compile(operatorString)).matcher(input);
                        //System.out.println(operatorMatch.find());
                        //while(operatorMatch.find()){System.out.println(o.group());}

                        
                        
			if(!operatorMatch.find()){throw new Exception("invalid operator breh");}
                        else if(!equationMatch.find()){throw new Exception("invalid number breh");}
			
			else{
                                
				float firstNumber = Float.parseFloat(equationMatch.group(1));
				float secondNumber = Float.parseFloat(equationMatch.group(3));
				float result = 0;
				switch(operatorMatch.group()){
				case "+":
					result = firstNumber + secondNumber;
					break;
					
				case "-":
					result = firstNumber - secondNumber;
					break;
					
				case "*":
					result = firstNumber * secondNumber;
					break;
					
				case "/":
					result = firstNumber / secondNumber;
					break;
				}
			System.out.println(result);
				
			}
                        

		}
	}catch(Exception e){
		 System.out.println(e.getMessage());	
		}
		
	}
		
	}
	
	

}
