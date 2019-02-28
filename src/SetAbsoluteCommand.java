
public class SetAbsoluteCommand extends Command {
    public SetAbsoluteCommand (String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
    }

    @Override
    public double execute() {
        ret = -1;
        if (myType.equals("heading")){
            double angle = parseString(myArgs.get(0));
            myTurtle.setHeading(angle);
            ret = angle;
        }
        else if (myType.equals("position")){
            double x = parseString(myArgs.get(0));
            double y = parseString(myArgs.get(1));
            ret = myTurtle.setPosition(x, y);
        }
        else if (myType.equals("towards")){

        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetAbsoluteCommand(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
