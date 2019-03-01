package mainpackage;

import javax.sound.sampled.Control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public abstract class ControlStructure {
    ProgramParser myParser;
    SystemStorage myStorage;
    int myStartingIndex;
    ArrayList<String> myUserInput=new ArrayList<>();
    ArrayList<Command> myNestedCommands=new ArrayList<>();
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

    protected void simplifyLineSection(int startingIndex) {
        String firstEntry = myUserInput.get(startingIndex);
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            parseNestedControl("List", startingIndex);
        }
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))){
            Operation operation=parseOperation(firstEntrySymbol, startingIndex);
            if (operation instanceof Command) {
                myNestedCommands.add((Command) operation);
                operation.storeCommand();
            }
        }
    }

    protected ArrayList<Command> parseNestedControl(String controlType, int currentIndex) {
        ControlStructure nestedControlStructure=new NoControlStructure(0, myParser, myStorage);
        if (controlType.equals("List")){
            nestedControlStructure=new CommandList(1, myParser, myStorage);
        }
        else nestedControlStructure = myParser.getControlStructure(controlType);
        nestedControlStructure.initializeStructure(currentIndex, myUserInput);
        double returnValue=nestedControlStructure.executeCode();
        nestedControlStructure.replaceCodeWithReturnValue(returnValue);
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected Operation parseOperation(String operationType, int currentIndex) {
        Operation emptyOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(emptyOperation, myUserInput, currentIndex, myParser, builderStack);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                builder.completeBuild();
                Operation parsedOperation=builder.getMyOperation();
                int operationIndex = builder.getStartingIndex();
                double returnVal = parsedOperation.execute();
                replaceOperationWithReturnValue(parsedOperation, operationIndex, returnVal);
                builderStack.pop();
                return parsedOperation;
            } else builder.continueBuildingOperation();
        }
        return emptyOperation;
    }

    protected double replaceOperationWithReturnValue(Operation operation, int currentIndex, double returnVal) {

        for (int k = -1; k < operation.getNumArgs(); k++) {
            myUserInput.remove(currentIndex);
        }

        myUserInput.add(currentIndex, Double.toString(returnVal));
        return returnVal;
    }

    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue){
        for(int k=0; k<myNumOfListArguments; k++) {
            while (!myUserInput.get(myStartingIndex).equals("]")) {
                myUserInput.remove(myStartingIndex);
                if (myUserInput.size()==0) return myUserInput;
            }
        }
        myUserInput.remove(myStartingIndex);
        myUserInput.add(myStartingIndex, Double.toString(returnValue));
        return myUserInput;
    }


    protected int findIndexOfNextList(int startingIndex, ArrayList<String> lineSection) {
        int currentIndex = startingIndex;
        String currentInput;
        int openBracketCount=1;
        int closedBracketCount=0;
        while(openBracketCount!=closedBracketCount){
            currentIndex++;
            currentInput=lineSection.get(currentIndex);
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
