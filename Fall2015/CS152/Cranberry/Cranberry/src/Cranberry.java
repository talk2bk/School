import java.util.*;

interface Value { }

interface Expression {
	Value execute(Environment env) throws Exception;
}

abstract class Literal implements Value, Expression {
	public Value execute(Environment env) throws Exception {
		return this;
	}
}

//class bool and/or/not operations

class Bool extends Literal {
    private boolean value;
    
    public Bool(boolean value){
        this.value = value;
    }
    public String toString() {return "" + value;}
    public boolean getValue(){return value;}
    
    public Bool and(Bool other){
        return new Bool(value && other.value);
    }
    
    public Bool or(Bool other){
        return new Bool(value || other.value);
    }
    public Bool not(){
        return new Bool(!value);
    }
    
}

//class text encapsulates string (append())
class Text extends Literal{
    private String value;
    public Text(String value){
        this.value = value;
    }
    public String toString() {return value;}
    public Text concat(Text other){
        return new Text(value+other.value);
    }
}

class Number extends Literal {
	private Double value;
	public Number(Double value) {
		this.value = value;
	}
	public String toString() { return "" + value; }
	public Number add(Number other) {
		return new Number(value + other.value);
	}
        public Number sub(Number other){
                return new Number(value - other.value);
        }
        public Number mul(Number other){
                return new Number(value * other.value);
        }
        public Number div(Number other){
                return new Number(value / other.value);
        }
        public Number square(Number other){
                return new Number(other.value*other.value);
        }
	// etc.
}
/*number stuff
Literal x = new Number(42);
Value y = x.execute(e);
	env -> e
	this -> x
		this is being returned for execute
y == x? true.
    
*/

// Boole, Text (i.e., String)

class Name implements Expression {
	private String printName;
	public Name(String printName) {
		this.printName = printName;
	}
	public String toString() { return printName; }
	public int hashCode() { return printName.hashCode(); }
	public Value execute(Environment env) throws Exception {
		Value val = env.get(this);
		if (val == null) throw new Exception(printName + " undefined");
		return val;
	}
}

/*
 * An environment is a linked list of maps
 */
class Environment extends HashMap<Name, Value> {
	private Environment next;
	public Environment(Environment next) {
		this.next = next;
	}
	public Environment() { this(null); }
	public Environment getNext() { return next; }
	public Value get(Name name) {
		if (containsKey(name)) {
			return super.get(name);
		} else {
			if (next == null) {
				return null;
			} else {
				return next.get(name);
			}
		}
	}
}

class Declaration implements Expression {
	private Name name;
	private Expression body;
	public Declaration(Name name, Expression exp) {
		this.name = name;
		this.body = exp;
	}
	public Value execute(Environment env) throws Exception {
		env.put(name, body.execute(env));
		return null;
	}
}

class Function implements Value {
	private List<Name> parameters;
	private Expression body;
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		if (parameters.size() != arguments.size()) throw new Exception("parameter-argument mismatch");
		Environment tempEnv = new Environment(callingEnv);
		for(int i = 0; i < parameters.size(); i++) {
			tempEnv.put(parameters.get(i), arguments.get(i));
		}
		return body.execute(tempEnv);
	}
}

// example of a primitive or pre-defined function, also mul, sub, and, or, not
class AddFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Number result = new Number(0.0);
		for(Value val: arguments) {
			if (val instanceof Number) {
				Number num = (Number)val;
				result = result.add(num);
			} else {
				throw new Exception("only numbers can be added");
			}
		}
		return result;
	}
}
//done?
class SubFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Number result = new Number(0.0);
		for(Value val: arguments) {
			if (val instanceof Number) {
				Number num = (Number)val;
				result = result.sub(num);
			} else {
				throw new Exception("only numbers can be subtracted");
			}
		}
		return result;
	}
}
//done?
class MulFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Number result = new Number(1.0);
		for(Value val: arguments) {
			if (val instanceof Number) {
				Number num = (Number)val;
				result = result.mul(num);
			} else {
				throw new Exception("only numbers can be multiplied");
			}
		}
		return result;
	}
}//done?
class SquareFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Number result = new Number(0.0);
		for(Value val: arguments) {
			if (val instanceof Number) {
				Number num = (Number)val;
				result = result.square(num);
			} else {
				throw new Exception("only numbers can be squared");
			}
		}
		return result;
	}
}
//done?
class AndFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Bool result = new Bool(false);
		for(Value val: arguments) {
			if (val instanceof Bool) {
				Bool bool = (Bool)val;
				result = result.and(bool);
			} else {
				throw new Exception("only boolean values can be compared");
			}
		}
		return result;
	}
}
//done?
class OrFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Bool result = new Bool(false);
		for(Value val: arguments) {
			if (val instanceof Bool) {
				Bool bool = (Bool)val;
				result = result.or(bool);
			} else {
				throw new Exception("only boolean values can be compared");
			}
		}
		return result;
	}
}
//done?
class NotFunction extends Function {
        //???public AddFunction(){ super(null, null);}
	public Value apply(List<Value> arguments, Environment callingEnv) throws Exception {
		Bool result = new Bool(false);
		for(Value val: arguments) {
			if (val instanceof Bool) {
				Bool bool = (Bool)val;
				result = bool.not();
			} else {
				throw new Exception("only boolean values can be used");
			}
		}
		return result;
	}
}

// add(add(3, 4), pi) for example
class FunCall implements Expression {
        Name name;
        List<Expression> operands;
        public FunCall(Name theName, List<Expression> arguments){
            name = theName;
            operands = arguments;
        }
                
        
	public Value execute(Environment env) throws Exception {
            Function f = (Function) name.execute(env);
            List<Value> arguments = new ArrayList<Value>();
            for(Expression express : operands){
                arguments.add(express.execute(env));
            }
            //2.arguments is a list of value of operands
            //3. arguments from part 2) f.apply(arguments, env);
            return f.apply(arguments, env);
	}
}


class Console {

	private Scanner kbd = new Scanner(System.in);

	private Environment globalEnv = new Environment();

	private List<String> scan(String exp) {
		Scanner line = new Scanner(exp);
		List<String> tokens = new ArrayList<String>();
		while(line.hasNext()) {
			tokens.add(line.next());
		}
		return tokens;
	}

    // for now:
	private Expression parse(List<String> tokens) {
            //put in logic to determine waht sort of expression I need?? or just build an expression...
            
                
                return null;
	}

	public void repl() {
		List<String> tokens;
		while(true) {
			try {
				tokens = new ArrayList<String>();
				System.out.print("-> ");
				String input = kbd.nextLine();
				if (input.equals("quit")) {
					break;
				}
				Expression exp = parse(scan(input));
				Value val = exp.execute(globalEnv);
				System.out.println("result = " + val);
			} catch(Exception e) {
				System.out.println("Error, " + e.getMessage());
			}

		}
		System.out.println("bye");
	}
}

public class Cranberry {

	public static void main(String args[]) {
		//Console console = new Console();
		//console.repl();
		try {
			Environment globalEnv = new Environment();
                        Name add = new Name("add");
			globalEnv.put(add, new AddFunction());
			Name pi = new Name("pi");
			Number num = new Number(3.14);
			Declaration dec = new Declaration(pi, num);
			dec.execute(globalEnv);
			System.out.println("pi = " + pi.execute(globalEnv));
			System.out.println("num = " + num.execute(globalEnv));
//			Name x = new Name("x");
//			System.out.println("x = " + x.execute(globalEnv));
                        
                        Number n3 = new Number(3.0);
                        Number n4 = new Number(4.0);
                        List<Expression> operands1 = new LinkedList<Expression>();
                        operands1.add(n3);
                        operands1.add(n4);
                        FunCall add1 = new FunCall(add,operands1);
                        //add(3,4)
                        List<Expression> operands2 = new LinkedList<Expression>();
                        operands2.add(add1);
                        operands2.add(pi);
                        
                        Name add2 = new Name("add2");
			globalEnv.put(add2, new AddFunction());
                        System.out.println("add(add(3,4),pi): "+ new FunCall(add2, operands2).execute(globalEnv).toString());
                        
                        Name x = new Name("x");
                        Declaration dec2 = new Declaration(x,new Number(5.0)); 
                        dec2.execute(globalEnv);
                        System.out.println("x = "+ globalEnv.get(x));
                        //x has been put into hte env
                        Name mul = new Name("mul");
                        globalEnv.put(mul, new MulFunction());
                        Number three = new Number(3.0);
                        //create 3 for mul
                        List<Expression> operandsMul = new LinkedList<Expression>();
                        //operands for mul
                        operandsMul.add(x);
                        operandsMul.add(three);
                        
                        FunCall mul1 = new FunCall(mul, operandsMul);
                        System.out.println("mul(x,3) = " + mul1.execute(globalEnv).toString());
                        
                        Name square = new Name("square");
                        globalEnv.put(square, new SquareFunction());
                        List<Expression> operandsSquare = new LinkedList<Expression>();
                        operandsSquare.add(mul1);
                        
                        System.out.println("square(mul(x,3)) = " + new FunCall(square,operandsSquare).execute(globalEnv).toString());
                        
                        
                        
                        
                        
                //add(add(3,4),pi); should return 10.14 
		} catch (Exception e) {
			System.err.println(e);
		}

	}


}
