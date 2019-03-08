package operations.BooleanOperations;

import operations.BooleanOperations.BooleanOperation;

public class NotOperation extends BooleanOperation {
    private final int NOT_NUM_ARGS = 1;
    public NotOperation(){
        super();
        setNumArgs(NOT_NUM_ARGS);
    }
    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) == 0;
        return getReturnValue(ret);
    }
}
