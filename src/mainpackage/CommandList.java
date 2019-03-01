package mainpackage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandList extends ControlStructure {

    public CommandList(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode() {
        convertCodeToCommands();
        return 0;
    }

    @Override
    protected void convertCodeToCommands(){
        int currentIndex = myStartingIndex;
        currentIndex++;
        String currentEntry;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount){
            currentEntry=myUserInput.get(currentIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) {
                myCommands.addAll(parseNestedControl(currentEntrySymbol, currentIndex));
            }
            else if (myParser.isOperation(currentEntrySymbol)) {
                Operation operation=parseOperation(currentEntrySymbol, currentIndex);
                if (operation instanceof Command) myCommands.add((Command) operation);
            }
            else ; //error
            currentIndex++;
            String updatedEntry=myUserInput.get(currentIndex);
            if(updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //error, bracket imbalance
        }
    }

    @Override
    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue) {
        return myUserInput;
    }
}

