package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class ShowTurtle extends TurtleOperation implements Command {
    private final int SHOW_NUM_ARGS = 0;
    private final double SHOW_VAL = 1;

    public ShowTurtle(){
        super();
        setNumArgs(SHOW_NUM_ARGS);
    }

    @Override
    public void execute() {
        getTurtle().setVisibility(true);
    }

    @Override
    public double evaluate() {
        return SHOW_VAL;
    }
}
