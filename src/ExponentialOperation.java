import java.util.ArrayList;
import java.util.List;

public class ExponentialOperation extends Expression {
    public ExponentialOperation (String myType, List<String> args, SystemStorage storage){
        super(myType, args, storage);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("pow")){
            ret = Math.pow(parseString(myArgs.get(0)), parseString(myArgs.get(1)));
        }
        else if (myType.equals("log")){
            ret = Math.log(parseString(myArgs.get(0)));
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new ExponentialOperation(myType, myArgs, mySystemStorage);
        return copy;
    }
}
