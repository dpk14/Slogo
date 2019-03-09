package controlStructures;

import general.ProgramParser;
import general.SystemStorage;
import java.util.ArrayList;

public class MakeUserInstruction extends ControlStructure {
    private int myIndexOfSecondList;
    boolean successful;

    public MakeUserInstruction(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
        successful=true;
    }

    @Override
    public ControlStructure copy() {
        return new MakeUserInstruction(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        String commandName=mySimplifiableLine.get(myStartingIndex+1);
        myIndexOfFirstList=myStartingIndex+2;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ; //throw error
        int currentIndex=myIndexOfFirstList;
        ArrayList<String> myVariableList=new ArrayList<>();

        currentIndex++;
        while (!mySimplifiableLine.get(currentIndex).equals("]")){
            String currentEntry=mySimplifiableLine.get(currentIndex);
            String symbol=myParser.getSymbol(currentEntry);
            if (!symbol.equals("Variable")){
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
        int end=findIndexOfEndBracket(myIndexOfSecondList, mySimplifiableLine);
        if (end<0);//TODO no endbracket errors

        ArrayList<String> myCommandList=new ArrayList<>();
        for(currentIndex=myIndexOfSecondList+1; currentIndex<=end; currentIndex++){
            myCommandList.add(mySimplifiableLine.get(currentIndex));
        }
        if (myStage.equals("execute")) {
            myParser.getControlMap().put(commandName, new UserDefinedCommand(myVariableList.size(), 0, myParser, myStorage, myVariableList, myCommandList));
            System.out.printf("SIZE: %d", myParser.getControlMap().size());
        }
        }

    @Override
    public double executeCode(){
        simplifyAndExecuteStructure();
        if (successful) return 1;
        return 0;
    }
}