package mainpackage;

abstract public class DisplayCommand extends Command{

    public DisplayCommand(String commandType, int numArgs, SystemStorage storage, ) {
        super(commandType, numArgs, storage);
    }

    @Override
    abstract public void execute();

    @Override
    abstract public double evaluate();

    @Override
    abstract public Operation copy();
}
