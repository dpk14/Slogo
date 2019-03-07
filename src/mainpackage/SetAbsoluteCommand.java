package mainpackage;

public class SetAbsoluteCommand extends TurtleOperation implements Command {
    public SetAbsoluteCommand (String movementType, int numArgs){
        super(movementType, numArgs);
        evaluate();
    }

    @Override
    public void execute() {
        if (myType.equals("heading")){
            double angle = ret;
            myTurtle.setHeading(angle);
        }
        else if (myType.equals("position")){
            double x = myArgs[0];
            double y = myArgs[1];
            myTurtle.setPosition(x, y);
        }
        else if (myType.equals("towards")){
            myTurtle.setToward(myArgs[0], myArgs[1]);
        }
    }

    public double evaluate(){
        if (myType.equals("heading")){
            ret = myArgs[0];
        }
        else if (myType.equals("position")){
            double x = myArgs[0];
            double y = myArgs[1];
            double current_x = myTurtle.getCoordinates()[0];
            double current_y = myTurtle.getCoordinates()[1];
            ret = calcDistance(current_x, x, current_y, y);
        }
        else if (myType.equals("towards")){
            ret = myTurtle.setToward(myArgs[0], myArgs[1]); //TODO: return change in heading
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetAbsoluteCommand(myType, myNumArgs);
        ((SetAbsoluteCommand) copy).setAnimal(myTurtle);
        return copy;
    }
}
