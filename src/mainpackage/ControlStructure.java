package mainpackage;

import javax.sound.sampled.Control;
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
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) parseNestedControl("List", startingIndex, simplifiedLineSection);
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))){
            simplifiedLineSection=parseOperation(firstEntrySymbol, startingIndex, simplifiedLineSection);
        }
        return simplifiedLineSection;
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected ArrayList<String> parseOperation(String operationType, int currentIndex, ArrayList<String> simplifiedLineSection) {
        Operation defaultOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, simplifiedLineSection, currentIndex, myParser, builderStack);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                currentIndex=builder.getStartingIndex();
                simplifiedLineSection=builder.performOperationAndSimplifyLine(currentIndex);
                builderStack.pop();
            } else builder.continueBuildingOperation();
        }
        return simplifiedLineSection;
    }

    protected ArrayList<String> parseNestedControl(String controlType, int currentIndex, ArrayList<String> simplifiedLineSection) {
        ControlStructure nestedControlStructure=new NoControlStructure(0, myParser, myStorage);
        if (controlType.equals("List")){
            nestedControlStructure=new CommandList(1, myParser, myStorage);
        }
        else nestedControlStructure = myParser.getControlStructure(controlType);
        nestedControlStructure.initializeStructure(currentIndex, simplifiedLineSection);
        double returnValue=nestedControlStructure.executeCode();
        simplifiedLineSection=nestedControlStructure.replaceCodeWithReturnValue(nestedControlStructure.getStartingIndex(), simplifiedLineSection, returnValue);
        return simplifiedLineSection;
    }

    protected ArrayList<String> replaceCodeWithReturnValue(int startingIndex, ArrayList<String> simplifiedLineSection, double returnValue){
        for(int k=0; k<myNumOfListArguments; k++) {
            while (!simplifiedLineSection.get(startingIndex).equals("]")) {
                simplifiedLineSection.remove(startingIndex);
                if (simplifiedLineSection.size()==0) return simplifiedLineSection;
            }
        }
        simplifiedLineSection.remove(startingIndex);
        simplifiedLineSection.add(startingIndex, Double.toString(returnValue));
        return simplifiedLineSection;
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
        currentIndex++;
        return currentIndex;
    }

    public ArrayList<String> getMyUserInput(){
        return myUserInput;
    }

    public int getStartingIndex(){
        return myStartingIndex;
    }

    public abstract double executeCode();

        //check next index. If it is a control structure, push it on stack

        //Each Control subclass inherits the above "push to stack conditional" through super.
        // Then this method is extended for the specific base case and expected structure of each ControlStructure
        // ** for instance, if some "base case" is met, the control structure executes its operations and is popped from the stack

}
