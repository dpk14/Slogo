import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class Console {
    TextArea userInput;
    HBox consoleArea;
    Button implement;
    public Console() {
        consoleArea = new HBox();
        userInput = new TextArea();
        implement = new Button();


    }


    public TextArea getTextArea(){
        return userInput;
    }
}
