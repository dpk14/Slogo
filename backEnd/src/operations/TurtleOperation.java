package operations;

import control.Animal;

/**
 * @author Irene Qiao isq
 */
abstract public class TurtleOperation extends Operation {
    private Animal myTurtle;

    public TurtleOperation(){
        super();
    }

    public void setAnimal(Animal turtle){
        myTurtle = turtle;
    }

    protected Animal getTurtle(){
        return myTurtle;
    }

    protected double calcDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2, 2));
    }

    @Override
    abstract public double evaluate();

}


