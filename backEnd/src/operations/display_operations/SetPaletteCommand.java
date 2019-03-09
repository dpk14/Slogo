package operations.display_operations;

import general.DisplayModel;
import operations.Command;
import operations.SystemStorageOperation;

public class SetPaletteCommand extends SystemStorageOperation implements Command {
    private final int PALETTE_COMMAND_NUM_ARGS = 4;
    private final int RED_ARG_INDEX = 1;
    private final int GREEN_ARG_INDEX = 2;
    private final int BLUE_ARG_INDEX = 3;


    private DisplayModel myDisplay;

    public SetPaletteCommand(){
        super();
    }

    @Override
    public void execute(){
        int index = (int) getArgIndex(0);
        int r = (int) getArgIndex(RED_ARG_INDEX);
        int g = (int) getArgIndex(GREEN_ARG_INDEX);
        int b = (int) getArgIndex(BLUE_ARG_INDEX);
        String hex = String.format("#%02x%02x%02x", r, g, b);
        myDisplay.setColorListItem(index, hex);
    }

    @Override
    public double evaluate(){
        return getArgIndex(0);
    }
}
