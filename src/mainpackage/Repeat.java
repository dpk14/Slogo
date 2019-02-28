package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlStructure {
    private double myTimesToRepeat;
    private int myIndexOfList;

    public Repeat(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode(){
            ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
            myTimesToRepeat=Double.parseDouble(simplifiedLine.get(myStartingIndex+1));
            myIndexOfList=myStartingIndex+2;
            if (!simplifiedLine.get(myIndexOfList).equals("[")); //throw error
            List<Command> previousCommandLog=myStorage.getMyCommandLog();
            for(int k=0; k<myTimesToRepeat; k++) {
                evaluateLineSection(myIndexOfList, simplifiedLine);
            }
            List<Command> currentCommandLog=myStorage.getMyCommandLog();
            if(previousCommandLog.size()!=currentCommandLog.size()){
                return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
            }
            return 0;
    }
    }


