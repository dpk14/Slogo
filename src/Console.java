import javafx.scene.control.TextArea;

public class Console {
    TextArea userInput;
    public Console() {
        userInput = new TextArea();
    }


    public TextArea getTextArea(){
        return userInput;
    }
}
