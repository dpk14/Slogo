package operations;

public class BooleanExpression extends Operation{
    public BooleanExpression(int numArgs){
        super(numArgs);
    }

    @Override
    public double evaluate() {
        boolean booleanRet = false;
        ret = 0;
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
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanExpression(myNumArgs);
        return copy;
    }
}
