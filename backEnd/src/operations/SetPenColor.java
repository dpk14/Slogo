package operations;

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
        String color = getDisplay().getColor((int) getArgIndex(0));
        getTurtle().setPenColor(color);
    }
}
