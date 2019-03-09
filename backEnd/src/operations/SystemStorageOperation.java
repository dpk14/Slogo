package operations;

import general.DisplayModel;
import general.SystemStorage;

abstract public class SystemStorageOperation extends TurtleOperation {
    private final int NUM_ARGS = 0;
    private SystemStorage myStorage;
    private DisplayModel myDisplay;

    public SystemStorageOperation(){
        super();
        setNumArgs(NUM_ARGS);
    }

    @Override
    abstract public double evaluate();

    protected SystemStorage getSystemStorage(){
        return myStorage;
    }

    protected DisplayModel getMyDisplay(){
        return myDisplay;
    }

    public void setSystemStorage(SystemStorage storage){
        myStorage = storage;
        myDisplay = myStorage.getDisplay();
    }
}
