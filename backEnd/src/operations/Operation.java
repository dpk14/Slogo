package operations;

import mainpackage.SystemStorage;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    private int myNumArgs;
    private double[] myArgs;
    private double ret;
    protected String myType;

    public Operation(String type, int numArgs) {
        myNumArgs = numArgs;
        myType = type;
    }

    abstract public double evaluate();

    public int getNumArgs() {
        return myNumArgs;
    }

    public void setArgs(double[] args){
        myArgs = args;
    }

    abstract public Operation copy();

    protected void setReturnValue(double val){
        ret = val;
    }

    protected double getReturnValue(){
        return ret;
    }

    protected double getArgIndex(int index){
        return myArgs[index];
    }
}

