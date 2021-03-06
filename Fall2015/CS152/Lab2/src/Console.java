import java.util.*;

class Command {

	private String opcode;
	private List<String> operands;

	public Command(String opcode, List<String> operands) {
			this.opcode = opcode;
			this.operands = operands;
	}

	private String add(List<String> args, Map<String, Integer> env)  throws Exception {
		Integer result = 0;
		Integer  x = 0;
		for(String arg: args) {
			try {
				x = Integer.parseInt(arg);
			} catch(Exception e) {
				x = env.get(arg);
				if (x == null) throw new Exception("undefined variable: " + arg);
			}
			result += x;
		}
		return "" + result;
	}
        //done?
	private String mul(List<String> args, Map<String, Integer> env) throws Exception {
            
            Integer result = 1;
            Integer x = 0;
            if(args.isEmpty()){return "1";}
            for(String arg: args){
                try{
                        x = Integer.parseInt(arg);
                }   catch(Exception e) {
                    x = env.get(arg);
                    if(x == null) throw new Exception("undefined variable: " + arg);
                }
                result *= x;
                
            }
            return "" + result;
        }
        //do this
	private String load(List<String> args, Map<String, Integer> env) throws Exception {
            
            try{
                env.put(args.get(0), Integer.parseInt(args.get(1)));
            }   catch(Exception e){
                if(env.get(args.get(0))== null) throw new Exception("Could not load variable: " + args.get(0));
            }
            return "done";
        }
        //do this
	public String execute(Map<String, Integer> env) throws Exception {
            if(opcode.equals("add")){return add(operands,env);}
            else if(opcode.equals("mul")){return mul(operands,env);}
            else if(opcode.equals("load")){return load(operands,env);}
            
            return "unrecognized opcode: "+ opcode;
        }
}

public class Console {
	private Scanner kbd = new Scanner(System.in);
	private Map<String, Integer> env = new HashMap<String, Integer>();
        
        //do this
	private List<String> scan(String exp) {
            List<String> result = new ArrayList<String>();
            for(String string : exp.split(" ")){
                result.add(string);
            }
            return result;
            
        }
        
        //do this
	private Command parse(List<String> tokens) {
            return new Command(tokens.get(0), tokens.subList(1, tokens.size()));
        }

	// read-execute-print loop
	public void repl() {
		while(true) {
			try {
				System.out.print("-> ");
				String input = kbd.nextLine();
				if (input.equals("quit")) break;
				Command cmmd = parse(scan(input));
				System.out.println(cmmd.execute(env));
			} catch(Exception e) {
				System.out.println("Error, " + e.getMessage());
			}
		}
		System.out.println("bye");
	}

	public static void main(String args[]) {
		Console console = new Console();
		console.repl();
	}
}
