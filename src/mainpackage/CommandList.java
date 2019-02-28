package mainpackage;

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
        while(openBracketCount!=closedBracketCount && currentIndex!=myUserInput.size()-1){
            currentEntry=myUserInput.get(currentIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            //System.out.printf("%d", myUserInput.size());
            ArrayList<String> simplifiedLine;
            if (myParser.isControl(currentEntrySymbol)) myUserInput=parseNestedControl(currentEntrySymbol, currentIndex, myUserInput);
            else if (myParser.isOperation(currentEntrySymbol)) myUserInput=parseOperation(currentEntrySymbol, currentIndex, myUserInput);
            else ; //error
            currentIndex++;
            String updatedEntry=myUserInput.get(currentIndex);
            if(updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //error, bracket imbalance
        }
        return 0;
    }
}
