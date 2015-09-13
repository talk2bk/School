
import java.util.*;

/**
 * 
 */
public class Name implements Expression {

    /**
     * 
     */
    public Name() {
    }

    /**
     * 
     */
    private String printName;

    /**
     * @param env 
     * @return
     */
    public Value execute(Map<Name, Value> env) {
        // TODO implement here
		Value result = env.get(this);
        if (result == null){throw new Exception("Undefined name: " + this)};
		return result;
    }

}