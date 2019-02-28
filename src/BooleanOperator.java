import java.util.List;

public class BooleanOperator extends Operation {
    public BooleanOperator(String type, int numArgs, SystemStorage storage){
        super(type, numArgs, storage);
    }

    @Override
    public double execute() {
        boolean booleanRet = false;
        ret = 0;
        if (myType.equals("and")){
            booleanRet = (parseString(myArgs.get(0)) != 0) && (parseString(myArgs.get(1)) != 0);
        }
        else if (myType.equals("or")){
            booleanRet = (parseString(myArgs.get(0)) != 0) || (parseString(myArgs.get(1)) != 0);
        }
        else if (myType.equals("not")){
            booleanRet = parseString(myArgs.get(0)) == 0;
        }
        if (booleanRet == true){
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanOperator(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
