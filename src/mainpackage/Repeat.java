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
    public double executeCode(){
            evaluateSimplifiableCopy(myStartingIndex+1);
            myTimesToRepeat=Double.parseDouble(myUserInput.get(myStartingIndex+1));
            myIndexOfList=myStartingIndex+2;
            myUserInput=mySimplifiableCopy;
            if (!myUserInput.get(myIndexOfList).equals("[")); //throw error
            List<Command> previousCommandLog=myStorage.getMyCommandLog();
            for(int k=0; k<myTimesToRepeat; k++) {
                mySimplifiableCopy=new ArrayList<>(myUserInput);
                evaluateSimplifiableCopy(myIndexOfList);
            }
            myUserInput=mySimplifiableCopy;
            List<Command> currentCommandLog=myStorage.getMyCommandLog();
            if(previousCommandLog.size()!=currentCommandLog.size()){
                return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
            }
            return 0;
    }
    }


