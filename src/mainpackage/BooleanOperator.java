package mainpackage;

public class BooleanOperator extends Operation {
    public BooleanOperator(String type, int numArgs){
        super(type, numArgs);
    }

    @Override
    public double evaluate() {
        boolean booleanRet = false;
        ret = 0;
        if (myType.equals("and")){
            booleanRet = (myArgs[0] != 0) && (myArgs[1] != 0);
        }
        else if (myType.equals("or")){
            booleanRet = (myArgs[0] != 0) || (myArgs[1] != 0);
        }
        else if (myType.equals("not")){
            booleanRet = myArgs[0] == 0;
        }
        if (booleanRet == true){
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanOperator(myType, myNumArgs);
        return copy;
    }
}
