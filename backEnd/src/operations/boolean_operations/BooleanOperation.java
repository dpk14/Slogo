package operations.boolean_operations;

import operations.Operation;

abstract public class BooleanOperation extends Operation {
    private final int BOOLEAN_NUM_ARGS = 2;
    private final double TRUE_VAL = 1;
    private final double FALSE_VAL = 0;

    public BooleanOperation(){
        super();
        setNumArgs(BOOLEAN_NUM_ARGS);
    }

    protected double getReturnValue(boolean ret){
        if (ret){
            return TRUE_VAL;
        }
        else {
            return FALSE_VAL;
        }
    }
    @Override
    abstract public double evaluate();
}
