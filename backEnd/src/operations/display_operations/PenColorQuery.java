package operations.display_operations;

import operations.SystemStorageOperation;

public class PenColorQuery extends SystemStorageOperation {
    private final int PEN_COLOR_QUERY_NUM_ARGS = 0;

    public PenColorQuery(){
        super();
        setNumArgs(PEN_COLOR_QUERY_NUM_ARGS);
    }

    @Override
    public double evaluate(){
        String color = getTurtle().getColor();
        int index = getMyDisplay().getColorsList().indexOf(color);
        return (double) index;
    }
}
