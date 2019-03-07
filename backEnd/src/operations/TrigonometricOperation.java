package operations;

public class TrigonometricOperation extends Operation {
    public TrigonometricOperation (String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("sin")){
            ret = Math.sin(myArgs[0]);
        }
        else if (myType.equals("cos")){
            ret = Math.cos(myArgs[0]);
        }
        else if (myType.equals("tan")){
            ret = Math.tan(myArgs[0]);
        }
        else if (myType.equals("atan")){
            ret = Math.atan(myArgs[0]);
        }
        else if (myType.equals("pi")){
            ret = Math.PI;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TrigonometricOperation(myType, myNumArgs);
        return copy;
    }
}
