package operations;

import general.SystemStorage;

abstract public class SystemStorageOperation extends Operation {
    private final int NUM_ARGS = 0;
    private SystemStorage myStorage;

    public SystemStorageOperation(){
        super();
        setNumArgs(NUM_ARGS);
    }
    @Override
    abstract public double evaluate();

    protected SystemStorage getSystemStorage(){
        return myStorage;
    }

    public void setSystemStorage(SystemStorage storage){
        myStorage = storage;
    }

}
