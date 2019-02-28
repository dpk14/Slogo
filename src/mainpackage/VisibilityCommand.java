package mainpackage;

public class VisibilityCommand extends Command{

    public VisibilityCommand(String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
    }

    @Override
    public double execute() {
        double ret = -1;
        if (myType.equals("show")){
            myTurtle.setVisibility(true);
            ret = 1;
        }
        else if (myType.equals("hide")){
            myTurtle.setVisibility(false);
            ret = 0;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new VisibilityCommand(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
