package mainpackage;

public class NumTurtles extends Operation {

    public NumTurtles(String operationType, int numArgs) {
        super(operationType, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("turtles")){
            //setReturnValue(mySystemStorage.numTurtlesCreated()); TODO: create SystemStorage operation superclass
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        return new NumTurtles(myType, getNumArgs());
    }
}
