import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Console {
    TextArea userInput;
    VBox consoleArea;
    Button implement;

    public Console() {
        consoleArea = new VBox();
        userInput = new TextArea();
        userInput.setWrapText(true);
        implement = new Button("Run");
        implement.setOnAction(e->sendText());
        consoleArea.getChildren().addAll(userInput, implement);

    }

    private void sendText(){
        String input = userInput.getText();

    }


    public VBox getConsoleArea(){
        return consoleArea;
    }
}
