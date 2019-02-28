package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {
    Animal myTurtle;

    public Command(String commandType, int numArgs, SystemStorage storage, Animal turtle){
        super(commandType, numArgs, storage);
        myTurtle = turtle;
    }

    @Override
    abstract public double execute();

    @Override
    abstract public Operation copy();

}
