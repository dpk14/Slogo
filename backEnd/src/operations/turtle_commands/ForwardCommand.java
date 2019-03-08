package operations.turtle_commands;

import controlStructures.For;
import operations.Command;
import operations.TurtleOperation;

public class ForwardCommand extends TurtleOperation implements Command {
    private final int FORWARD_NUM_ARGS = 1;

    public ForwardCommand(){
        super();
        setNumArgs(FORWARD_NUM_ARGS);
    }

    @Override
    public void execute() {
        double stepSize = getArgIndex(0);
        getTurtle().changePosition(stepSize);
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
