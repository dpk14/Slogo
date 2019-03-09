package operations;

public class SetBackgroundCommand extends DisplayOperation implements Command {
    private final int SET_BACKGROUND_NUM_ARGS = 1;

    public SetBackgroundCommand(){
        super();
        setNumArgs(SET_BACKGROUND_NUM_ARGS);
    }

    @Override
    public double evaluate(){
        return getArgIndex(0);
    }

    @Override
    public void execute(){
        getMyDisplay().setBackgroundColor((int) getArgIndex(0));
    }

}
