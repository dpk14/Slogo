package mainpackage;

public class SetAbsoluteCommand extends TurtleOperation implements Command {
    public SetAbsoluteCommand (String movementType, int numArgs){
        super(movementType, numArgs);
        evaluate();
    }

    @Override
    public void execute() {
        if (myType.equals("heading")){
            double angle = getArgIndex(0);
            getTurtle().setHeading(angle);
        }
        else if (myType.equals("position")){
            double x = getArgIndex(0);
            double y = getArgIndex(1);
            getTurtle().setPosition(x, y);
        }
        else if (myType.equals("towards")){
            getTurtle().setToward(getArgIndex(0), getArgIndex(1));
        }
    }

    public double evaluate(){
        if (myType.equals("heading")){
            setReturnValue(getArgIndex(0));
        }
        else if (myType.equals("position")){
            double x = getArgIndex(0);
            double y = getArgIndex(1);
            double current_x = getTurtle().getCoordinates()[0];
            double current_y = getTurtle().getCoordinates()[1];
            setReturnValue(calcDistance(current_x, x, current_y, y));
        }
        else if (myType.equals("towards")){
            setReturnValue(getTurtle().setToward(getArgIndex(0), getArgIndex(1))); //TODO: return change in heading
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new SetAbsoluteCommand(myType, getNumArgs());
        ((SetAbsoluteCommand) copy).setAnimal(getTurtle());
        return copy;
    }
}
