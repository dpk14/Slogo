package operations.home_operations;

import operations.Command;
import operations.home_operations.HomeOperation;

public class ClearScreen extends HomeOperation implements Command {
    @Override
    public void execute() {
        getTurtle().setPosition(getHomeX(), getHomeY());
        getTurtle().clearTrails();
    }

    @Override
    public double evaluate() {
        double current_x = getTurtle().getCoordinates()[0];
        double current_y = getTurtle().getCoordinates()[1];
        return calcDistance(current_x, getHomeX(), current_y, getHomeY());
    }
}
