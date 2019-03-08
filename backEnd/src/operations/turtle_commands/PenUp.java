package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class PenUp extends TurtleOperation implements Command {
    private final int PEN_UP_NUM_ARGS = 0;
    private final double PEN_UP_VAL = 0;

    public PenUp(){
        super();
        setNumArgs(PEN_UP_NUM_ARGS);
    }
    @Override
    public void execute() {
        getTurtle().penUp();
    }

    @Override
    public double evaluate() {
        return PEN_UP_VAL;
    }
}
