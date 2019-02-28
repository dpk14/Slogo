import java.util.ArrayList;
import java.util.Stack;

public abstract class ControlStructure {
    ProgramParser myParser;
    SystemStorage myStorage;
    int myStartingIndex;
    ArrayList<String> myUserInput=new ArrayList<>();
    int myNumOfListArguments; //defined by default ** DO NOT FORGET TO SET THISs

    public ControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        myNumOfListArguments=numOfListArguments;
        myParser=parser;
        myStorage=storage;
    }

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key
    public void initializeStructure(int startingIndex, ArrayList<String> userInput){
        myStartingIndex=startingIndex;
        myUserInput=userInput;
    }

    protected ArrayList<String> evaluateLineSection(int startingIndex, ArrayList<String> lineSection) {
        ArrayList<String> simplifiedLineSection = new ArrayList<String>(lineSection);
        String firstEntry = simplifiedLineSection.get(startingIndex);
        firstEntry=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            int currentIndex = startingIndex;
            while (!simplifiedLineSection.get(currentIndex + 1).equals("]")) {
                String nextEntry=simplifiedLineSection.get(currentIndex+1);
                nextEntry=myParser.getSymbol(nextEntry);
                if(myParser.isControl(nextEntry)) parseNestedControl(nextEntry, currentIndex+1, simplifiedLineSection);
                else if (myParser.isOperation(nextEntry)) parseOperation(nextEntry, currentIndex+1, simplifiedLineSection);
                else; //error
                currentIndex++;
            }
        } else if(!(firstEntry.equals("Variable")|| firstEntry.equals("Constant"))){
            parseOperation(firstEntry, startingIndex, simplifiedLineSection);
        }
        return simplifiedLineSection;
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected double parseOperation(String operationType, int currentIndex, ArrayList<String> simplifiedLineSection) {
        double operationReturnValue=0;
        Operation defaultOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, simplifiedLineSection, operationType, myParser);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                operationReturnValue=builder.performOperationAndSimplifyLine(builderStack, currentIndex);
            } else builder.continueBuildingOperation(builderStack);
        }
        return operationReturnValue;
    }

    protected void parseNestedControl(String controlType, int currentIndex, ArrayList<String> simplifiedLineSection) {
        ControlStructure nestedControlStructure = myParser.getControlStructure(controlType); //will automatically throw error if doesn't work
        nestedControlStructure.initializeStructure(currentIndex, simplifiedLineSection);
        nestedControlStructure.executeCode();
        nestedControlStructure.removeAndAdvance(currentIndex, simplifiedLineSection);
        }

    protected int removeAndAdvance(int currentIndex, ArrayList<String> simplifiedLineSection){
        for(int k=0; k<myNumOfListArguments; k++) {
            while (!simplifiedLineSection.get(currentIndex).equals("]")) {
                simplifiedLineSection.remove(currentIndex);
            }
        }
        simplifiedLineSection.add(currentIndex, "SIMPLIFIED_CONTROL");
        return currentIndex;
    }


    protected int findIndexOfNextList(int startingIndex) {
        int currentIndex = startingIndex;
        String currentInput;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount){
            currentIndex++;
            currentInput=myUserInput.get(currentIndex);
            if(currentInput.equals("[")) openBracketCount++;
            else if (currentInput.equals("]")) closedBracketCount++;
            if(closedBracketCount+3==openBracketCount); //throw bracket imbalance error
        }
        return currentIndex++;
    }

    public abstract double executeCode();

        //check next index. If it is a control structure, push it on stack

        //Each Control subclass inherits the above "push to stack conditional" through super.
        // Then this method is extended for the specific base case and expected structure of each ControlStructure
        // ** for instance, if some "base case" is met, the control structure executes its operations and is popped from the stack

}
