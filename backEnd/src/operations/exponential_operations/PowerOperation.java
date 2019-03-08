package operations.exponential_operations;

import operations.Operation;

public class PowerOperation extends Operation {
    private final int POWER_NUM_ARGS = 2;
    public PowerOperation(){
        super();
        setNumArgs(POWER_NUM_ARGS);
    }
    @Override
    public double evaluate() {
        return Math.pow(getArgIndex(0), getArgIndex(1));
    }
}
