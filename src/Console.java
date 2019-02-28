import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Console {
    TextArea userInput;
    VBox consoleArea;
    Button implement;

    public Console() {
        consoleArea = new VBox();
        userInput = new TextArea();
        userInput.setWrapText(true);
        implement = new Button("Run");
        consoleArea.getChildren().addAll(userInput, implement);

    }

    public String getText(){
        return userInput.getText();
    }

    public Button getButton(){
        return implement;
    }


    public VBox getConsoleArea(){
        return consoleArea;
    }
}
