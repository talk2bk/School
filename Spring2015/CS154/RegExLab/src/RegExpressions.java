import java.io.*;
import java.util.regex.*;

//lab 1, 2, 3
public class RegExpressions {

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
			String mathEquation = "(0|(\\+|-)?[1-9][0-9]*(\\.[0-9]+)?)~(\\+|\\-|\\/|\\*)?~(0|(\\+|-)?[1-9][0-9]*(\\.[0-9]+)?)";
			Pattern equationPattern = Pattern.compile(mathEquation);
			Matcher match = equationPattern.matcher(input);
			
			System.out.println(match.find());
			match.reset();
			}
		}catch(Exception e){
		 System.out.println("nope");	
		}
		
	}
		
	}
	
	

}