package operations.display_operations;

import operations.Command;
import operations.SystemStorageOperation;

public class SetBackgroundCommand extends SystemStorageOperation implements Command {
    private final int SET_BACKGROUND_NUM_ARGS = 1;

    public SetBackgroundCommand(){
        super();
        setNumArgs(SET_BACKGROUND_NUM_ARGS);
    }

    @Override
    public double evaluate(){
        return getArgIndex(0);
    }

    @Override
    public void execute(){
        getSystemStorage().getDisplay().setBackgroundColor((int) getArgIndex(0));
    }
}
