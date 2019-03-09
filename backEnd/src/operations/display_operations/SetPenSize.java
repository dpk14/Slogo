package operations.display_operations;

import operations.Command;

public class SetPenSize extends PenOperation implements Command {

    public SetPenSize(){
        super();
    }
    @Override
    public void execute() {
        getTurtle().setPenSize((int) getArgIndex(0));
    }

    @Override
    public double evaluate() {
        return getArgIndex(0);
    }
}
