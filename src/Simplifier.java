import java.util.ArrayList;

public class Simplifier {

    public String[] simplifyCommandArguments(ArrayList<String> currentLine, int currentIndex){
        int ArgumentsSimplified=0;
        int reducedValue=0;
        currentIndex++;
        while(ArgumentsSimplified<myNumberOfArguments){
            if(isImmediateOrVariable(currentLine.get(currentIndex))){
                currentIndex++;
            }
            else{
                reducedValue=checkExpressionTypeAndSimplify(currentLine, currentIndex);
                currentIndex=getCurrentIndex();
            }
            myArguments[ArgumentsSimplified]=reducedValue;
            ArgumentsSimplified++;
            reducedValue=0;

        }
        return commandArguments;
    }

    public int checkExpressionTypeAndSimplify(ArrayList<String> currentLine, int currentIndex){

    }
}
