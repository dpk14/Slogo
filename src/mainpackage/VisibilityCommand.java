package mainpackage;

public class VisibilityCommand extends TurtleOperation implements Command{

    public VisibilityCommand(String movementType, int numArgs){
        super(movementType, numArgs);
    }

    @Override
    public void execute() {
        if (myType.equals("show")){
            getTurtle().setVisibility(true);
        }
        else if (myType.equals("hide")){
            getTurtle().setVisibility(false);
        }
    }

    @Override
    public double evaluate(){
        if (myType.equals("show")){
            setReturnValue(1);
        }
        else if (myType.equals("hide")){
            setReturnValue(0);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new VisibilityCommand(myType, getNumArgs());
        ((VisibilityCommand) copy).setAnimal(getTurtle());
        return copy;
    }
}
