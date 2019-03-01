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
    ArrayList<String> mySimplifiableLine;
    ArrayList<String> mySavedLine;
    ControlStructure myOuterStructure;
    int myIndexOfList;

    int myNumOfListArguments; //defined by default ** DO NOT FORGET TO SET THISs

    public ControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        myNumOfListArguments=numOfListArguments;
        myParser=parser;
        myStorage=storage;
    }

    public abstract ControlStructure copy();

    public void initializeStructure(int startingIndex, ArrayList<String> currentLineSection, ControlStructure OuterStructure){
        myStartingIndex=startingIndex;
        mySimplifiableLine=currentLineSection;
        mySavedLine=new ArrayList<>(currentLineSection);
        myOuterStructure=OuterStructure;
        if (myOuterStructure==null) System.out.println("null");
        System.out.println("yeet");
    }

    protected void resetSimplification(ArrayList<String> savedLine){
        mySimplifiableLine=savedLine;
        if(myOuterStructure==null) return;
        myOuterStructure.resetSimplification(savedLine);
    }

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key

    protected ArrayList<String> simplifyAndEvaluate(ArrayList<String> simplifiableLine, int startingIndex) {
        printTest(startingIndex, simplifiableLine);

        String firstEntry = simplifiableLine.get(startingIndex);
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            simplifiableLine=parseList(startingIndex, simplifiableLine);
        }
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))) {
            simplifiableLine = parseOperation(firstEntrySymbol, startingIndex, simplifiableLine);
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseList(int startingIndex, ArrayList<String> simplifiableLine) {
        startingIndex++;
        String currentEntry;
        int openBracketCount = 1;
        int closedBracketCount = 0;
        while (openBracketCount != closedBracketCount) {
            currentEntry = simplifiableLine.get(startingIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) parseNestedControl(currentEntrySymbol, startingIndex, simplifiableLine);
            else if (myParser.isOperation(currentEntrySymbol)) {
                simplifiableLine=parseOperation(currentEntrySymbol, startingIndex, simplifiableLine);
            } else ; //error
            startingIndex++;
            String updatedEntry = simplifiableLine.get(startingIndex);
            if (updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if (closedBracketCount + 3 == openBracketCount) ; //error, bracket imbalance
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseNestedControl(String controlType, int currentIndex, ArrayList<String> simplifiableLine) {
        ControlStructure defaultStructure = myParser.getControlStructure(controlType);
        ControlStructure nestedControlStructure=defaultStructure.copy();
        nestedControlStructure.initializeStructure(currentIndex, simplifiableLine, this);
        double returnValue=nestedControlStructure.executeCode();
        nestedControlStructure.replaceCodeWithReturnValue(returnValue, simplifiableLine);
        return simplifiableLine;
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected ArrayList<String> parseOperation(String operationType, int currentIndex, ArrayList<String> simplifiableLine) {
        Operation defaultOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, simplifiableLine, currentIndex, myParser, builderStack);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            currentIndex = builder.getStartingIndex();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                Operation parsedOperation=builder.createOperation();
                if (parsedOperation instanceof Command) parsedOperation.storeCommand();
                double returnVal = parsedOperation.execute();
                replaceOperationWithReturnValue(parsedOperation, currentIndex, returnVal, simplifiableLine);
                builderStack.pop();
            } else builder.continueBuildingOperation();
        }
        return simplifiableLine;
    }

    protected double replaceOperationWithReturnValue(Operation operation, int currentIndex, double returnVal, ArrayList<String> simplifiableLine) {
        for (int k = -1; k < operation.getNumArgs(); k++) {
            simplifiableLine.remove(currentIndex);
        }
        simplifiableLine.add(currentIndex, Double.toString(returnVal));
        return returnVal;
    }

    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue, ArrayList<String> simplifiableLine){

        for(int k=0; k<myNumOfListArguments; k++) {
            while (!simplifiableLine.get(myStartingIndex).equals("]")) {
                simplifiableLine.remove(myStartingIndex);
                if (simplifiableLine.size()==0) return simplifiableLine;
            }
        }
        simplifiableLine.remove(myStartingIndex);
        simplifiableLine.add(myStartingIndex, Double.toString(returnValue));
        return simplifiableLine;
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

    public double executeCode(){
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        simplifyAndExecuteStructure();
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }

    protected void simplifyAndExecuteStructure(){}

    public void printTest(int index, ArrayList<String> line){
        for (String s:line){
            System.out.printf("%s ", s);
        }
        System.out.printf("\n%d\n", index);
    }

    public ArrayList<String> getMySimplifiableLine(){
        return mySimplifiableLine;
    }
}
