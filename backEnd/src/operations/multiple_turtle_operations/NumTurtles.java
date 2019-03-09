package operations.multiple_turtle_operations;

import operations.SystemStorageOperation;

public class NumTurtles extends SystemStorageOperation {

    public NumTurtles() {
        super();
    }

    @Override
    public double evaluate() {
        return getSystemStorage().getAnimals().keySet().size();
    }

}
