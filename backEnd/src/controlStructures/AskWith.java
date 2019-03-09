package controlStructures;

import general.Animal;
import general.ProgramParser;
import general.SystemStorage;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

public class AskWith extends ControlStructure{
    int myIndexOfSecondList;

    public AskWith(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new Ask(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    public void simplifyAndExecuteStructure(){
        myIndexOfFirstList=myStartingIndex+1;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("["));
        Map<String, Animal> existingAnimals=myStorage.getAnimals();
        List<Entry<String, Animal>> activeAnimals=new ArrayList<>();
        Animal animalToBeChecked;

        for (String animalName: existingAnimals.keySet()){
            resetSimplification(mySavedLine);
            mySavedLine=new ArrayList<>(mySavedLine);

            animalToBeChecked = existingAnimals.get(animalName);
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, animalToBeChecked);

            double condition=Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList+1));
            if (condition==1) activeAnimals.add(new SimpleEntry<>(animalName, animalToBeChecked));
        }

        myIndexOfSecondList=findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine)+1;

        int counter=0;
        for(Entry entry: activeAnimals) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList, (Animal) entry.getValue());
            if (counter!=activeAnimals.size()-1) {
                resetSimplification(mySavedLine);
                mySavedLine = new ArrayList<>(mySavedLine);
            }
            counter++;
        }
        declareUnrepeatable();
    }
}
