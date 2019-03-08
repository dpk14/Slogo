package operations.BooleanOperations;

import operations.BooleanOperations.BooleanOperation;

public class EqualTo extends BooleanOperation {

    public EqualTo(){
        super();
    }
    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) == getArgIndex(1);
        return getReturnValue(ret);
    }
}
