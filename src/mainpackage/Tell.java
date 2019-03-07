package mainpackage;


import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

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
        simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
            int end = findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine);
            System.out.printf("%d %d", myIndexOfFirstList, end);
            ArrayList<String> activeAnimals=new ArrayList<>();
            String animalID = "";
            for (int k = myIndexOfFirstList + 1; k < end; k++) {
                System.out.println("marker");
                animalID = mySimplifiableLine.get(k);
                activeAnimals.add(animalID);
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
