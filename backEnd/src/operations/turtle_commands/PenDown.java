package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class PenDown extends TurtleOperation implements Command {
    private final int PEN_DOWN_NUM_ARGS = 0;
    private final double PEN_DOWN_VAL = 1;

    public PenDown(){
        super();
        setNumArgs(PEN_DOWN_NUM_ARGS);
    }

    @Override
    public void execute() {
        getTurtle().penDown();
    }

    @Override
    public double evaluate() {
        return PEN_DOWN_VAL;
    }
}
