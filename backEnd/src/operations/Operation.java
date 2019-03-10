package operations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    private int myNumArgs;
    private double[] myArgs;
    private boolean hasUnlimitedArgs = false;

    public Operation() {
    }

    protected void setNumArgs(int num){
        myNumArgs = num;
    }
    abstract public double evaluate();

    public int getNumArgs() {
        return myNumArgs;
    }

    public void setArgs(double[] args){
        myArgs = args;
    }

    public boolean hasUnlimitedArgs(){
        return hasUnlimitedArgs;
    }

    protected void setUnlimitedArgs(){
        hasUnlimitedArgs = true;
    }
    
    protected double getArgIndex(int index){
        return myArgs[index];
    }

}

