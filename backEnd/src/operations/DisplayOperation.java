package operations;

import frontend.AnimalScreen;

abstract public class DisplayOperation extends Operation{
    protected AnimalScreen myScreen;

    public DisplayOperation(String commandType, int numArgs) {
        super(commandType, numArgs);
    }

    public void setAnimalScreen(AnimalScreen screen){
        myScreen = screen;
    }

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();
}
