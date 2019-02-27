import java.util.Stack;

public class ControlStructure {
    // Amanda ToDo:
    /** make constructor, make basic CntrlStruct commands, think about logic*/

    private void evaluateIndependentLine(int startingIndex) {
        String firstEntry=myCurrentLine.get(currentIndex);
        if(firstEntry.getSymbol().equals("Comment") || myCurrentLine.size()==0) return;
        if()
        else parseOperationAndUpdateIndex(currentIndex);

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

    private int parseOperationAndUpdateIndex(int currentIndex) {
        String operationTag = myCurrentLine.get(currentIndex);
        operationTag = mySymbols.get(operationTag);
        Operation defaultOperation = myOperationsMap.get(operationTag); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, myCurrentLine, currentIndex, operationTag);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                currentIndex=builder.performOperationAndSimplifyLine(builderStack, currentIndex);
            } else builder.continueBuildingOperation(builderStack);
        }
        return currentIndex;
    }

    public void executeCode(Stack<ControlStructure> controlStructureStack, ArrayList<ArrayList<String>> textBlock, int currentLineNumber, int currentIndex){
        //check next index. If it is a control structure, push it on stack

        //Each Control subclass inherits the above "push to stack conditional" through super.
        // Then this method is extended for the specific base case and expected structure of each ControlStructure
        // ** for instance, if some "base case" is met, the control structure executes its operations and is popped from the stack
    }
}
