package mainpackage;

public class VisibilityCommand extends TurtleCommand{

    public VisibilityCommand(String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
    }

    @Override
    public void execute() {
        if (myType.equals("show")){
            myTurtle.setVisibility(true);
        }
        else if (myType.equals("hide")){
            myTurtle.setVisibility(false);
        }
    }

    @Override
    public double evaluate(){
        ret = -1;
        if (myType.equals("show")){
            ret = 1;
        }
        else if (myType.equals("hide")){
            ret = 0;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new VisibilityCommand(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
