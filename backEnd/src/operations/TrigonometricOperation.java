package operations;

public class TrigonometricOperation extends Operation {
    public TrigonometricOperation (String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("sin")){
            setReturnValue(Math.sin(getArgIndex(0)));
        }
        else if (myType.equals("cos")){
            setReturnValue(Math.cos(getArgIndex(0)));
        }
        else if (myType.equals("tan")){
            setReturnValue(Math.tan(getArgIndex(0)));
        }
        else if (myType.equals("atan")){
            setReturnValue(Math.atan(getArgIndex(0)));
        }
        else if (myType.equals("pi")){
            setReturnValue(Math.PI);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new TrigonometricOperation(myType, getNumArgs());
        return copy;
    }
}
