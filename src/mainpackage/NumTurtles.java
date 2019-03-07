package mainpackage;

public class NumTurtles extends Operation {

    public NumTurtles(String operationType, int numArgs) {
        super(operationType, numArgs);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("turtles")){
            ret = mySystemStorage.numTurtlesCreated();
        }
        return ret;
    }

    @Override
    public Operation copy() {
        return new NumTurtles(myType, myNumArgs);
    }
}
