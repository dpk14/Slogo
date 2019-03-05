package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {
    protected Animal myTurtle;

    public Command(String commandType, int numArgs, SystemStorage storage, Animal turtle){
        super(commandType, numArgs, storage);
        myTurtle = turtle;
    }

    public void setMyTurtle(Animal turtle){
        myTurtle = turtle;
    }

    abstract public void execute();

    @Override
    abstract public Operation copy();

    @Override
    abstract public double evaluate();
}
