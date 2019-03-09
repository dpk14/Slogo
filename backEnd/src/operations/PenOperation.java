package operations;

import general.Animal;
import general.DisplayModel;

abstract public class PenOperation extends Operation{
    private Animal myTurtle;
    private DisplayModel myDisplay;
    private final int SET_PEN_NUM_ARGS = 1;

    public PenOperation(){
        super();
        setNumArgs(SET_PEN_NUM_ARGS);
    }
    @Override
    abstract public double evaluate();

    public void setAnimal (Animal turtle){
        myTurtle = turtle;
    }

    public void setDisplay(DisplayModel display){
        myDisplay = display;
    }

    protected Animal getTurtle(){
        return myTurtle;
    }

    protected DisplayModel getDisplay(){
        return myDisplay;
    }
}
