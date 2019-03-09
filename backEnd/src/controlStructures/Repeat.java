package controlStructures;

import general.ProgramParser;
import general.SystemStorage;
import java.util.ArrayList;

public class Repeat extends ControlStructure {
    double myTimesToRepeat;

    public Repeat(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage) {
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new Repeat(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    // performs updates on Simplifiable copy, then adjusts myUserInput to equal this, until it's simplified
    @Override
    protected void simplifyAndExecuteStructure() {
        int counter = 0;
        do {
            simplifyAndEvaluate(mySimplifiableLine, myStartingIndex + 1, myAnimal);
            myTimesToRepeat = Double.parseDouble(mySimplifiableLine.get(myStartingIndex + 1)); //TODO: change to say MyParser.parseValue in case its a vari
            myIndexOfFirstList = myStartingIndex + 2;

            if (myTimesToRepeat == 0) return;
            if (myTimesToRepeat<0); //TODO: error
            if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ;

            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
            if (counter != myTimesToRepeat - 1) {
                resetSimplification(mySavedLine);
                mySavedLine=new ArrayList<>(mySavedLine);
            }
            counter++;
        } while (counter < myTimesToRepeat);
    }
}


