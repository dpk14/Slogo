package operations.math_operations;

import operations.Operation;

public class QuotientOperation extends Operation {
    private final int QUOTIENT_NUM_ARGS = 2;
    public QuotientOperation(){
        super();
        setNumArgs(QUOTIENT_NUM_ARGS);
        setUnlimitedArgs();
    }
    @Override
    public double evaluate() {
        double quotient = getArgIndex(0) / getArgIndex(1);
        return quotient;
    }
}
