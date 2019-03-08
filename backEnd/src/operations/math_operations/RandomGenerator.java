package operations.math_operations;

import operations.Operation;

public class RandomGenerator extends Operation {
    private final int RANDOM_NUM_ARGS = 0;
    public RandomGenerator (){
        super();
        setNumArgs(RANDOM_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return Math.random() * getArgIndex(0);
    }
}
