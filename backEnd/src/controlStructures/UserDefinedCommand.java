package controlStructures;

import general.ProgramParser;
import general.SystemStorage;

import java.util.ArrayList;

public class UserDefinedCommand extends ControlStructure {
    ArrayList<String> myVariables;
    ArrayList<String> myCommandList;
    ArrayList<String> myArguments;

    public UserDefinedCommand(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage, ArrayList<String> variables, ArrayList<String> commandList){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
        myVariables=variables;
        myCommandList=commandList;
        myArguments=new ArrayList<>();
    }

    @Override
    public ControlStructure copy() {
        return new UserDefinedCommand(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage, myVariables, myCommandList);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        for(int currentIndex=myStartingIndex+1; currentIndex<=myNumOfExpressionArguments+myStartingIndex+1; currentIndex++){
            String entry=mySimplifiableLine.get(currentIndex);
            String entrySymbol=myParser.getSymbol(entry);
            if(!(entrySymbol.equals("Variable") || entrySymbol.equals("Constant"))); //error, insufficient type of arguments
            simplifyAndEvaluate(mySimplifiableLine, currentIndex, myAnimal);
            myArguments.add(mySimplifiableLine.get(currentIndex));
        }

        if(myNumOfExpressionArguments!=myArguments.size()); //TODO: throw error

        for(int k = 0; k < myNumOfExpressionArguments; k++){
            String currentVariable=myVariables.get(k);
            myStorage.setVariableValue(currentVariable, Double.parseDouble(myArguments.get(k)));
        }

        simplifyAndEvaluate(myCommandList, 0, myAnimal);

    }
}