package Operations;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation{
    String myCommandType;
    double[] myArguments;
    int myNumArgs;
    Command(String commandType, int numArgs){
        myCommandType=commandType;
        myNumArgs=numArgs;
    }

    @Override
    public int getNumArgs(){
        return myNumArgs;
    }

    @Override
    public Operation copy(){
        return this;
    }

    @Override
    abstract public double execute();

    public void setArgs(double[] arguments){
        myArguments=arguments;
    }

}
