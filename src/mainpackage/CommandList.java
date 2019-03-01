package mainpackage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandList extends ControlStructure {

    public CommandList(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode() {
        simplifyAndExecuteStructure();
        return 0;
    }

    @Override
    public ControlStructure copy(){
        return this;
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        int currentIndex = myStartingIndex;
        currentIndex++;
        String currentEntry;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount){
            currentEntry=mySimplifiableLine.get(currentIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) parseNestedControl(currentEntrySymbol, currentIndex, mySimplifiableLine);
            else if (myParser.isOperation(currentEntrySymbol)) {
                parseOperation(currentEntrySymbol, currentIndex, mySimplifiableLine);
            }
            else ; //error
            currentIndex++;
            String updatedEntry=mySimplifiableLine.get(currentIndex);
            if(updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //error, bracket imbalance
        }
    }

    @Override
    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue, ArrayList<String> simplifiableLine) {
        return simplifiableLine;
    }
}

