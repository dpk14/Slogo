package operations;

public class SetPen extends TurtleCommand {
    public SetPen (String movementType, int numArgs, SystemStorage storage){
        super(movementType, numArgs, storage);
    }

    @Override
    public void execute() {
        if (myType.equals("up")){
            getTurtle().penUp();
        }
        else if (myType.equals("down")){
            getTurtle().penDown();
        }
    }

    @Override
    public double evaluate(){
        if (myType.equals("up")){
            setReturnValue(0);
        }
        else if (myType.equals("down")){
            setReturnValue(1);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new SetPen(myType, getNumArgs());
        return copy;
    }
}
