package controlStructures;

import mainpackage.Animal;
import mainpackage.ProgramParser;
import mainpackage.SystemStorage;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

public class Ask extends ControlStructure{
    int myIndexOfSecondList;

    public Ask(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
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
        simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
        int end = findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine);

        List<Entry<String, Animal>> activeAnimals= new ArrayList<>();

        for(int k = myIndexOfFirstList+1; k<end; k++) {
            String animalID=mySimplifiableLine.get(k);
            Animal animal=myStorage.getAnimal(animalID);
            activeAnimals.add(new SimpleEntry<>(animalID, animal));
        }
        myIndexOfSecondList=end+1;

        for(Entry entry: activeAnimals) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList, (Animal) entry.getValue());
        }

        declareUnrepeatable();
        }
}
