package operations.math_operations;

import operations.Operation;

public class DifferenceOperation extends Operation {
    private final int DIFFERENCE_NUM_ARGS = 2;
    boolean UnlimitedArgs;

    public DifferenceOperation() {
        super();
        setUnlimitedArgs(true);
    }

    @Override
    public double evaluate() {
        double product = getArgIndex(0) * getArgIndex(1);
        return product;
    }
}
