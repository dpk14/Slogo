package operations.MathOperations;

import operations.Operation;

public class MinusOperation extends Operation {
    private final int MINUS_NUM_ARGS = 1;
    private final int MINUS_MULTIPLIER = -1;
    public MinusOperation(){
        super();
        setNumArgs(MINUS_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        double minus = getArgIndex(0) * MINUS_MULTIPLIER;
        return minus;
    }
}
