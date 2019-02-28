package mainpackage;

public class RotateCommand extends Command{
    private final int RIGHT_HEADING_MULTIPLIER = -1;

    public RotateCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
    }

    @Override
    public double execute() {
        double angle = parseString(myArgs.get(0));
        if (myType.equals("right")){
            myTurtle.adjustHeading(angle);
        }
        else if (myType.equals("left")){
            myTurtle.adjustHeading(angle * RIGHT_HEADING_MULTIPLIER);
        }
        ret = angle;
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new RotateCommand(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
