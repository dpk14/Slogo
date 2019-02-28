import javax.sound.sampled.Control;
import java.util.ArrayList;
import java.util.Stack;

public class RepeatStructure extends ControlStructure {
    private double myTimesToRepeat;
    private int myIndexOfList;

    @Override
    public double executeCode(){
            ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
            myTimesToRepeat=Double.parseDouble(simplifiedLine.get(myStartingIndex+1));
            myIndexOfList=myStartingIndex+2;
            if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
            for(int k=0; k<myTimesToRepeat; k++) {
                evaluateLineSection(myIndexOfList, simplifiedLine);
            }
            //return last command result
    }
    }


