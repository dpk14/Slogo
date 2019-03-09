package operations.display_operations;

import operations.Command;

public class SetPenColor extends PenOperation implements Command {
    public SetPenColor(){
        super();
    }

    @Override
    public double evaluate(){
        return getArgIndex(0);
    }

    @Override
    public void execute(){
        String color = getSystemStorage().getDisplay().getColor((int) getArgIndex(0));
        getTurtle().setPenColor(color);
    }
}
