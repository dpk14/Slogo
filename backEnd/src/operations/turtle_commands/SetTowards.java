package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class SetTowards extends TurtleOperation implements Command {
    private final int TOWARDS_NUM_ARGS = 2;

    public SetTowards(){
        super();
        setNumArgs(TOWARDS_NUM_ARGS);
    }

    @Override
    public void execute() {
        getTurtle().setToward(getArgIndex(0), getArgIndex(1));
    }

    @Override
    public double evaluate() {
        return getTurtle().setToward(getArgIndex(0), getArgIndex(1)); //TODO: change calculation of heading change
    }
}
