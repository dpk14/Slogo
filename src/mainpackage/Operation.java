package mainpackage;

import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    protected String myType;
    protected int myNumArgs;
    protected List<Double> myArgs;
    protected SystemStorage mySystemStorage;
    protected double ret;

    public Operation(String operationType, int numArgs, SystemStorage systemStorage) {
        myType = operationType;
        myNumArgs = numArgs;
        mySystemStorage = systemStorage;
    }

    abstract public double evaluate();

    public int getNumArgs() {
        return myNumArgs;
    }

    public void setArgs(List<Double> args){
        myArgs = args;
    }

    abstract public Operation copy();

    /*protected double parseString(String argument) {
        if (argument != null){
            if (argument.contains(":")) {
                int variableIndex = argument.indexOf(":") + 1;
                double val = mySystemStorage.getVariableValue(argument.substring(variableIndex));
                return val;
            } else {
                return Double.parseDouble(argument);
            }
        }
        throw new NullPointerException();
    */

    public void storeCommand(){
        if (this instanceof TurtleCommand) mySystemStorage.addToHistory((TurtleCommand) this);
        else; //throw error
    }

    public double getReturnValue(){
        return ret;
    }
}

