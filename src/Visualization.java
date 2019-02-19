import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Visualization extends Application {

    final private int HEIGHT_OF_SCENE = 800;
    final private int WIDTH_OF_SCENE = 1000;
    final private int HEIGHT_OF_TURTLE_SCREEN = 600;
    final private int WIDTH_OF_TURTLE_SCREEN = 1000;


    private SystemStorage storage;

    @Override
    public void start(Stage stage) throws Exception {
        var turtle_one = new Animal("0", HEIGHT_OF_TURTLE_SCREEN, WIDTH_OF_TURTLE_SCREEN);
        storage = new SystemStorage();
        storage.storeAnimal("0", turtle_one);
        createScene(stage);
    }

    private void createScene(Stage stage) {

        var main_pane = new BorderPane();

        var center_screen = new Group();
        center_screen.prefHeight(HEIGHT_OF_TURTLE_SCREEN);
        center_screen.prefWidth(WIDTH_OF_TURTLE_SCREEN);
        var turtle_to_add = storage.getAnimal("0");
        center_screen.getChildren().add(turtle_to_add.getImageView());

        main_pane.setCenter(center_screen);

        var scene = new Scene(main_pane, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);

        stage.setScene(scene);
        stage.show();
    }


}
