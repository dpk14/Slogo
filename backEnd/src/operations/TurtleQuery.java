package operations;

public class TurtleQuery extends TurtleOperation {

    public TurtleQuery (String movementType, int numArgs) {
        super(movementType, numArgs);
    }

    @Override
    public double evaluate(){
        if (myType.equals("xcor")){
            setReturnValue(getTurtle().getCoordinates()[0]);
        }
        else if (myType.equals("ycor")){
            setReturnValue(getTurtle().getCoordinates()[1]);
        }
        else if (myType.equals("heading")){
            setReturnValue(getTurtle().getHeading());
        }
        else if (myType.equals("pen")){
            if (getTurtle().getPenStatus()){
                setReturnValue(0);
            }
            else {
                setReturnValue(1);
            }
        }
        else if (myType.equals("showing")){
            getTurtle().setVisibility(true);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new TurtleQuery(myType, getNumArgs());
        ((TurtleQuery) copy).setAnimal(getTurtle());
        return copy;
    }
}
