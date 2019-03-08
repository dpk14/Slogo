package operations.math_operations;

import operations.Operation;

public class SumOperation extends Operation {
    private final int SUM_NUM_ARGS = 2;

    public SumOperation() {
        super();
        setNumArgs(SUM_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        double sum = getArgIndex(0) + getArgIndex(1);
        return sum;
    }

}
