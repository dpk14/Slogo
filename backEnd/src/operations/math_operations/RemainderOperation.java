package operations.math_operations;

import operations.Operation;

public class RemainderOperation extends Operation {
    private final int REMAINDER_NUM_ARGS = 2;
    public RemainderOperation(){
        super();
        setNumArgs(REMAINDER_NUM_ARGS);
        setUnlimitedArgs();
    }
    @Override
    public double evaluate() {
        double remainder = getArgIndex(0) % getArgIndex(1);
        return remainder;
    }
}
