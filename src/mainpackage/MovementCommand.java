package mainpackage;

public class  MovementCommand extends Command {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
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
        System.out.printf("%f", ret);
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new MovementCommand(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
