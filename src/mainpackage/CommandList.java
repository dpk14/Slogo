package mainpackage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandList extends ControlStructure {

    public CommandList(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    public double executeCode() {
        int currentIndex = myStartingIndex;
        currentIndex++;
        String currentEntry;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount && currentIndex!=mySimplifiableCopy.size()-1){
            currentEntry=mySimplifiableCopy.get(currentIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) parseNestedControl(currentEntrySymbol, currentIndex);
            else if (myParser.isOperation(currentEntrySymbol)) parseOperation(currentEntrySymbol, currentIndex);
            else ; //error
            currentIndex++;
            String updatedEntry=mySimplifiableCopy.get(currentIndex);
            if(updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //error, bracket imbalance
            myUserInput=mySimplifiableCopy;
        }
        return 0;
    }

    @Override
    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue) {
        return myUserInput;
    }
}

