package mainpackage;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {
    Animal myTurtle;

    public Command(String commandType, int numArgs, SystemStorage storage){
        super(commandType, numArgs, storage);
        myTurtle=mySystemStorage.getAnimal("first");
    }

    @Override
    abstract public double execute();

    @Override
    abstract public Operation copy();

}
