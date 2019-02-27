import java.util.List;
import java.util.ArrayList;

public class HomeCommand extends Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;

    public HomeCommand (String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("home")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
        }
        else if (myType.equals("clear")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
            //TODO: erase trails
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new HomeCommand(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
