package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class BackwardCommand extends TurtleOperation implements Command {
    private final int BACKWARD_NUM_ARGS = 1;
    private final int BACKWARD_MULTIPLIER = -1;

    public BackwardCommand(){
        super();
        setNumArgs(BACKWARD_NUM_ARGS);
    }

    @Override
    public void execute() {
        double stepSize = getArgIndex(0);
        getTurtle().changePosition(stepSize * BACKWARD_MULTIPLIER);
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
