package operations.exponential_operations;

import operations.Operation;

public class LogOperation extends Operation {
    private final int LOG_NUM_ARGS = 1;
    public LogOperation(){
        super();
        setNumArgs(LOG_NUM_ARGS);
    }
    @Override
    public double evaluate() {
        return Math.log(getArgIndex(0));

    }
}
