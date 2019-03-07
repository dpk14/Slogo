package operations;

import mainpackage.Animal;

/**
 * @author Irene Qiao isq
 */
abstract public class TurtleOperation extends Operation {
    protected Animal myTurtle;

    public TurtleOperation(String commandType, int numArgs){
        super(commandType, numArgs);
    }

    public void setAnimal(Animal turtle){
        myTurtle = turtle;
    }

    public Animal getTurtle(){
        return myTurtle;
    }

    protected double calcDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2, 2));
    }

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();

}

