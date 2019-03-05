package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {
    protected Animal myTurtle;

    public Command(String commandType, int numArgs, SystemStorage storage){
        super(commandType, numArgs, storage);
        myTurtle = mySystemStorage.getAnimal(0);
    }
    @Override
    abstract public void execute();

    @Override
    abstract public Operation copy();

    @Override
    abstract public double evaluate();
}
