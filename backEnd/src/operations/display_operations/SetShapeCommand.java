package operations.display_operations;

import general.DisplayModel;
import operations.Command;
import operations.SystemStorageOperation;

public class SetShapeCommand extends SystemStorageOperation implements Command {
    private final int SET_SHAPE_NUM_ARGS = 1;
    private DisplayModel myDisplay;

    public SetShapeCommand(){
        super();
        setNumArgs(SET_SHAPE_NUM_ARGS);
        myDisplay = getSystemStorage().getDisplay();
    }

    @Override
    public void execute() {
        int index = (int) getArgIndex(0);
        String shapeFile = myDisplay.getAnimalShapesList().get(index);
        getTurtle().setImage(shapeFile);
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
