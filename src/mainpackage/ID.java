package mainpackage;

public class ID extends TurtleOperation {
    public ID(String operationType, int numArgs) {
        super(operationType, numArgs);
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public Operation copy() {
        Operation copy = new ID(myType, myNumArgs);
        ((ID) copy).setAnimal(myTurtle);
        return copy;
    }
}
