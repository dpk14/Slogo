package mainpackage;

public class SetVisual extends DisplayCommand {
    public SetVisual(String commandType, int numArgs, SystemStorage storage) {
        super(commandType, numArgs, storage);
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
