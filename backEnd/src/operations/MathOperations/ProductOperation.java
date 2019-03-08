package operations.MathOperations;

import operations.Operation;

public class ProductOperation extends Operation {
    private final int PRODUCT_NUM_ARGS = 2;
    public ProductOperation(){
        super();
        setNumArgs(PRODUCT_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        double product = getArgIndex(0) * getArgIndex(1);
        return product;
    }
}
