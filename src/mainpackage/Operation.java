package mainpackage;

import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    protected String myType;
    protected int myNumArgs;
    protected double[] myArgs;
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

    abstract public Operation copy();

}

