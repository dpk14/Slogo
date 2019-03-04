package mainpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class SystemStorage {

    private Map<String, Animal> habitat;
    private Map<String, Double> userVariables;
    private Map<String, List<Command>> myCustomCommands;
    private List<Command> myCommandLog;

    public SystemStorage(){
        habitat = new HashMap<>();
        userVariables = new HashMap<>();
        myCustomCommands = new HashMap<>();
        myCommandLog = new ArrayList<>();
    }

    public Map<String, Animal> getAnimals(){
        return habitat;
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


    public void storeAnimal (String animalName, Animal object){
        habitat.put(animalName, object);
    }

    public Animal getAnimal(String animalName){
        return habitat.get(animalName);
    }

    public double getVariableValue (String variable){
        if (!userVariables.keySet().contains(variable)){
            setVariableValue(variable, 0);
        }
        return userVariables.get(variable);
        }

    public List<Command> getCustomCommand(String commandName){
        return myCustomCommands.get(commandName);
    }

    public void setVariableValue(String variableName, double value){
        userVariables.putIfAbsent(variableName, value);
        if (userVariables.keySet().contains(variableName)){
            userVariables.put(variableName, value);
        }
    }

    public void setCustomCommand(String variableName, List<Command> commands){
        myCustomCommands.putIfAbsent(variableName, commands);
        if (myCustomCommands.keySet().contains(variableName)){
            myCustomCommands.put(variableName, commands);
        }
    }

    public void addToHistory(Command command){
        myCommandLog.add(command);
    }

    public List<Command> getMyCommandLog() {
        return myCommandLog;
    }
}
