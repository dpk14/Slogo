package operations.multiple_turtle_operations;

import operations.SystemStorageOperation;

public class NumTurtles extends SystemStorageOperation {
    private final int NUM_TURTLES_NUM_ARGS = 0;

    public NumTurtles() {
        super();
        setNumArgs(NUM_TURTLES_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return getSystemStorage().getAnimals().keySet().size();
    }

}
