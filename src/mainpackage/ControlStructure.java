package mainpackage;

import javax.sound.sampled.Control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.Stack;

public abstract class ControlStructure {
    ProgramParser myParser;
    SystemStorage myStorage;
    int myStartingIndex;
    ArrayList<String> mySimplifiableLine;
    ArrayList<String> mySavedLine;
    ControlStructure myOuterStructure;
    List<Entry<String, Animal>> myActiveAnimals;
    int myNumOfExpressionArguments;
    int myIndexOfFirstList;

    int myNumOfListArguments; //defined by default ** DO NOT FORGET TO SET THISs

    public ControlStructure(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        myNumOfExpressionArguments=numOfExpressionArguments;
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

    public void giveAnimals(List<Entry<String, Animal>> activeAnimals){
        myActiveAnimals=activeAnimals;
    }

    protected void resetSimplification(ArrayList<String> savedLine){
        mySimplifiableLine=savedLine;
        if(myOuterStructure==null) return;
        myOuterStructure.resetSimplification(savedLine);
    }

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key

    protected ArrayList<String> simplifyAndEvaluate(ArrayList<String> simplifiableLine, int startingIndex, List<Entry<String, Animal>> activeAnimals) {
        printTest(startingIndex, simplifiableLine);

        String firstEntry = simplifiableLine.get(startingIndex);
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            parseList(startingIndex, simplifiableLine, activeAnimals);
        }
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))) {
            parseOperation(firstEntrySymbol, startingIndex, simplifiableLine, activeAnimals);
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseList(int startingIndex, ArrayList<String> simplifiableLine, List<Entry<String, Animal>> activeAnimals) {
        startingIndex++;
        String currentEntry;
        int openBracketCount = 1;
        int closedBracketCount = 0;
        while (openBracketCount != closedBracketCount) {
            currentEntry = simplifiableLine.get(startingIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) parseNestedControl(currentEntrySymbol, startingIndex, simplifiableLine, activeAnimals);
            else if (myParser.isOperation(currentEntrySymbol)) {
                parseOperation(currentEntrySymbol, startingIndex, simplifiableLine, activeAnimals);
            } else ; //error
            startingIndex++;
            String updatedEntry = simplifiableLine.get(startingIndex);
            if (updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if (closedBracketCount + 3 == openBracketCount) ; //error, bracket imbalance
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseNestedControl(String controlType, int currentIndex, ArrayList<String> simplifiableLine, List<Entry<String, Animal>> activeAnimals) {
        ControlStructure defaultStructure = myParser.getControlStructure(controlType);
        ControlStructure nestedControlStructure=defaultStructure.copy();
        nestedControlStructure.initializeStructure(currentIndex, simplifiableLine, this);
        nestedControlStructure.giveAnimals(activeAnimals);
        double returnValue=nestedControlStructure.executeCode();
        nestedControlStructure.replaceCodeWithReturnValue(returnValue, simplifiableLine);
        return simplifiableLine;
    }


    //replaces any operation tag with the return value of that operation, simplifying the line section
    protected ArrayList<String> parseOperation(String operationType, int currentIndex, ArrayList<String> simplifiableLine, List<Entry<String, Animal>> activeAnimals) {
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
                double returnVal = operateOnAnimals(parsedOperation, activeAnimals);
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

        simplifiableLine.remove(myStartingIndex); //removes control tag

        for (int k=0; k<myNumOfExpressionArguments; k++){
            simplifiableLine.remove(myStartingIndex);
        }

        String currentEntry;
        for(int k=0; k<myNumOfListArguments; k++) {
            int openBracketCount=1;
            int closedBracketCount=0;
            simplifiableLine.remove(myStartingIndex); //removes first outer bracket of list currently being removed
            while (openBracketCount != closedBracketCount) {
                currentEntry = simplifiableLine.get(myStartingIndex);
                if (currentEntry.equals("[")) openBracketCount++;
                else if (currentEntry.equals("]")) closedBracketCount++;
                if (closedBracketCount + 3 == openBracketCount) ; //throw bracket imbalance error
                simplifiableLine.remove(myStartingIndex);
                //printTest(myStartingIndex, simplifiableLine);
            }
           // if (!simplifiableLine.get(myStartingIndex).equals("[")); //TODO: error: second list of structure must directly follow first list
        }
        simplifiableLine.add(myStartingIndex, Double.toString(returnValue));


        /*
        for(int k=0; k<myNumOfListArguments; k++) {
            while (!simplifiableLine.get(myStartingIndex).equals("]")) {
                simplifiableLine.remove(myStartingIndex);
                //if (simplifiableLine.size()==0) return simplifiableLine;
            }
            simplifiableLine.remove(myStartingIndex);
        }
        simplifiableLine.add(myStartingIndex, Double.toString(returnValue));
        */
        return simplifiableLine;
    }

    protected int findIndexOfEndBracket(int startingIndex, ArrayList<String> lineSection) {
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
        return currentIndex;
    }

    double operateOnAnimals(Operation operation, List<Entry<String, Animal>> activeAnimals){
        double returnVal=0;
        for(Entry entry : activeAnimals){
            Animal animal=(Animal) entry.getValue();
            returnVal=operation.executeCode(animal);
        }
        return returnVal;
    }

    public double executeCode(){
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        int previousSize=previousCommandLog.size();
        simplifyAndExecuteStructure();
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        int currentSize=currentCommandLog.size();
        if(previousSize!=currentSize){
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
