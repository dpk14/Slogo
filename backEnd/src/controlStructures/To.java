package controlStructures;

import mainpackage.ProgramParser;
import mainpackage.SystemStorage;

import java.util.ArrayList;

public class To extends ControlStructure {
    private int myIndexOfSecondList;
    boolean successful;

    public To (int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
        successful=true;
    }

    @Override
    public ControlStructure copy() {
        return new To(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        String commandName=mySimplifiableLine.get(myStartingIndex+1);
        myIndexOfFirstList=myStartingIndex+2;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ; //throw error
        int currentIndex=myIndexOfFirstList;
        ArrayList<String> myVariableList=new ArrayList<>();

        while (!mySimplifiableLine.get(currentIndex).equals("]")){
            String currentEntry=mySimplifiableLine.get(currentIndex);
            if (!currentEntry.contains(":")){
                successful=false;
                return;
            } //TODO: variable list must contain only variables
            myParser.removeColon(currentEntry);
            myVariableList.add(currentEntry);
            currentIndex++;
        }
        currentIndex++;
        myIndexOfSecondList = currentIndex;
        if (!mySimplifiableLine.get(myIndexOfSecondList).equals("[")) ; //throw error
        if (findIndexOfEndBracket(myIndexOfSecondList, mySimplifiableLine)<0); //TODO no endbracket errors

        ArrayList<String> myCommandList=new ArrayList<>();
        while(!mySimplifiableLine.get(currentIndex).equals("]")){
            myCommandList.add(mySimplifiableLine.get(currentIndex));
            currentIndex++;
        }
        myCommandList.add(mySimplifiableLine.get(currentIndex));
        myParser.getControlMap().put(commandName, new UserDefinedCommand(myVariableList.size(), 0, myParser, myStorage, myVariableList, myCommandList));
    }

    @Override
    public double executeCode(){
        simplifyAndExecuteStructure();
        if (successful) return 1;
        return 0;
    }
}