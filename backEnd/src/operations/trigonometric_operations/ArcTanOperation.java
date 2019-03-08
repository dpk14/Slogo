package operations.trigonometric_operations;

import operations.Operation;

public class ArcTanOperation extends Operation {
    private final int ARCTAN_NUM_ARGS = 1;

    public ArcTanOperation(){
        super();
        setNumArgs(ARCTAN_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return Math.atan(getArgIndex(0));
    }
}
