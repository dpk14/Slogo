import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class OperationBuilder {
    ArrayList<String> myCurrentLine;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private HashMap<String, Operation> myOperationsMap;
    Operation myOperation;
    String myOperationMarker;
    int myNumOfArgsNeeded;
    int myNumOfArgsFilled;
    String[] myOperationArguments;


    public OperationBuilder(Operation defaultOperation, ArrayList<String> currentLine, String operationMarker, List<Map.Entry<String, Pattern>> symbols, HashMap<String, Operation> operations){
        myCurrentLine=currentLine;
        myOperation=defaultOperation.copy();
        myOperationMarker=operationMarker;
        myNumOfArgsNeeded=myOperation.getNumOfArgs();
        myOperationArguments=new String[myNumOfArgsNeeded];
        myOperationsMap=operations;
        mySymbols=symbols;
    }

    public void continueBuildingOperation(Stack<OperationBuilder> builderStack) {
        int builderIndex = myCurrentLine.indexOf(myOperationMarker);
        for (int k = 0; k < myNumOfArgsNeeded; k++) {
            String kthArgument = myCurrentLine.get(builderIndex + k);
            String kthArgumentSymbol = mySymbols.get(kthArgument);
            if (kthArgumentSymbol.equals("Variable")) myOperationArguments[k] = variableToConstant(kthArgument);
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
    public int performOperationAndSimplifyLine(Stack<OperationBuilder> builderStack, int currentIndex) {
        double returnVal=myOperation.execute();
        if(builderStack.size()==1) currentIndex+=myNumOfArgsFilled;
        else{
            for (int k = 1; k < myOperation.getNumOfArgs(); k++) {
                myCurrentLine.remove(currentIndex);
            }
        }
        myCurrentLine.add(currentIndex, returnVal.toString());
        if (myOperation instanceof Command) myCommandLog.add(myOperation);
        builderStack.pop();
        return currentIndex;
    }

    public int getMyNumOfArgsFilled() {
        return myNumOfArgsFilled;
    }

    public int getMyNumOfArgsNeeded(){
        return myNumOfArgsNeeded;
    }
}
