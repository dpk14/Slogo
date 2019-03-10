package operations.display_operations;

import general.Animal;
import operations.SystemStorageOperation;

abstract public class PenOperation extends SystemStorageOperation {
    private final int SET_PEN_NUM_ARGS = 1;

    public PenOperation(){
        super();
        setNumArgs(SET_PEN_NUM_ARGS);
    }

    @Override
    abstract public double evaluate();

}
