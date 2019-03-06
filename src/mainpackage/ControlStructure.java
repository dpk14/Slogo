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
    Animal myAnimal;
    int myNumOfExpressionArguments;
    int myIndexOfFirstList;
    boolean isRepeatable;

    int myNumOfListArguments; //defined by default ** DO NOT FORGET TO SET THISs

    public ControlStructure(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        myNumOfExpressionArguments=numOfExpressionArguments;
        myNumOfListArguments=numOfListArguments;
        myParser=parser;
        myStorage=storage;
        isRepeatable=true;
    }

    public abstract ControlStructure copy();

    /*
    This secondary constructor tells the specific ControlStructure-- which has been copied from its default case -- the specific
    location on the UserInput line it begins, what animal it is operating on, and a pointer to the ControlStructure it is nested inside.
    This pointer allows nested structures to give a simplified version of themselves back to all of their outer control structures, as well as restore
    a saved, past version of themselves to be modified again by the outer structures. See resetSimplification for an example.
     */

    public void initializeStructure(int startingIndex, ArrayList<String> currentLineSection, ControlStructure OuterStructure, Animal animal){
        myStartingIndex=startingIndex;
        mySimplifiableLine=currentLineSection;
        mySavedLine=new ArrayList<>(currentLineSection);
        myOuterStructure=OuterStructure;
        myAnimal=animal;
        if (myOuterStructure==null) System.out.println("null");
        System.out.println("yeet");
    }

    protected void resetSimplification(ArrayList<String> savedLine){
        mySimplifiableLine=savedLine;
        if(myOuterStructure==null) return;
        myOuterStructure.resetSimplification(savedLine);
    }

    /*
     The method evaluateInput in Main repeats the current control structure over and over again for all turtles the "Tell" command declared active.
     "Tell," which modifies the amount of turtles active, should not be repeated for all turtles, because it is declaring a new
     set of active turtles to be repeated. Ask and AskWith should also not be repeated for the main active turtles, because
     they are operating on a different set of turtles. Because these structures are not repeatable, they call the below method
     declareUnrepeatable() to set the outermost structure as unrepeatable, so that the loop in evaluateInput will only process this code once.
      */

    protected void declareUnrepeatable(){
        if(myOuterStructure==null) {
            isRepeatable=false;
            return;
        }
        myOuterStructure.declareUnrepeatable();
    }

    //gives the textBlock in which to apply the control structure, as well as the index and line of the control structure key

    protected ArrayList<String> simplifyAndEvaluate(ArrayList<String> simplifiableLine, int startingIndex, Animal activeAnimal) {
        printTest(startingIndex, simplifiableLine);

        String firstEntry = simplifiableLine.get(startingIndex);
        String firstEntrySymbol=myParser.getSymbol(firstEntry);
        if (firstEntry.equals("[")) {
            parseList(startingIndex, simplifiableLine, activeAnimal);
        }
        else if(!(firstEntrySymbol.equals("Variable") || firstEntrySymbol.equals("Constant"))) {
            parseOperation(firstEntrySymbol, startingIndex, simplifiableLine, activeAnimal);
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseList(int startingIndex, ArrayList<String> simplifiableLine, Animal activeAnimal) {
        startingIndex++;
        String currentEntry;
        int openBracketCount = 1;
        int closedBracketCount = 0;
        while (openBracketCount != closedBracketCount) {
            currentEntry = simplifiableLine.get(startingIndex);
            String currentEntrySymbol = myParser.getSymbol(currentEntry);
            if (myParser.isControl(currentEntrySymbol)) parseNestedControl(currentEntrySymbol, startingIndex, simplifiableLine, activeAnimal);
            else if (myParser.isOperation(currentEntrySymbol)) {
                parseOperation(currentEntrySymbol, startingIndex, simplifiableLine, activeAnimal);
            } else ; //error
            startingIndex++;
            String updatedEntry = simplifiableLine.get(startingIndex);
            if (updatedEntry.equals("[")) openBracketCount++;
            else if (updatedEntry.equals("]")) closedBracketCount++;
            if (closedBracketCount + 3 == openBracketCount) ; //error, bracket imbalance
        }
        return simplifiableLine;
    }

    protected ArrayList<String> parseNestedControl(String controlType, int currentIndex, ArrayList<String> simplifiableLine, Animal activeAnimal) {
        ControlStructure defaultStructure = myParser.getControlStructure(controlType);
        ControlStructure nestedControlStructure=defaultStructure.copy();
        nestedControlStructure.initializeStructure(currentIndex, simplifiableLine, this, activeAnimal);
        double returnValue=nestedControlStructure.executeCode();
        nestedControlStructure.replaceCodeWithReturnValue(returnValue, simplifiableLine);
        return simplifiableLine;
    }


    /*
    replaces any operation tag with the return value of that operation, simplifying the line section

    to understand this method better, first read continueBuildingOperation in the OperationBuilder class, then come back here and read
    the below lines.

    If the builder at the top top of the builder stack has filled its arguments, the operation is created and executed, then the builder is popped.
    The operation is then removed from the line and replaced with its returnValue
    */

    protected ArrayList<String> parseOperation(String operationType, int currentIndex, ArrayList<String> simplifiableLine, Animal activeAnimal) {
        Operation defaultOperation = myParser.getOperation(operationType); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack = new Stack<OperationBuilder>();
        OperationBuilder builder = new OperationBuilder(defaultOperation, simplifiableLine, currentIndex, myParser, builderStack);
        builderStack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            currentIndex = builder.getStartingIndex();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                Operation parsedOperation=builder.createOperation();
                if (parsedOperation instanceof Command) {
                    parsedOperation.storeCommand();
                    parsedOperation.setAnimal(activeAnimal);
                }
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
            else if (currentIndex==lineSection.size()-startingIndex-1) return -1;
            if(closedBracketCount+3==openBracketCount); //throw bracket imbalance error
        }
        return currentIndex;
    }

    /* called by an outer ControlStructure or by evaluateInput in main, executeCode() actually executes a ControlStructure's associated
     code chunk, returning the value of the last command made. It always calls simplifyAndExecuteStructure, the method extended
    in each ControlStructure subclass to contain the specific rules defining how the subclass processes, visits,
    and evaluates its code chunk
    */

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

    protected boolean repeatable(){
        return isRepeatable;
    }

}
