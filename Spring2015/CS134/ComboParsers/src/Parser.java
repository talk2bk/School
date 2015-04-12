

import java.util.function.*;


public class Parser implements UnaryOperator<Result>{
    protected UnaryOperator<Result> parser;
    
    void setParser(UnaryOperator<Result> alt) {
        parser = alt;
    }

    @Override
    public Result apply(Result t) {
        return parser.apply(t);
    }
}
