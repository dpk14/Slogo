package operations.home_operations;

import operations.Command;
import operations.TurtleOperation;

abstract public class HomeOperation extends TurtleOperation implements Command {
    private final double HOME_X = 0;
    private final double HOME_Y = 0;
    private final int HOME_OPERATION_NUM_ARGS = 0;

    public HomeOperation (){
        super();
        setNumArgs(HOME_OPERATION_NUM_ARGS);
    }

    protected double getHomeX(){
        return HOME_X;
    }

    protected double getHomeY(){
        return HOME_Y;
    }

    @Override
    abstract public void execute();

    @Override
    abstract public double evaluate();

}
