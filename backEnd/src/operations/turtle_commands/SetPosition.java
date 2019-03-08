package operations.turtle_commands;

import operations.Command;
import operations.TurtleOperation;

public class SetPosition extends TurtleOperation implements Command {
    private final int POSITION_NUM_ARGS = 2;

    public SetPosition(){
        super();
        setNumArgs(POSITION_NUM_ARGS);
    }

    @Override
    public void execute() {
        double x = getArgIndex(0);
        double y = getArgIndex(1);
        getTurtle().setPosition(x, y);
    }

    @Override
    public double evaluate() {
        double x = getArgIndex(0);
        double y = getArgIndex(1);
        double current_x = getTurtle().getCoordinates()[0];
        double current_y = getTurtle().getCoordinates()[1];
        return calcDistance(current_x, x, current_y, y);
    }
}
