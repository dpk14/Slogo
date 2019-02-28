import java.util.*;
import java.util.regex.Pattern;

public class OperationBuilder {
    ArrayList<String> myCurrentLine;
    Operation myOperation;
    String myOperationMarker;
    int myNumOfArgsNeeded;
    int myNumOfArgsFilled;
    String[] myOperationArguments;
    ProgramParser myParser;



    public OperationBuilder(Operation defaultOperation, ArrayList<String> currentLine, String operationMarker, ProgramParser parser){
        myCurrentLine=currentLine;
        myOperation=defaultOperation.copy();
        myOperationMarker=operationMarker;
        myNumOfArgsNeeded=myOperation.getNumArgs();
        myOperationArguments=new String[myNumOfArgsNeeded];
        myParser=parser;
    }

    public void continueBuildingOperation(Stack<OperationBuilder> builderStack) {
        int builderIndex = myCurrentLine.indexOf(myOperationMarker);
        for (int k = 0; k < myNumOfArgsNeeded; k++) {
            String kthArgument = myCurrentLine.get(builderIndex + k);
            String kthArgumentSymbol = myParser.getSymbol(kthArgument);
            if (kthArgumentSymbol.equals("Variable")) myOperationArguments[k] = myParser.parseVariable(kthArgument);
            else if (kthArgumentSymbol.equals("Constant")) myOperationArguments[k] = kthArgument;
            else {
                Operation defaultOperation = myOperationsMap.get(kthArgumentSymbol);
                builderStack.push(new OperationBuilder(defaultOperation, myCurrentLine, kthArgument, mySymbols, myOperationsMap));
                break;
            }
        }
    }

    //complete the construction of the operation by executing it, then:

    //retain the most simplified version of the operation, which will exist when there is only one operationBuilder left in the stack. Then advance the current index to the end of the command
    //if the stack is bigger than 1, it can still be simplified further, so simplify the currentLine by removing the current expression from the list and replacing it with a simplified value.
    public double performOperationAndSimplifyLine(Stack<OperationBuilder> builderStack, int currentIndex) {
        double returnVal=myOperation.execute();

        for (int k = 1; k < myOperation.getNumArgs(); k++) {
            myCurrentLine.remove(currentIndex);
        }
        /*
        if(builderStack.size()==1) currentIndex+=myNumOfArgsFilled;
        else{
            for (int k = 1; k < myOperation.getNumArgs(); k++) {
                myCurrentLine.remove(currentIndex);
            }
        }
        */

        myCurrentLine.add(currentIndex, Double.toString(returnVal));
        if (myOperation instanceof Command) myCommandLog.add(myOperation);
        builderStack.pop();
        return returnVal;
    }

    public int getMyNumOfArgsFilled() {
        return myNumOfArgsFilled;
    }

    public int getMyNumOfArgsNeeded(){
        return myNumOfArgsNeeded;
    }
}
