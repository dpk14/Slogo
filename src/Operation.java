import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    protected String myType;
    protected List<String> myArgs;
    protected SystemStorage mySystemStorage;

    public Operation(String operationType, List arugments, SystemStorage systemStorage) {
        myType = operationType;
        myArgs = arugments;
        mySystemStorage = systemStorage;
    }

    abstract public double execute();

    public int getNumArgs() {
        return myArgs.size();
    }

    public List<String> getMyArgs(){
        return myArgs;
    }

    abstract public Operation copy();

    protected double parseString(String argument) {
        if (argument != null){
            if (argument.contains(":")) {
                int variableIndex = argument.indexOf(":") + 1;
                double val = mySystemStorage.getVariableValue(argument.substring(variableIndex)); //TODO: catch NullPointerException
                return val;
            } else {
                return Double.parseDouble(argument);
            }
        }
        throw new NullPointerException(); //TODO: catch NullPointerException
    }
}

