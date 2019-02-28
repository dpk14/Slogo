import javax.sound.sampled.Control;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RepeatStructure extends ControlStructure {
    private double myTimesToRepeat;
    private int myIndexOfList;

    public RepeatStructure(int numOfListArguments){
        super(numOfListArguments);
    }

    @Override
    public double executeCode(){
            ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
            myTimesToRepeat=Double.parseDouble(simplifiedLine.get(myStartingIndex+1));
            myIndexOfList=myStartingIndex+2;
            if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
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


