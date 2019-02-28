import java.util.*;
import java.util.regex.Pattern;

public class OperationBuilder {
    ArrayList<String> myUserInput;
    Operation myOperation;
    String myOperationMarker;
    int myNumOfArgsNeeded;
    int myNumOfArgsFilled;
    String[] myOperationArguments;
    ProgramParser myParser;



    public OperationBuilder(Operation defaultOperation, ArrayList<String> userInput, String operationMarker, ProgramParser parser){
        myUserInput=userInput;
        myOperation=defaultOperation.copy();
        myOperationMarker=operationMarker;
        myNumOfArgsNeeded=myOperation.getNumArgs();
        myOperationArguments=new String[myNumOfArgsNeeded];
        myParser=parser;
    }

    public void continueBuildingOperation(Stack<OperationBuilder> builderStack) {
        int builderIndex = myUserInput.indexOf(myOperationMarker);
        for (int k = 0; k < myNumOfArgsNeeded; k++) {
            String kthArgument = myUserInput.get(builderIndex + k);
            String kthArgumentSymbol = myParser.getSymbol(kthArgument);
            if (kthArgumentSymbol.equals("Variable")) myOperationArguments[k] = myParser.parseVariable(kthArgument);
            else if (kthArgumentSymbol.equals("Constant")) myOperationArguments[k] = kthArgument;
            else {
                Operation defaultOperation = myParser.getOperation(kthArgumentSymbol);
                builderStack.push(new OperationBuilder(defaultOperation, myUserInput, kthArgument, myParser));
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
            myUserInput.remove(currentIndex);
        }

        myUserInput.add(currentIndex, Double.toString(returnVal));
        if (myOperation instanceof Command) myOperation.Log();
        builderStack.pop();
        return returnVal;
    }

    /*
        if(builderStack.size()==1) currentIndex+=myNumOfArgsFilled;
        else{
            for (int k = 1; k < myOperation.getNumArgs(); k++) {
                myUserInput.remove(currentIndex);
            }
        }
        */

    public int getMyNumOfArgsFilled() {
        return myNumOfArgsFilled;
    }

    public int getMyNumOfArgsNeeded(){
        return myNumOfArgsNeeded;
    }
}
