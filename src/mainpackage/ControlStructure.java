package mainpackage;

import javax.sound.sampled.Control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class ControlStructure {
    ProgramParser myParser;
    SystemStorage myStorage;
    int myStartingIndex;
    ArrayList<String> myUserInput;
    List<Command> myCommands;
    int myNumOfListArguments; //defined by default ** DO NOT FORGET TO SET THISs

    public ControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        myNumOfListArguments=numOfListArguments;
        myParser=parser;
        myStorage=storage;
        myUserInput=new ArrayList<>();
        myCommands=new ArrayList<>();
    }

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key
    public void initializeStructure(int startingIndex, ArrayList<String> userInput){
        myStartingIndex=startingIndex;
        myUserInput=userInput;
    }

    protected List<Command> simplifyLineSection(int startingIndex) {
        List<Command> nestedCommands=new ArrayList<>();

        String firstEntry = myUserInput.get(startingIndex);
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            List<Command> parsedList=parseNestedControl("List", startingIndex);
            nestedCommands.addAll(parsedList);
        }
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))) {
            List<Command> parsedCommands=parseOperation(firstEntrySymbol, startingIndex);
            nestedCommands.addAll(parsedCommands);
        }
        return nestedCommands;
    }

    protected List<Command> parseNestedControl(String controlType, int currentIndex) {
        ControlStructure nestedControlStructure=new NoControlStructure(0, myParser, myStorage);
        if (controlType.equals("List")){
            nestedControlStructure=new CommandList(1, myParser, myStorage);
        }
        else nestedControlStructure = myParser.getControlStructure(controlType);
        nestedControlStructure.initializeStructure(currentIndex, myUserInput);
        double returnValue=nestedControlStructure.evaluateCode();
        nestedControlStructure.replaceCodeWithReturnValue(returnValue);
        List<Command> listOfCommands=nestedControlStructure.getMyCommands();
        return listOfCommands;
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected List<Command> parseOperation(String operationType, int currentIndex) {
        Operation defaultOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        ArrayList<Command> parsedCommands=new ArrayList<>();
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, myUserInput, currentIndex, myParser, builderStack);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            currentIndex = builder.getStartingIndex();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                Operation parsedOperation=builder.createOperation();
                if (parsedOperation instanceof Command) parsedCommands.add((Command) parsedOperation);
                double returnVal = parsedOperation.evaluate();
                replaceOperationWithReturnValue(parsedOperation, currentIndex, returnVal);
                builderStack.pop();
            } else builder.continueBuildingOperation();
        }
        return parsedCommands;
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

    public double evaluateCode(){
        convertCodeToCommands();
        if(myCommands.size()==0) return 0;
        else return myCommands.get(myCommands.size()-1).getReturnValue();
    }

    protected void convertCodeToCommands(){}

    public ArrayList<String> getMyUserInput(){
        return myUserInput;
    }

    public int getStartingIndex(){
        return myStartingIndex;
    }

    public List<Command> getMyCommands() {
        return myCommands;
    }
}
