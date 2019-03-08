package operations.trigonometric_operations;

import operations.Operation;

public class CosOperation extends Operation {
    private final int COS_NUM_ARGS = 1;

    public CosOperation(){
        super();
        setNumArgs(COS_NUM_ARGS);
    }
    @Override
    public double evaluate() {
        return Math.cos(getArgIndex(0));
    }
}
