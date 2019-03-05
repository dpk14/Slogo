package mainpackage;

public class HomeCommand extends Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;

    public HomeCommand (String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
    }

    @Override
    public void execute() {
        if (myType.equals("home")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
        }
        else if (myType.equals("clear")){
            ret = myTurtle.setPosition(HOME_X, HOME_Y);
            myTurtle.clearTrails();
        }
    }

    @Override
    public double evaluate(){
        ret = -1;
        double current_x = myTurtle.getCoordinates()[0];
        double current_y = myTurtle.getCoordinates()[1];
        ret = Math.sqrt(Math.pow(current_x - HOME_X,2) + Math.pow(current_y - HOME_Y, 2));
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new HomeCommand(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
