import java.util.ArrayList;
import java.util.List;

public class SetAbsoluteCommand extends Command {
    public SetAbsoluteCommand (String movementType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(movementType, arguments, storage, turtle);
    }

    public SetAbsoluteCommand (SystemStorage storage, Animal turtle){
        super("heading", new ArrayList<String>(), storage, turtle);
        super.getMyArgs().add(turtle.getCurrentAngle());
    }

    @Override
    public double execute() {
        if (myType.equals("heading")){
            double angle
            myTurtle.setHeading();
        }
    }

    @Override
    public Operation copy() {
        return null;
    }
}
