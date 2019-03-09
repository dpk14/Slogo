package interpreters;

import general.DisplayModel;
import general.SystemStorage;

public class DisplayInterpreter {
    private SystemStorage myStorage;
    private DisplayModel currentDisplay;
    private DisplayModel updatedDisplay;

    public DisplayInterpreter(SystemStorage storage){
        myStorage = storage;
        updatedDisplay = new DisplayModel();
        currentDisplay = myStorage.getDisplay();
    }

}
