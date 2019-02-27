import javax.sound.sampled.Control;
import java.util.ArrayList;
import java.util.Stack;

public class RepeatStructure extends ControlStructure {
    private double myTimesToRepeat;
    private int myIndexOfList;

    @Override
    public void executeCode(){
            ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1);
            myTimesToRepeat=Double.parseVariableOrDouble(simplifiedLine.get(myStartingIndex+1));
            myIndexOfList=myStartingIndex+2;
            if (!simplifiedLine.get( myStartingIndex+2).equals("[")); //throw error
            for(int k=0; k<myTimesToRepeat; k++) {
                evaluateLineSection(myIndexOfList, simplifiedLine);
            }
            simplifyLine(simplifiedLine);
    }
    }


