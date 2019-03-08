package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class HideTurtle extends TurtleOperation implements Command {
    private final int HIDE_NUM_ARGS = 0;
    private final double HIDE_VAL = 0;

    public HideTurtle(){
        super();
        setNumArgs(HIDE_NUM_ARGS);
    }

    @Override
    public void execute() {
        getTurtle().setVisibility(false);
    }

    @Override
    public double evaluate() {
        return HIDE_VAL;
    }
}
