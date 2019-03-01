package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlStructure {
    private double myTimesToRepeat;
    private int myIndexOfList;

    public Repeat(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    // performs updates on Simplifiable copy, then adjusts myUserInput to equal this, until it's simplified
    @Override
    protected void convertCodeToCommands() {
        simplifyLineSection(myStartingIndex + 1);
        myTimesToRepeat = Double.parseDouble(myUserInput.get(myStartingIndex + 1));
        myIndexOfList = myStartingIndex + 2;
        if (!myUserInput.get(myIndexOfList).equals("[")) ; //throw error
        List<Command> parsedList = simplifyLineSection(myIndexOfList);
        for (int k = 0; k < myTimesToRepeat; k++) {
            myCommands.addAll(parsedList);
        }
    }
    }


