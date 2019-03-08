package operations.MathOperations;

import operations.Operation;

public class DifferenceOperation extends Operation {
    private final int DIFFERENCE_NUM_ARGS = 2;
    public DifferenceOperation() {
        super();
        setNumArgs(DIFFERENCE_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        double product = getArgIndex(0) * getArgIndex(1);
        return product;
    }
}
