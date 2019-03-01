package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlStructure {
    double myTimesToRepeat;
    int myIndexOfList;

    public Repeat(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    // performs updates on Simplifiable copy, then adjusts myUserInput to equal this, until it's simplified
    @Override
    protected void simplifyAndExecuteStructure() {
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex + 1);
        myTimesToRepeat = Double.parseDouble(mySimplifiableLine.get(myStartingIndex + 1));
        myIndexOfList = myStartingIndex + 2;
        if (!mySimplifiableLine.get(myIndexOfList).equals("[")) ; //throw error
        for (int k = 0; k < myTimesToRepeat; k++) {
            mySimplifiableLine=new ArrayList<String>(mySavedLine);
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfList);
        }
    }
    }


