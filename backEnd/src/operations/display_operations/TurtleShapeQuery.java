package operations.display_operations;

import operations.SystemStorageOperation;

public class TurtleShapeQuery extends SystemStorageOperation {
    private final int TURTLE_SHAPE_QUERY_NUM_ARGS = 0;

    public TurtleShapeQuery(){
        super();
        setNumArgs(TURTLE_SHAPE_QUERY_NUM_ARGS);
    }

    @Override
    public double evaluate(){
        String shape = getTurtle().getShape();
        int index = getMyDisplay().getAnimalShapesList().indexOf(shape);
        return (double) index;
    }
}
