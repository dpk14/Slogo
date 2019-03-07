package mainpackage;

public class SetPen extends TurtleOperation implements Command {
    public SetPen (String movementType, int numArgs){
        super(movementType, numArgs);
        evaluate();
    }

    @Override
    public void execute() {
        if (myType.equals("up")){
            myTurtle.penUp();
        }
        else if (myType.equals("down")){
            myTurtle.penDown();
        }
    }

    @Override
    public double evaluate(){
        ret = -1;
        if (myType.equals("up")){
            ret = 0;
        }
        else if (myType.equals("down")){
            ret = 1;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new SetPen(myType, myNumArgs);
        return copy;
    }
}
