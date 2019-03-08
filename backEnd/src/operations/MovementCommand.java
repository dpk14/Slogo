package operations;

public class  MovementCommand extends TurtleOperation implements Command {
    private final int BACKWARD_MULTIPLIER = -1;

    public MovementCommand(String type, int numArgs){
        super(type, numArgs);
    }

    @Override
    public void execute() {
        double stepSize = getArgIndex(0);
        if (myType.equals("forward")){
            getTurtle().changePosition(stepSize);
        }
        else if (myType.equals("backward")){
            getTurtle().changePosition(stepSize * BACKWARD_MULTIPLIER);
        }
    }

    @Override
    public double evaluate(){
        setReturnValue(getArgIndex(0));
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new MovementCommand(myType, getNumArgs());
        ((MovementCommand) copy).setAnimal(getTurtle());
        return copy;
    }
}
