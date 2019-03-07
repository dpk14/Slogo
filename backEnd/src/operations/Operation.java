package operations;

import mainpackage.SystemStorage;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    protected String myType;
    protected int myNumArgs;
    protected double[] myArgs;
    protected SystemStorage mySystemStorage;
    protected double ret;

    public Operation(String operationType, int numArgs) {
        myType = operationType;
        myNumArgs = numArgs;
    }

    abstract public double evaluate();

    public int getNumArgs() {
        return myNumArgs;
    }

    public void setArgs(double[] args){
        myArgs = args;
    }

    public void setSystemStorage(SystemStorage storage){
        mySystemStorage = storage;
    }

    abstract public Operation copy();

    public void storeCommand(){
        if (this instanceof Command) mySystemStorage.addToHistory((Command) this);
        else; //TODO: throw error
    }
}

