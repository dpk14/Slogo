package mainpackage;

import java.util.ArrayList;

public class CommandList extends ControlStructure {

    public CommandList(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    public double executeCode() {
        int currentIndex = myStartingIndex;
        String currentEntry;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount && currentIndex!=myUserInput.size()-1){
            currentIndex++;
            currentEntry=myUserInput.get(currentIndex);
            if(currentEntry.equals("[")) openBracketCount++;
            else if (currentEntry.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //error, bracket imbalance
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            //System.out.printf("%d", myUserInput.size());
            if (myParser.isControl(currentEntrySymbol)) myUserInput=parseNestedControl(currentEntrySymbol, currentIndex, myUserInput);
            else if (myParser.isOperation(currentEntrySymbol)) myUserInput=parseOperation(currentEntrySymbol, currentIndex, myUserInput);
            else ; //error
        }
        return 0;
    }
}
