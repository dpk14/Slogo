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
        mySystemStorage=new SystemStorage();
        myConsole = new Console();
        Button myRun = myConsole.getButton();
        myRun.setOnAction(e->sendText());
        Scene scene=createScene();
        stage.setScene(scene);
        stage.show();
    }

    private void sendText(){
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
    public void evaluateInput(String inputString) {
        ;
        ArrayList<ArrayLis<String>> textBlockNoSpaces=new ArrayList<ArrayList<String>>();
        for(int currentLineNumber=0; currentLineNumber<textBlock.size(); currentLineNumber++) {
            String[] currentLineWithoutSpaces = textBlock.get(currentLineNumber).split(WHITESPACE);
            ArrayList<String> currentLineWithoutSpaces_AsList = new ArrayList<>(Arrays.asList(currentLineWithoutSpaces));
            textBlockNoSpaces.add(currentLineWithoutSpaces_AsList); //control structures need access to textBlockNoSpaces
        }
            int currentLineNumber=0;
            int currentIndex=0;
            String firstEntry=textBlockNoSpaces.get(currentLineNumber).get(currentIndex);
            ControlStructure firstControlStructure=myParser.getControlStructure(firstEntry);
            firstControlStructure.initializeStructure(currentIndex, currentLineNumber, textBlockNoSpaces);
            Stack<ControlStructure> controlStructureStack=new Stack<ControlStructure>();
            controlStructureStack.push(firstControlStructure);
            while(controlStructureStack.size()!=0) {
                ControlStructure currentStructure= controlStructureStack.peek();
                currentStructure.executeCode(controlStructureStack, textBlockNoSpaces);
            }
            // else check for a control tag. Call Control.execute, and evaluateIndependentLine will be called accordingly
            //I'm thinking that if no control tag, create base control, which simply calls evaluateIndependentLine until textBlock runs out
            //if List, call parseOperationAndUpdate Index until close bracket is d etected
        }

    public static void main (String[] args) {}
}
