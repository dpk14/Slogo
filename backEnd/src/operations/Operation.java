package operations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    private int myNumArgs;
    private double[] myArgs;

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

    public Operation copy(){
        try{
            Constructor constructor = this.getClass().getConstructor();
            Operation copy = (Operation) constructor.newInstance();
            return copy;
        }
        catch (NoSuchMethodException nsm){
            System.out.println("no such method");
        }
        catch (InstantiationException ie){

        }
        catch (IllegalAccessException iae){

        }
        catch (InvocationTargetException ite){

        }
        return null;
    }

    protected double getArgIndex(int index){
        return myArgs[index];
    }
}

