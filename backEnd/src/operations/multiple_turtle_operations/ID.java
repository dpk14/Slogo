package operations.multiple_turtle_operations;

import operations.TurtleOperation;

public class ID extends TurtleOperation {
    private int ID_NUM_ARGS = 0;
    public ID() {
        super();
        setNumArgs(ID_NUM_ARGS);
    }

    @Override
    public double evaluate() {
        return Double.parseDouble(getTurtle().getAnimalID());
    }

}
