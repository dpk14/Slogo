package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class TurtleCommand extends Command {
    protected Animal myTurtle;

    public TurtleCommand(String commandType, int numArgs, SystemStorage storage){
        super(commandType, numArgs, storage);
    }

    public void setAnimal(Animal turtle){
        myTurtle = turtle;
    }

    public Animal getTurtle(){
        return myTurtle;
    }

    @Override
    abstract public void execute();

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();
}


