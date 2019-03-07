package operations;

public class TurtleQuery extends TurtleOperation {

    public TurtleQuery (String movementType, int numArgs) {
        super(movementType, numArgs);
    }

    @Override
    public double evaluate(){
        ret = -1;
        if (myType.equals("xcor")){
            ret = myTurtle.getCoordinates()[0];
        }
        else if (myType.equals("ycor")){
            ret = myTurtle.getCoordinates()[1];
        }
        else if (myType.equals("heading")){
            ret = myTurtle.getHeading();
        }
        else if (myType.equals("pen")){
            if (myTurtle.getPenStatus()){
                ret = 0;
            }
            else {
                ret = 1;
            }
        }
        else if (myType.equals("showing")){
            myTurtle.setVisibility(true);
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new TurtleQuery(myType, myNumArgs);
        ((TurtleQuery) copy).setAnimal(myTurtle);
        return copy;
    }
}
