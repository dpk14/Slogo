package mainpackage;

public class RotateCommand extends TurtleOperation implements Command{
    private final int RIGHT_HEADING_MULTIPLIER = -1;

    public RotateCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
    }

    @Override
    public void execute() {
        double angle = getArgIndex(0);
        if (myType.equals("right")){
            getTurtle().adjustHeading(angle);
        }
        else if (myType.equals("left")){
            getTurtle().adjustHeading(angle * RIGHT_HEADING_MULTIPLIER);
        }
    }

    @Override
    public double evaluate(){
        getArgIndex(0);
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new RotateCommand(myType, getNumArgs());
        ((RotateCommand) copy).setAnimal(getTurtle());
        return copy;
    }
}
