package controlStructures;

import general.Animal;
import general.ProgramParser;
import general.SystemStorage;

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
        System.out.printf("active A size %d", activeAnimals.size());

        myIndexOfSecondList=end+1;

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
