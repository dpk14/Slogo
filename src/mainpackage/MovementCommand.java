package mainpackage;

public class  MovementCommand extends TurtleOperation implements Command {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
    }

    @Override
    public void execute() {
        double stepSize = ret;
        if (myType.equals("forward")){
            myTurtle.changePosition(stepSize);
        }
        else if (myType.equals("backward")){
            myTurtle.changePosition(stepSize * BACKWARD_MULTIPLIER);
        }
    }

    @Override
    public double evaluate(){
        ret = myArgs[0];
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new MovementCommand(myType, myNumArgs);
        ((MovementCommand) copy).setAnimal(myTurtle);
        return copy;
    }
}
