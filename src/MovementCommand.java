import java.util.ArrayList;
import java.util.List;

public class  MovementCommand extends Command {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    public MovementCommand(SystemStorage storage, Animal turtle){
        super("forward", new ArrayList<String>(), storage, turtle);
        super.getMyArgs().add("0");
    }

    @Override
    public double execute() {
        double stepSize = parseString(myArgs.get(0));
        if (myType.equals("forward")){
            myTurtle.changePosition(stepSize);
        }
        else if (myType.equals("backward")){
            myTurtle.changePosition(stepSize * BACKWARD_MULTIPLIER);
        }
        return stepSize;
    }

    @Override
    public Operation copy() {
        Operation copy = new MovementCommand(myType, myArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
