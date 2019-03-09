package general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayModel {
    private String backgroundColor;
    private List<String> colorsList;
    private ErrorMessage errorMessage;
    private List<String> animalShapesList;

    public DisplayModel(ErrorMessage error){
        makeColorsList();
        makeAnimalShapesList();
        setDefaultBackgroundColor();
        errorMessage = error;
    }

    public List<String> getColorsList(){
        return Collections.unmodifiableList(colorsList);
    }

    public void setColorListItem(int index, String hex){
        colorsList.set(index, hex);
    }

    public List<String> getAnimalShapesList(){
        return animalShapesList;
    }

    private void makeColorsList(){
        colorsList = new ArrayList<>();
        var resource = ResourceBundle.getBundle("resources/default_visual_items/DefaultColors");
        for (String key: resource.keySet()){
            colorsList.add(resource.getString(key));
        }
    }

    private void makeAnimalShapesList(){
        animalShapesList = new ArrayList<>();
        var resource = ResourceBundle.getBundle("resources/default_visual_items/DefaultAnimalShapes");
        for (String key: resource.keySet()){
            animalShapesList.add(resource.getString(key));
        }
    }

    private void setDefaultBackgroundColor(){
        backgroundColor = colorsList.get(0);
    }

    public String getBackgroundColor(){
        return backgroundColor;
    }

    public void setBackgroundColor (int index) throws NullPointerException{
        if (index < colorsList.size()){
            backgroundColor = colorsList.get(index);
        }
        else {
            errorMessage.addError("IndexOutOfBounds");
        }
    }

    public String getColor(int index){
        return colorsList.get(index);
    }
}
