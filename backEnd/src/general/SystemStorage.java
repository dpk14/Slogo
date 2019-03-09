package general;

import operations.Command;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class SystemStorage {

    private Map<String, Animal> habitat;
    private Map<String, Double> userVariables;
    private Map<String, List<Command>> myCustomCommands;
    private List<Command> myCommandLog;
    private double height_of_screen;
    private double width_of_screen;
    private List<Entry<String, Animal>> myActiveAnimals;
    private DisplayModel myDisplayModel;

    public SystemStorage(ErrorMessage error){
        habitat = new HashMap<>();
        userVariables = new HashMap<>();
        myCustomCommands = new HashMap<>();
        myCommandLog = new ArrayList<>();
        myActiveAnimals = new ArrayList<>();
        myDisplayModel=new DisplayModel(error);
    }

    public void setScreenParameters(double height, double width){
        height_of_screen = height;
        width_of_screen = width;
    }

    public Map<String, Animal> getAnimals(){
        return habitat;
    }

    public void setActiveAnimals(List<String> activeList){
        myActiveAnimals.clear();
        for(String animalID: activeList){
            myActiveAnimals.add(new SimpleEntry(animalID, getAnimal(animalID)));
        }
    }

    public List<Entry<String, Animal>> getActiveAnimals(){
        return myActiveAnimals;
    }

    public Set<String> getAnimalNames(){
        HashSet<String> names = new HashSet<>();
        for (String name: habitat.keySet()){
            names.add(name);
        }
        return names;
    }

    public Map<String, Double> getVariableMap(){
        return userVariables;
    }

    public Set<String> getVariableNames(){
        HashSet<String> names = new HashSet<>();
        for (String name: userVariables.keySet()){
            names.add(name);
        }
        return names;
    }

    public Animal getAnimal(String animalID){
        if(!habitat.containsKey(animalID)){
            Animal temp = new Animal(animalID, height_of_screen, width_of_screen);
            habitat.put(animalID, temp);
        }
        return habitat.get(animalID);
    }

    public double getVariableValue (String variable){
        if (!userVariables.keySet().contains(variable)){
            setVariableValue(variable, 0);
        }
        return userVariables.get(variable);
        }

    public void setVariableValue(String variableName, double value){
        userVariables.putIfAbsent(variableName, value);
        if (userVariables.keySet().contains(variableName)){
            userVariables.put(variableName, value);
        }
    }

    public void addToHistory(Command command){
        myCommandLog.add(command);
    }

    public List<Command> getMyCommandLog() {
        return myCommandLog;
    }

    public DisplayModel getDisplay() {return myDisplayModel;}
}
