import java.util.ArrayList;
import java.util.List;

public class VisibilityCommand extends Command{

    public VisibilityCommand(String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("show")){
            ret = 1;
        }
        else if (myType.equals("hide")){
            ret = 0;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new VisibilityCommand(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
