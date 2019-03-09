package general;

import java.util.List;
import java.util.ResourceBundle;

public class DisplayModel {
    private String backgroundColor;
    private List<String> colorsList;
    private ErrorMessage errorMessage;

    public DisplayModel(ErrorMessage erorr){
        makeColorsList();
        setDefaultBackgroundColor();
        errorMessage = erorr;
    }

    private void makeColorsList(){
        var resource = ResourceBundle.getBundle("DefaultColors");
        for (String key: resource.keySet()){
            colorsList.add(resource.getString(key));
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
