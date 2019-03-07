package mainpackage;

public class SetVisual extends DisplayOperation implements Command{
    public SetVisual(String commandType, int numArgs) {
        super(commandType, numArgs);
    }

    @Override
    public void execute() {

    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public Operation copy() {
        return null;
    }
}
