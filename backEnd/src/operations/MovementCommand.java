package operations;

public class  MovementCommand extends TurtleCommand {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String movementType, int numArgs){
        super(movementType, numArgs);
        evaluate();
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
