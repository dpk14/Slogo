package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class LeftRotateCommand extends TurtleOperation implements Command {
    private final int LEFT_ROTATE_NUM_ARGS = 1;
    private final int RIGHT_HEADING_MULTIPLIER = -1;

    public LeftRotateCommand(){
        super();
        setNumArgs(LEFT_ROTATE_NUM_ARGS);
    }
    @Override
    public void execute() {
        double angle = getArgIndex(0);
        getTurtle().adjustHeading(angle * RIGHT_HEADING_MULTIPLIER);
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
