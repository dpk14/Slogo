package mainpackage;

import java.util.*;

public class OperationBuilder {
    ArrayList<String> myUserInput;
    Operation myOperation;
    int myStartingIndex;
    int myNumOfArgsNeeded;
    int myNumOfArgsFilled;
    String[] myOperationArguments;
    ProgramParser myParser;
    Stack myBuilderStack;

    public OperationBuilder(Operation defaultOperation, ArrayList<String> userInput, int startingIndex, ProgramParser parser, Stack builderStack){
        myUserInput=userInput;
        myOperation=defaultOperation.copy();
        myStartingIndex=startingIndex;
        myNumOfArgsNeeded=myOperation.getNumArgs();
        myOperationArguments=new String[myNumOfArgsNeeded];
        myParser=parser;
        myBuilderStack=builderStack;
    }

    /*
    operationBuilders simplify the code following an operation tag until they fully fill the operation with simplified, variable or constant arguments.
    For instance, SUM expects two arguments, so the operationBuilder will simplify the first argument into a variable or constant, fill
    the first argument of the Operation with that value, then simplify the second argument and fill it. ContinueBuildingOperation does most of this work.
    It uses a stack, and pushes a nested operation to the front of the stack to be simplified if an operation tag is detected in an argument spot
    instead of a variable or constant. The builder stack and stack loop is created in the parseOperation method of ControlStructure. See this method
    to understand how this is performed
     */


    public void continueBuildingOperation() {
        for (int k = 0; k < myNumOfArgsNeeded; k++) {
            String kthArgument = myUserInput.get(myStartingIndex + 1 + k);
            String kthArgumentSymbol = myParser.getSymbol(kthArgument);
            if (kthArgumentSymbol.equals("Variable") || kthArgumentSymbol.equals("Constant")) {
                if (myOperationArguments[k] == null) myNumOfArgsFilled++;
                myOperationArguments[k] = kthArgument;
            }
            else {
                Operation defaultOperation = myParser.getOperation(kthArgumentSymbol);
                myBuilderStack.push(new OperationBuilder(defaultOperation, myUserInput, myStartingIndex+k+1, myParser, myBuilderStack));
                break;
            }
        }
    }

    //complete the construction of the operation by executing it, then:

    //retain the most simplified version of the operation, which will exist when there is only one operationBuilder left in the stack. Then advance the current index to the end of the command
    //if the stack is bigger than 1, it can still be simplified further, so simplify the currentLine by removing the current expression from the list and replacing it with a simplified value.
    public Operation createOperation() {
        myOperation.setArgs(new ArrayList<>(Arrays.asList(myOperationArguments)));
        return myOperation;
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

    public int getStartingIndex(){
        return myStartingIndex;
    }

}
