import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void start(Stage stage) {
        askForLanguages(stage);

    }

    private void askForLanguages(Stage stage){
        MenuBar languageBar = new MenuBar();
        Menu languages = new Menu("Choose Language");
        MenuItem english  = makeMenuItem("English", stage);
        MenuItem german = makeMenuItem("German", stage);
        MenuItem italian = makeMenuItem("Italian", stage);
        MenuItem russian = makeMenuItem("Russian", stage);
        MenuItem chinese = makeMenuItem("Chinese", stage);
        MenuItem french = makeMenuItem("French", stage);
        MenuItem portuguese = makeMenuItem("Portuguese", stage);
        MenuItem spanish = makeMenuItem("Spanish", stage);
        HBox root = new HBox();
        languages.getItems().addAll(english, german, italian, russian, chinese, french, portuguese, spanish);
        languageBar.getMenus().add(languages);
        root.getChildren().add(languageBar);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private MenuItem makeMenuItem(String language, Stage stage){
        MenuItem temp = new MenuItem(language);
        temp.setOnAction(e->createSLOGO(language, stage));
        return temp;
    }

    private void createSLOGO(String language, Stage stage){
        myParser = new ProgramParser();
        myParser.addPatterns("regex/resources/languages/" + language + ".properties");
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


    public void evaluateInput(ArrayList<String> textBlock) {
        ArrayList<String> currentLine;
        ArrayList<ArrayList<String>> textBlockNoSpaces=new ArrayList<ArrayList<String>>();
        for(int currentLineNumber=0; currentLineNumber<textBlock.size(); currentLineNumber++) {
            String[] currentLineWithoutSpaces = textBlock.get(currentLineNumber).split(WHITESPACE);
            ArrayList<String> currentLineWithoutSpaces_AsList = new ArrayList<>(Arrays.asList(currentLineWithoutSpaces));
            textBlockNoSpaces.add(currentLineWithoutSpaces_AsList); //control structures need access to textBlockNoSpaces
        }
            int currentLineNumber=0;
            int currentIndex=0;
            String firstEntry=textBlockNoSpaces.get(currentLineNumber).get(currentIndex);
            ControlStructure outerControlStructure=myParser.getControlStructure(firstEntry);
            Stack<ControlStructure> controlStructureStack=new Stack<ControlStructure>();
            controlStructureStack.push(outerControlStructure);
            while(controlStructureStack.size()!=0) {
                ControlStructure currentStructure= controlStructureStack.peek();
                currentStructure.executeCode(controlStructureStack, textBlockNoSpaces, currentLineNumber, currentIndex);
            }
            // else check for a control tag. Call Control.execute, and evaluateIndependentLine will be called accordingly
            //I'm thinking that if no control tag, create base control, which simply calls evaluateIndependentLine until textBlock runs out
            //if List, call parseOperationAndUpdate Index until close bracket is d etected
        }

    public static void main (String[] args) {}
}
