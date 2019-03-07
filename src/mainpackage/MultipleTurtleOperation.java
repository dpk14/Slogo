package mainpackage;

public class MultipleTurtleOperation extends Operation {

    public MultipleTurtleOperation(String operationType, int numArgs, SystemStorage systemStorage) {
        super(operationType, numArgs, systemStorage);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("turtles")){
            ret = mySystemStorage.numTurtlesCreated();
        }
        else if (myType.equals("id")){
            //TODO: make this into a TurtleCommand, return ID of turtle
        }
        return ret;
    }

    @Override
    public Operation copy() {
        return new MultipleTurtleOperation(myType, myNumArgs, mySystemStorage);
    }
}
