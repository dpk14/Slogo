package operations;

import general.DisplayModel;

abstract public class DisplayOperation extends Operation{
    private DisplayModel myDisplay;

    public DisplayOperation() {
        super();
    }

    @Override
    abstract public double evaluate();

    public void setDisplay(DisplayModel display){
        myDisplay = display;
    }

    protected DisplayModel getMyDisplay(){
        return myDisplay;
    }
}
