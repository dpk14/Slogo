package operations;

public class HomeCommand extends TurtleOperation implements Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;

    public HomeCommand (String movementType, int numArgs){
        super(movementType, numArgs);
    }

    @Override
    public void execute() {
        if (myType.equals("home")){
            setReturnValue(getTurtle().setPosition(HOME_X, HOME_Y));
        }
        else if (myType.equals("clear")){
            setReturnValue(getTurtle().setPosition(HOME_X, HOME_Y));
            getTurtle().clearTrails();
        }
    }

    @Override
    public double evaluate(){
        double current_x = getTurtle().getCoordinates()[0];
        double current_y = getTurtle().getCoordinates()[1];
        setReturnValue(calcDistance(current_x, HOME_X, current_y, HOME_Y));
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new HomeCommand(myType, getNumArgs());
        ((HomeCommand) copy).setAnimal(getTurtle());
        return copy;
    }
}
