import java.util.ArrayList;
import java.util.List;

public class TrigonometricOperation extends Expression {
    public TrigonometricOperation (String myType, List<String> args, SystemStorage storage){
        super(myType, args, storage);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("sin")){
            ret = Math.sin(parseString(myArgs.get(0)));
        }
        else if (myType.equals("cos")){
            ret = Math.cos(parseString(myArgs.get(0)));
        }
        else if (myType.equals("tan")){
            ret = Math.tan(parseString(myArgs.get(0)));
        }
        else if (myType.equals("atan")){
            ret = Math.atan(parseString(myArgs.get(0)));
        }
        else if (myType.equals("pi")){
            ret = Math.PI;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TrigonometricOperation(myType, myArgs, mySystemStorage);
        return copy;
    }
}
