import java.util.ArrayList;
import java.util.List;

public class TurtleQuery extends Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;

    public TurtleQuery (String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("xcor")){

        }
        else if (myType.equals("ycor")){

        }
        else if (myType.equals("heading")){

        }
        else if (myType.equals("pen")){

        }
        else if (myType.equals("showing")){

        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TurtleQuery(myType, myArgs, mySystemStorage);
        return copy;
    }
}
