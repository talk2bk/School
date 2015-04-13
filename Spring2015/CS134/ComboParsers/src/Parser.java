

import java.util.function.*;


public class Parser implements UnaryOperator<Result>{
    protected UnaryOperator<Result> parser;
    
    void setParser(UnaryOperator<Result> unaryOperator) {
        parser = unaryOperator;
    }

    @Override
    public Result apply(Result r) {
        return parser.apply(r);
    }
}
