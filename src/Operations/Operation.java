package Operations;

import java.util.ArrayList;

/**
 * @author Irene Qiao isq
 */
abstract public class Operation {
    String myType;
    ArrayList<Double> myArgs;

    abstract public double execute();
    abstract public int getNumArgs();
    abstract public Operation copy();
}
