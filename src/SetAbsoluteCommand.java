import java.util.ArrayList;
import java.util.List;

public class SetAbsoluteCommand extends Command {
    public SetAbsoluteCommand (String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("heading")){
            double angle = parseString(myArgs.get(0));
            myTurtle.setHeading(angle);
            ret = angle;
        }
        else if (myType.equals("position")){
            double x = parseString(myArgs.get(0));
            double y = parseString(myArgs.get(1));
            ret = myTurtle.setPosition(x, y);
        }
        else if (myType.equals("towards")){

        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetAbsoluteCommand(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
