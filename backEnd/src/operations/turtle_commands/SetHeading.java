package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class SetHeading extends TurtleOperation implements Command {
    private final int HEADING_NUM_ARGS = 1;

    public SetHeading(){
        super();
        setNumArgs(HEADING_NUM_ARGS);
    }

    @Override
    public void execute() {
        double angle = getArgIndex(0);
        getTurtle().setHeading(angle);
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
