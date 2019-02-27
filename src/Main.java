import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main extends Application {

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
        Visualization myVisualization=new Visualization(mySystemStorage, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);
        Scene scene=myVisualization.initializeScene();
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        stage.setScene(scene);
        stage.show();
    }

    private void handleMouseInput(KeyCode code) {
        //if Run button click ed:
        evaluateInput(texBlock);

    }

    public void evaluateInput(ArrayList<String> textBlock) {
        ArrayList<String> currentLine;
        ArrayList<ArrayList<String>> textBlockNoSpaces=new ArrayList<ArrayList<String>>();
        for(int currentLineNumber=0; currentLineNumber<textBlock.size(); currentLineNumber++) {
            String[] currentLineWithoutSpaces = textBlock.get(currentLineNumber).split(WHITESPACE);
            ArrayList<String> currentLineWithoutSpaces_AsList = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));
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
