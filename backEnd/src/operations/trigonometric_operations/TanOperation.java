package operations.trigonometric_operations;

import operations.Operation;

public class TanOperation extends Operation {
    private final int TAN_NUM_ARGS = 1;

    public TanOperation(){
        super();
        setNumArgs(TAN_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return Math.tan(getArgIndex(0));
    }

}
