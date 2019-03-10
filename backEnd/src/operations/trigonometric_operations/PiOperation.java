package operations.trigonometric_operations;

import operations.Operation;

public class PiOperation extends Operation {
    private final int PI_NUM_ARGS = 0;

    public PiOperation(){
        super();
        setNumArgs(PI_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return Math.PI;
    }
}
