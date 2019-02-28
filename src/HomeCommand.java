import java.util.List;

public class HomeCommand extends Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;

    public HomeCommand (String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
    }

    @Override
    public double execute() {
        ret = -1;
        if (myType.equals("home")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
        }
        else if (myType.equals("clear")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
            myTurtle.clearTrails();
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new HomeCommand(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
