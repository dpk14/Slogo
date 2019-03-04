package mainpackage;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
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
        List<Entry<String, Animal>> currentAnimal=new ArrayList<>();
        for (String animalName: existingAnimals.keySet()){

            resetSimplification(mySavedLine);
            mySavedLine=new ArrayList<>(mySavedLine);

            Animal animal = existingAnimals.get(animalName);
            Entry turtleToBeChecked= new SimpleEntry<>(animalName, animal);
            currentAnimal.add(turtleToBeChecked);
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, currentAnimal);
            double condition=Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList+1));
            if (condition==1) activeAnimals.add(turtleToBeChecked);
        }
        myIndexOfSecondList=findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine)+1;
        simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList, activeAnimals);
    }
}
