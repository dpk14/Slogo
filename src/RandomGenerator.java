import java.util.ArrayList;
import java.util.List;

public class RandomGenerator extends Expression {
    public RandomGenerator (String myType, List<String> args, SystemStorage storage){
        super(myType, args, storage);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("random")){
            ret = Math.random() * parseString(myArgs.get(0));
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new RandomGenerator(myType, myArgs, mySystemStorage);
        return copy;
    }
}
