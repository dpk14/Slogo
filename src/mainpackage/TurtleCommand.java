package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class TurtleCommand extends Command {
    protected Animal myTurtle;

    public TurtleCommand(String commandType, int numArgs, SystemStorage storage, Animal turtle){
        super(commandType, numArgs, storage);
        myTurtle = turtle;
    }

    public void setMyTurtle(Animal turtle){
        myTurtle = turtle;
    }

    @Override
    abstract public void execute();

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();
}


