package mainpackage;

public class TrigonometricOperation extends Operation {
    public TrigonometricOperation (String myType, int numArgs, SystemStorage storage){
        super(myType, numArgs, storage);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("sin")){
            ret = Math.sin(myArgs.get(0));
        }
        else if (myType.equals("cos")){
            ret = Math.cos(myArgs.get(0));
        }
        else if (myType.equals("tan")){
            ret = Math.tan(myArgs.get(0));
        }
        else if (myType.equals("atan")){
            ret = Math.atan(myArgs.get(0));
        }
        else if (myType.equals("pi")){
            ret = Math.PI;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TrigonometricOperation(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
