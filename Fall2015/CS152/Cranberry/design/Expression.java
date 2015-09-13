
import java.util.*;

/**
 * 
 */
public interface Expression {

    /**
     * @param env 
     * @return
     */
    public Value execute(Map<Name, Value> env) throws Exception;

}