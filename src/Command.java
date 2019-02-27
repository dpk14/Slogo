import java.util.ArrayList;
import java.util.List;

/**
 * @author Irene Qiao isq
 */
abstract public class Command extends Operation {
    Animal myTurtle;

    public Command(String commandType, List<String> arguments, SystemStorage storage, Animal turtle){
        super(commandType, arguments, storage);
        myTurtle = turtle;
    }

    @Override
    abstract public double execute();

    @Override
    abstract public Operation copy();

}
