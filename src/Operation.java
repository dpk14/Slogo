import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    String myType;
    List<String> myArgs;
    SystemStorage mySystemStorage;

    public Operation(String operationType, List arugments, SystemStorage systemStorage){
        myType = operationType;
        myArgs = arugments;
        mySystemStorage = systemStorage;
    }

    abstract public double execute();
    public int getNumArgs(){
        return myArgs.size();
    }
    abstract public Operation copy();
    protected double parseString(String argument){
        if (argument.contains(":")){
            int variableIndex = argument.indexOf(":") + 1;
            double val = mySystemStorage.getVariableValue(argument.substring(variableIndex));
            return val;
        }
        else {
            return 0; //TODO: catch NullPointerException
        }
    }
}
