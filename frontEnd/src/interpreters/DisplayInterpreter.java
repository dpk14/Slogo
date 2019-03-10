package interpreters;

import general.DisplayModel;
import general.ErrorMessage;
import general.SystemStorage;
import visualization.ScreenOptions;

public class DisplayInterpreter {
    private SystemStorage myStorage;
    private DisplayModel currentDisplay;
    private ScreenOptions currentScreen;
    private ScreenOptions updatedScreen;

    public DisplayInterpreter(SystemStorage storage){
        myStorage = storage;
        //updatedDisplay = new DisplayModel(new ErrorMessage());
        currentDisplay = myStorage.getDisplay();
    }

    public void updateDisplay(){

    }
/*
    private ScreenOptions updateScreen(){

    }
*/
}
