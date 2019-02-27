import java.util.ArrayList;
import java.util.List;

public class RotateCommand extends Command{
    private final int RIGHT_HEADING_MULTIPLIER = -1;

    public RotateCommand(String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    public RotateCommand(SystemStorage storage, Animal turtle){
        super("forward", new ArrayList<String>(), storage, turtle);
        super.getMyArgs().add("0");
    }

    @Override
    public double execute() {
        double angle = parseString(myArgs.get(0));
        if (myType.equals("left")){
            myTurtle.adjustHeading(angle);
        }
        else if (myType.equals("right")){
            myTurtle.adjustHeading(angle * RIGHT_HEADING_MULTIPLIER);
        }
        return angle;
    }

    @Override
    public Operation copy() {
        Operation copy = new RotateCommand(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
