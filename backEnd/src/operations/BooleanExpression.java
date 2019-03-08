package operations;

public class BooleanExpression extends Operation{
    public BooleanExpression(String type, int numArgs){
        super(type, numArgs);
    }

    @Override
    public double evaluate() {
        boolean booleanRet = false;
        setReturnValue(0);
        if (myType.equals("less")){
            booleanRet = getArgIndex(0) < getArgIndex(1);
        }
        else if (myType.equals("greater")){
            booleanRet = getArgIndex(0) > getArgIndex(1);
        }
        else if (myType.equals("equal")){
            booleanRet = getArgIndex(0) == getArgIndex(1);
        }
        else if (myType.equals("notequal")){
            booleanRet = getArgIndex(0) != getArgIndex(1);
        }
        if (booleanRet == true){
            setReturnValue(1);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanExpression(myType, getNumArgs());
        return copy;
    }
}
