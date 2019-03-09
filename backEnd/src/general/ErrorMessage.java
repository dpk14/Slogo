package general;

import java.util.*;

public class ErrorMessage {
    private ResourceBundle resource;
    private List<String> errors;

    public ErrorMessage(){
        resource = ResourceBundle.getBundle("ErrorMessages");
        errors = new ArrayList<>();
    }

    public void addError(String key){
        errors.add(key);
    }

    public List<String> getErrorsList(){
        return Collections.unmodifiableList(errors);
    }

    public void removeError(String error){
        errors.remove(error);
    }
}
