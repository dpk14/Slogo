package operations.home_operations;

import operations.Command;

public class HomeCommand extends HomeOperation implements Command {
    public HomeCommand(){
        super();
    }
    @Override
    public void execute() {
        getTurtle().setPosition(getHomeX(), getHomeY());
    }

    @Override
    public double evaluate() {
        double current_x = getTurtle().getCoordinates()[0];
        double current_y = getTurtle().getCoordinates()[1];
        return calcDistance(current_x, getHomeX(), current_y, getHomeY());
    }
}
