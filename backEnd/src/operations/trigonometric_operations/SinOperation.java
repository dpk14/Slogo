package operations.trigonometric_operations;

import operations.Operation;

public class SinOperation extends Operation {
    private final int SIN_NUM_ARGS = 1;

    public SinOperation(){
        super();
        setNumArgs(SIN_NUM_ARGS);
    }
    @Override
    public double evaluate() {
        return Math.sin(getArgIndex(0));
    }
}
