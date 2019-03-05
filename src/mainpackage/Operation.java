package mainpackage;

import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    protected String myType;
    protected int myNumArgs;
    protected List<String> myArgs;
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

    public void setArgs(List<String> args){
        myArgs = args;
    }

    abstract public Operation copy();

    protected double parseString(String argument) {
        if (argument != null){
            if (argument.contains(":")) {
                int variableIndex = argument.indexOf(":") + 1; //TODO: use regular expression to check for variable
                double val = mySystemStorage.getVariableValue(argument.substring(variableIndex)); //TODO: catch NullPointerException
                return val;
            } else {
                return Double.parseDouble(argument);
            }
        }
        throw new NullPointerException(); //TODO: catch NullPointerException
    }

    public void storeCommand(){
        if (this instanceof TurtleCommand) mySystemStorage.addToHistory((TurtleCommand) this);
        else; //throw error
    }

    public double getReturnValue(){
        return ret;
    }
}

