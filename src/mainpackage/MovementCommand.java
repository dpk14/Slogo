package mainpackage;

public class  MovementCommand extends Command {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
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
        ret = stepSize;
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new MovementCommand(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
