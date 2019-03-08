package mainpackage;

public class BooleanOperator extends Operation {
    public BooleanOperator(String type, int numArgs){
        super(type, numArgs);
    }

    @Override
    public double evaluate() {
        setReturnValue(0);
        boolean booleanRet = false;
        if (myType.equals("and")){
            booleanRet = (getArgIndex(0) != 0) && (getArgIndex(1) != 0);
        }
        else if (myType.equals("or")){
            booleanRet = (getArgIndex(0) != 0) || (getArgIndex(1) != 0);
        }
        else if (myType.equals("not")){
            booleanRet = getArgIndex(0) == 0;
        }
        if (booleanRet == true){
            setReturnValue(1);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanOperator(myType, getNumArgs());
        return copy;
    }
}
