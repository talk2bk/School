import java.util.*;

class Statement {
    private String conclusion = new String();
    private List<String> conditions = new LinkedList<String>();
    
    Statement(String statement) {
       this.conclusion = statement;
    }
    Statement(String conclusion, String condition1, String condition2) {
        this.conclusion = conclusion;
        conditions.add(condition1);
        conditions.add(condition2);
    }
    
    public boolean matches(String goal){
        if(this.conclusion.equals(goal)){
            return true;
        }
        return false;
    }
    public boolean hasConditions(){
        if(this.conditions.isEmpty()){
            return false;
        }
        return true;
    }
    public List<String> addConditions(){
        return conditions;
    }

}

public class Engine {
	private List<Statement> kbase = new LinkedList<Statement>();
	private Scanner scanner = new Scanner(System.in);

	public boolean execute(List<String> goals) {
            //	   1. if no goals return true
            if(goals.isEmpty()){return true;}
            //	   2. traverse kbase looking for statements that match first goal
            //	   3. each time one is found recursively execute with goals2 = goals - 1st goal + tail
            //	   4. if true is ever returned, stop iteration and return true
            for(Statement statement : kbase){
                if(statement.matches(goals.get(0))){
                    if(statement.hasConditions()){
                        goals.addAll(statement.addConditions());
                    }
                    if(execute(goals.subList(1, goals.size())) == false){
                        String temp = goals.get(0);
                        goals.clear();
                        goals.add(temp);
                    }
                    else{return true;}
                    
                }
            }
            //	   5. if nothing works, return false
            return false;
	}

	public void repl() {
		List<String> goals = new LinkedList<String>();
		while(true) {
			System.out.print("?- ");
			String query = scanner.next();
			if (query.equals("quit")) {
				System.out.println("bye");
				break;
			}
			goals.clear();
			goals.add(query);
			boolean result = execute(goals);
			System.out.println(result);

		}
	}

    public void add(Statement statement) {
        kbase.add(statement);
    }

}
