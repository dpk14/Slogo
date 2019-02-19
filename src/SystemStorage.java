import java.util.HashMap;
import java.util.Map;

public class SystemStorage {

    Map<String, Animal> habitat;
    public SystemStorage(){
        habitat = new HashMap<String, Animal>();
    }







    public void storeAnimal(String animalName, Animal object){
        habitat.put(animalName, object);
    }

    public Animal getAnimal(String animalName){
        return habitat.get(animalName);
    }

}
