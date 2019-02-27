import java.util.List;

public class BooleanOperator extends Operation {
    public BooleanOperator(String type, List<String> args, SystemStorage storage){
        super(type, args, storage);
    }

    @Override
    public double execute() {
        boolean ret = false;
        if (myType.equals("and")){
            ret = (parseString(myArgs.get(0)) != 0) && (parseString(myArgs.get(1)) != 0);
        }
        else if (myType.equals("or")){
            ret = (parseString(myArgs.get(0)) != 0) || (parseString(myArgs.get(1)) != 0);
        }
        else if (myType.equals("not")){
            ret = parseString(myArgs.get(0)) == 0;
        }
        if (ret == true){
            return 1;
        }
        return 0;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanOperator(myType, myArgs, mySystemStorage);
        return copy;
    }
}
