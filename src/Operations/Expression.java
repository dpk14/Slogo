package Operations;

import java.util.ArrayList;

public class Expression extends Operation{
    String myExpressionType;
    ArrayList<Double> myArguments= new ArrayList<>();
    int myNumberOfArguments;
    
    Expression(String commandType, int numberOfArguments){
        myExpressionType=commandType;
        myNumberOfArguments=numberOfArguments;
    }

    public int getNumberOfArguments(){
        return myNumberOfArguments;
    }

    public ArrayList<Double> getArguments(){
        return myArguments;
    }

    @Override
    public double execute() {
        return 0;
    }

    @Override
    public int getNumArgs() {
        return 0;
    }

    @Override
    public Operation copy() {
        return null;
    }
}
