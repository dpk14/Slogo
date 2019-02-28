import java.util.List;

public class BooleanExpression extends Operation{
    public BooleanExpression(String myType, List<String> args, SystemStorage storage){
        super(myType, args, storage);
    }

    @Override
    public double execute() {
        boolean ret = false;
        if (myType.equals("less")){
            ret = parseString(myArgs.get(0)) < parseString(myArgs.get(1));
        }
        else if (myType.equals("greater")){
            ret = parseString(myArgs.get(0)) > parseString(myArgs.get(1));
        }
        else if (myType.equals("equal")){
            ret = parseString(myArgs.get(0)) == parseString(myArgs.get(1));
        }
        else if (myType.equals("notequal")){
            ret = parseString(myArgs.get(0)) != parseString(myArgs.get(1));
        }
        if (ret == true){
            return 1;
        }
        return 0;
    }

    @Override
    public Operation copy() {
        Operation copy = new BooleanExpression(myType, myArgs, mySystemStorage);
        return copy;
    }
}
