package operations;

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
