import java.util.ArrayList;
import java.util.List;

public class SetPen extends Command {
    public SetPen (String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    public SetPen (SystemStorage storage, Animal turtle){
        super("up", new ArrayList<>(), storage, turtle);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("up")){
            myTurtle.penUp();
            ret = 0;
        }
        else if (myType.equals("down")){
            myTurtle.penDown();
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetPen(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
