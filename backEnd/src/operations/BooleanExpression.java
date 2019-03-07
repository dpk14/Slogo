package operations;

public class BooleanExpression extends Operation{
    public BooleanExpression(String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        boolean booleanRet = false;
        ret = 0;
        if (myType.equals("less")){
            booleanRet = myArgs[0] < myArgs[1];
        }
        else if (myType.equals("greater")){
            booleanRet = myArgs[0] > myArgs[1];
        }
        else if (myType.equals("equal")){
            booleanRet = myArgs[0] == myArgs[1];
        }
        else if (myType.equals("notequal")){
            booleanRet = myArgs[0] != myArgs[1];
        }
        if (booleanRet == true){
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanExpression(myType, myNumArgs);
        return copy;
    }
}
