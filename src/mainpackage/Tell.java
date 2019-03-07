package mainpackage;


import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

public class Tell extends ControlStructure{
    double myReturnVal;

    public Tell(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new Tell(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    public void simplifyAndExecuteStructure(){
        myIndexOfFirstList=myStartingIndex+1;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("["));
        List<Entry<String, Animal>> activeAnimals=myStorage.getActiveAnimals();
        simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
            int end = findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine);
            System.out.printf("%d %d", myIndexOfFirstList, end);
            activeAnimals.clear();
            String animalID = "";
            for (int k = myIndexOfFirstList + 1; k < end; k++) {
                System.out.println("marker");
                animalID = mySimplifiableLine.get(k);
                Animal animal = myStorage.getAnimal(animalID);
                activeAnimals.add(new SimpleEntry<>(animalID, animal));
            }
            if (animalID.length() == 0) myReturnVal = 0;
            else myReturnVal = Double.parseDouble(animalID);
            declareUnrepeatable();
            System.out.printf("SIZE: %d", activeAnimals.size());
            myStorage.setActiveAnimals(activeAnimals);
        }

    @Override
    public double executeCode(){
    simplifyAndExecuteStructure();
    return myReturnVal;
    }
}
