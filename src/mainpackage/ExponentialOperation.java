package mainpackage;

public class ExponentialOperation extends Operation {
    public ExponentialOperation (String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("pow")){
            setReturnValue(Math.pow(getArgIndex(0), getArgIndex(1)));
        }
        else if (myType.equals("log")){
            setReturnValue(Math.log(getArgIndex(0)));
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new ExponentialOperation(myType, getNumArgs());
        return copy;
    }
}
