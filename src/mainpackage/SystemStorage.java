package mainpackage;

import javafx.scene.layout.Pane;

import java.util.*;

public class SystemStorage {

    private Map<Integer, Animal> habitat;
    private Map<String, Double> userVariables;
    private Map<String, List<TurtleCommand>> myCustomCommands;
    private List<TurtleCommand> myCommandLog;
    private Pane animalCanvas;
    private double height_of_screen;
    private double width_of_screen;
    private List<Map.Entry<Integer, Animal>> myActiveAnimals;

    public SystemStorage(){
        habitat = new HashMap<>();
        userVariables = new HashMap<>();
        myCustomCommands = new HashMap<>();
        myCommandLog = new ArrayList<>();
        myActiveAnimals = new ArrayList<>();
    }


    public Map<String, Animal> getAnimals(){
        return habitat;
    }

    public void setActiveAnimals(ArrayList<Integer> activateAnimal){
        for(Integer animalID : activateAnimal){
            myActiveAnimals.add(new AbstractMap.SimpleEntry<>(animalID, getAnimal(animalID)));
        }
    }

    public List<Map.Entry<Integer, Animal>> getActiveAnimals(){
        return myActiveAnimals;
    }

    public double numTurtlesCreated(){
        return habitat.keySet().size();
    }

    public Set<Integer> getAnimalNames(){
        HashSet<Integer> names = new HashSet<>();
        for (Integer integer: habitat.keySet()){
            names.add(integer);
        }
        return names;
    }

    public void setScreenParameters(Pane canvas, double height, double width){
        animalCanvas = canvas;
        height_of_screen = height;
        width_of_screen = width;
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


    public Animal getAnimal(Integer animalID){
        if(!habitat.containsKey(animalID)){
            Animal temp = new Animal(animalID, height_of_screen, width_of_screen, animalCanvas);
            habitat.put(animalID, temp);
            animalCanvas.getChildren().add(temp.getImageView());
        }
        return habitat.get(animalID);
    }

    public double getVariableValue (String variable){
        if (!userVariables.keySet().contains(variable)){
            setVariableValue(variable, 0);
        }
        return userVariables.get(variable);
        }

    public List<TurtleCommand> getCustomCommand(String commandName){
        return myCustomCommands.get(commandName);
    }

    public void setVariableValue(String variableName, double value){
        userVariables.putIfAbsent(variableName, value);
        if (userVariables.keySet().contains(variableName)){
            userVariables.put(variableName, value);
        }
    }

    public void setCustomCommand(String variableName, List<TurtleCommand> commands){
        myCustomCommands.putIfAbsent(variableName, commands);
        if (myCustomCommands.keySet().contains(variableName)){
            myCustomCommands.put(variableName, commands);
        }
    }

    public double getActiveId(){
        int lastIndex = myCommandLog.size() - 1;
        double id = myCommandLog.get(lastIndex).getTurtle().getAnimalID();
        return id;
    }

    public void addToHistory(TurtleCommand command){
        myCommandLog.add(command);
    }

    public List<TurtleCommand> getMyCommandLog() {
        return myCommandLog;
    }
}
