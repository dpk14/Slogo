package operations.BooleanOperations;

import operations.BooleanOperations.BooleanOperation;

public class NotEqualTo extends BooleanOperation {

    public NotEqualTo(){
        super();
    }
    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) != getArgIndex(1);
        return getReturnValue(ret);
    }
}
