import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SystemStorage {

    Map<String, Animal> habitat;

    public SystemStorage(){
        habitat = new HashMap<String, Animal>();
    }

    public void storeAnimal(String animalName, Animal animal){
        habitat.put(animalName, animal);
    }

    public Animal getAnimal(String animalName){
        return habitat.get(animalName);
    }

    public Set<String> getAnimalNames(){
        return habitat.keySet();
    }

}
