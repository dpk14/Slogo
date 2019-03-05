package mainpackage;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import java.lang.reflect.Array;

public class  Main extends Application {


    final private int HEIGHT_OF_SCENE = 800;
    final private int WIDTH_OF_SCENE = 1000;

    final private int HEIGHT_OF_CONSOLE_AREA = 160 ;
    final private int HEIGHT_OF_OPTIONS = 20;
    final private int HEIGHT_OF_ANIMAL_SCREEN = 620;
    final private int WIDTH_OF_ANIMAL_SCREEN = 1000;

    private HashMap<String, Animal> myActiveTurtles=new HashMap<>();

    private Console myConsole;
    public ProgramParser myParser;
    public SystemStorage mySystemStorage;

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
        mySystemStorage = new SystemStorage();
        myParser = new ProgramParser(mySystemStorage);
        myParser.addPatterns("resources/languages/" + language);
        myParser.addPatterns("resources/languages/Syntax");
        myParser.makeOperationsMap();
        myParser.makeControlMap();
        myConsole = new Console(mySystemStorage, HEIGHT_OF_CONSOLE_AREA);
        Button myRun = myConsole.getButton();
        myRun.setOnAction(e->sendText());
        Scene scene = createScene();
        stage.setScene(scene);
        stage.show();
    }

    private void sendText() {
        evaluateInput(myConsole.getText());
        myConsole.updateVariables();
    }

    private Scene createScene() {
        var main_pane = new BorderPane();

        var animal_screen = new AnimalScreen(mySystemStorage, HEIGHT_OF_ANIMAL_SCREEN, WIDTH_OF_ANIMAL_SCREEN);
        var options = new ScreenOptions(animal_screen.getAnimalPane(), mySystemStorage, HEIGHT_OF_OPTIONS);

        main_pane.setCenter(animal_screen.getAnimalPane());
        main_pane.setTop(options.getOptions());
        main_pane.setBottom(myConsole.getConsoleArea());

        var scene = new Scene(main_pane, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);

        return scene;
    }


    //gets the contents of the textbox as a string without newline characters
    private void evaluateInput(String inputString) {
        if (inputString.length() == 0) return;
        String[] splitByLine = inputString.split("\n");
        for (int k = 0; k < splitByLine.length; k++) {
            if (splitByLine[k].contains("#")) splitByLine[k] = " ";
        }
        String newLineCharacterRemoved=String.join(" ", splitByLine);
        String[] splitString=newLineCharacterRemoved.split(" ");
        ArrayList<String> userInputList = new ArrayList<String>();
        for(String s: splitString) {
            if(s.length()!=0) userInputList.add(s);
        }

            int currentIndex = 0;
            ArrayList<String> simplifiableInput = new ArrayList<>(userInputList);
            while (currentIndex < simplifiableInput.size()) {
                List<Entry<String, Animal>> activeAnimals=mySystemStorage.getActveAnimals();
                for(Entry entry : activeAnimals) {
                    Animal currentAnimal = (Animal) entry.getValue();
                    String currentInput = simplifiableInput.get(currentIndex);
                    String currentInputSymbol = myParser.getSymbol(currentInput);
                    ControlStructure defaultStructure = myParser.getControlStructure(currentInputSymbol);
                    ControlStructure currentControlStructure = defaultStructure.copy();
                    currentControlStructure.initializeStructure(currentIndex, simplifiableInput, null, currentAnimal);
                    double returnValue = currentControlStructure.executeCode();

                    //simplifiableInput=currentControlStructure.getMySimplifiableLine();
                    System.out.println("\n");
                    for (String s : simplifiableInput) {
                        System.out.printf("%s ", s);
                    }
                    currentControlStructure.replaceCodeWithReturnValue(returnValue, simplifiableInput);
                    System.out.println("\n");
                    for (String s : simplifiableInput) {
                        System.out.printf("%s ", s);
                    }
                    if(!currentControlStructure.repeatable()) break;
                }
                currentIndex++;
            }
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}
