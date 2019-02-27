import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ScreenBuilder extends Application {
    final private int HEIGHT_OF_SCENE = 800;
    final private int WIDTH_OF_SCENE = 1000;

    final private int HEIGHT_OF_ANIMAL_SCREEN = 600;
    final private int WIDTH_OF_ANIMAL_SCREEN = 1000;

    private SystemStorage mySystemStorage;


    @Override
    public void start(Stage stage)  {
        mySystemStorage = new SystemStorage();
        var scene = createScene();
        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene() {
        var main_pane = new BorderPane();

        var animal_screen = new AnimalScreen(mySystemStorage, HEIGHT_OF_ANIMAL_SCREEN, WIDTH_OF_ANIMAL_SCREEN);
        var options = new ScreenOptions(animal_screen.getAnimalPane(), mySystemStorage);
        var console = new Console();

        main_pane.setCenter(animal_screen.getAnimalPane());
        main_pane.setTop(options.getOptions());
        main_pane.setBottom(console.getConsoleArea());

        var scene = new Scene(main_pane, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);

        return scene;
    }


    public static void main (String[] args) {
        launch(args);
    }

}
