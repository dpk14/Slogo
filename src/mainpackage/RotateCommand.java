package mainpackage;

public class RotateCommand extends TurtleCommand{
    private final int RIGHT_HEADING_MULTIPLIER = -1;

    public RotateCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
        evaluate();
    }

    @Override
    public void execute() {
        double angle = ret;
        if (myType.equals("right")){
            myTurtle.adjustHeading(angle);
        }
        else if (myType.equals("left")){
            myTurtle.adjustHeading(angle * RIGHT_HEADING_MULTIPLIER);
        }
    }

    @Override
    public double evaluate(){
        ret = parseString(myArgs.get(0));
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new RotateCommand(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
