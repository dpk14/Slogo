import java.util.ArrayList;
import java.util.Stack;

public class ControlStructure {
    int myStartingIndex;
    int myStartingLine;
    ArrayList<ArrayList<String>> myTextBlock=new ArrayList<ArrayList<String>>();
    // Amanda ToDo:
    /** make constructor, make basic CntrlStruct commands, think about logic*/

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key
    public void initializeStructure(int startingIndex, int startingLine,  ArrayList<ArrayList<String>> textBlock){
        myStartingIndex=startingIndex;
        myStartingLine=startingLine;
        myTextBlock=textBlock;
    }

    private void evaluateLineSection(int startingIndex, ArrayList<String> currentLine) {
        ArrayList<String> simplifiedLineSection=new ArrayList<String>(currentLine);
        String firstEntry=currentLine.get(startingIndex);
        if(firstEntry.getSymbol().equals("Comment") || simplifiedLineSection.size()==0) return;
        if(firstEntry.equals("[")){
            int currentIndex=startingIndex;
            while(!currentLine.get(currentIndex).equals("]"))
        }
        else parseOperation(currentIndex, simplifiedLineSection);

        /*
        boolean isList=currentLine.get(currentIndex).equals("[");
        if(isList) currentIndex++; //skips over bracket
        if (!currentLine.contains("]")); //throw error
        int commandCount=0;
        while(!currentLine.get(currentIndex).equals("]")) {
            if(!isList && commandCount==1); //throw error, bc if it isn't a list, shouldn't be more than one command
            currentIndex=parseCommand(currentIndex);
            commandCount++;
        }
        */
    }

    //replaces any operation tag with the return value of that operation, simplifying the line section
    private double parseOperation(int currentIndex, ArrayList<String> simplifiedLineSection) {
        double operationReturnValue=0;
        String operationTag = simplifiedLineSection.get(currentIndex);
        operationTag = mySymbols.get(operationTag);
        Operation defaultOperation = myOperationsMap.get(operationTag); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, myCurrentLine, currentIndex, operationTag);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                operationReturnValue=builder.performOperationAndSimplifyLine(builderStack, currentIndex);
            } else builder.continueBuildingOperation(builderStack);
        }
        return operationReturnValue;
    }

    public void executeCode(Stack<ControlStructure> controlStructureStack, ArrayList<ArrayList<String>> textBlock){

        //check next index. If it is a control structure, push it on stack

        //Each Control subclass inherits the above "push to stack conditional" through super.
        // Then this method is extended for the specific base case and expected structure of each ControlStructure
        // ** for instance, if some "base case" is met, the control structure executes its operations and is popped from the stack
    }
}
