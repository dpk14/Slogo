package mainpackage;

public class TurtleQuery extends Operation {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;
    private Animal myTurtle;

    public TurtleQuery (String movementType, int numArgs, SystemStorage storage, Animal turtle) {
        super(movementType, numArgs, storage);
        myTurtle = turtle;
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
        Operation copy = new TurtleQuery(myType, myNumArgs, mySystemStorage, myTurtle);
        return copy;
    }
}
