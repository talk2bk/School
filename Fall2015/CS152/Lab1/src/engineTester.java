
public class engineTester {
    	public static void main(String[] args) {
		Engine engine = new Engine();
//		engine.add(new Statement("homerIsMale"));
//		engine.add(new Statement("bartIsMale"));
//		engine.add(new Statement("homerIsParentOfBart"));
//		engine.add(new Statement("homerIsFatherOfBart","homerIsMale","homerIsParentOfBart" ));
                
                engine.add(new Statement("a","b","c"));
                engine.add(new Statement("a","d","e"));
                engine.add(new Statement("b"));
                engine.add(new Statement("d"));
                engine.add(new Statement("e"));
		engine.repl();
	}
}
