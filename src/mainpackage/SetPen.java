package mainpackage;

public class SetPen extends Command {
    public SetPen (String movementType, int numArgs, SystemStorage storage, Animal turtle){
        super(movementType, numArgs, storage, turtle);
    }

    @Override
    public double execute() {
        ret = -1;
        if (myType.equals("up")){
            myTurtle.penUp();
            ret = 0;
        }
        else if (myType.equals("down")){
            myTurtle.penDown();
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetPen(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
