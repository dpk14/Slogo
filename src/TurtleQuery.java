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
            ret = myTurtle.getCoordinates()[0];
        }
        else if (myType.equals("ycor")){
            ret = myTurtle.getCoordinates()[1];
        }
        else if (myType.equals("heading")){
            ret = myTurtle.getHeading();
        }
        else if (myType.equals("pen")){
            if (myTurtle.getPenStatus()){
                ret = 0;
            }
            else {
                ret = 1;
            }
        }
        else if (myType.equals("showing")){
            myTurtle.setShowing();
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TurtleQuery(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
