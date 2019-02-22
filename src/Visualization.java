public class Visualization {
    final private int HEIGHT_OF_SCENE = 800;
    final private int WIDTH_OF_SCENE = 1000;
    final private int HEIGHT_OF_TURTLE_SCREEN = 600;
    final private int WIDTH_OF_TURTLE_SCREEN = 1000;
    private SystemStorage mySystemStorage;
    private BorderPane myMainPane;
    private Console myConsole;
    private Group myRoot;

    public Visualization(SystemStorage storage) {
        mySystemStorage=storage;
        var turtle_one = new Animal("0", HEIGHT_OF_TURTLE_SCREEN, WIDTH_OF_TURTLE_SCREEN);
        mySystemStorage.storeAnimal("0", turtle_one);
        myMainPane = new BorderPane();
        myConsole = new Console();
        myMainPane.setBottom(console.getTextArea());
    }

    public Scene initializeScene(){
        Scene scene = new Scene(myRoot, WIDTH_OF_SCENE, HEIGHT_OF_SCENE);
        var center_screen = new Group();
        center_screen.prefHeight(HEIGHT_OF_TURTLE_SCREEN);
        center_screen.prefWidth(WIDTH_OF_TURTLE_SCREEN);
        var turtle_to_add = storage.getAnimal("0");
        center_screen.getChildren().add(turtle_to_add.getImageView());
        main_pane.setCenter(center_screen);
        return scene;
    }

}
