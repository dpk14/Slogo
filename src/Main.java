import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class  Main extends Application {


    final private int HEIGHT_OF_SCENE = 800;
    final private int WIDTH_OF_SCENE = 1000;

    final private int HEIGHT_OF_ANIMAL_SCREEN = 600;
    final private int WIDTH_OF_ANIMAL_SCREEN = 1000;

    private Console myConsole;
    public ProgramParser myParser;
    public SystemStorage mySystemStorage;

    public final String WHITESPACE = "\\s+";

    @Override
    public void start(Stage stage) throws Exception {
        myParser = new ProgramParser();
        myParser.addPatterns("regex/resources/languages/" + language);
        myParser.addPatterns("regex/resources/languages/Syntax");
        myParser.makeOperationsMap();
        myParser.makeControlMap();
        mySystemStorage = new SystemStorage();
        myConsole = new Console();
        Button myRun = myConsole.getButton();
        myRun.setOnAction(e -> sendText());
        Scene scene = createScene();
        stage.setScene(scene);
        stage.show();
    }

    private void sendText() {
        evaluateInput(myConsole.getText());
    }

    private Scene createScene() {
        var main_pane = new BorderPane();

        var animal_screen = new AnimalScreen(mySystemStorage, HEIGHT_OF_ANIMAL_SCREEN, WIDTH_OF_ANIMAL_SCREEN);
        var options = new ScreenOptions(animal_screen.getAnimalPane(), mySystemStorage);


        main_pane.setCenter(animal_screen.getAnimalPane());
        main_pane.setTop(options.getOptions());
        main_pane.setBottom(myConsole.getConsoleArea());

        var scene = new Scene(main_pane, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);

        return scene;
    }


    //gets the contents of the textbox as a string without newline characters
    private void evaluateInput(String inputString) {
        String[] splitByLine=inputString.split("\n");
        String newLineCharacterRemoved=String.join(" ", splitByLine);
        ArrayList<String> userInputList = new ArrayList<String>(Arrays.asList(newLineCharacterRemoved.split(" ")));
        int currentIndex = 0;
        while(currentIndex<userInputList.size()) {
            String nextEntry = userInputList.get(currentIndex);
            ControlStructure currentControlStructure = myParser.getControlStructure(nextEntry);
            currentControlStructure.initializeStructure(currentIndex, userInputList);
            currentControlStructure.executeCode();
            currentControlStructure.removeAndAdvance(currentIndex, userInputList);
            currentIndex++;
        }
        }

    public static void main (String[] args) {}
}
