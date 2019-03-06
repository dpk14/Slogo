package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {

    public Command(String commandType, int numArgs, SystemStorage storage){
        super(commandType, numArgs, storage);
    }

    abstract public void execute();

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();
}
