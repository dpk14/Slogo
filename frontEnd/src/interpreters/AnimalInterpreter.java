package interpreters;

import general.Animal;
import general.SystemStorage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimalInterpreter {
    private Pane myCanvas;
    private Map<String, ArrayList<Line>> myTrails;
    private Map<String, ImageView> myTurtles;
    private SystemStorage myStorage;

    public AnimalInterpreter(SystemStorage storage){
        myStorage=storage;
        myTurtles=new HashMap<>();
        myTrails=new HashMap<>();
    }

    public void setCanvas(Pane canvas){
        myCanvas=canvas;
    }

    public void updateAnimals(){
        HashMap<String, Animal> animals= (HashMap) myStorage.getAnimals();
        for(String animalID: animals.keySet()){
            ImageView turtle=interpret(animalID, animals);
            myTurtles.put(animalID, turtle);
        }
    }

    public ImageView interpret(String animalID, Map<String, Animal> animals){
        ImageView turtle;
        Animal animal=animals.get(animalID);
        if(!myTurtles.containsKey(animalID)) {
            turtle = new ImageView();
            myCanvas.getChildren().add(turtle);
        }
        else turtle=myTurtles.get(animalID);

        setImage(animal, turtle);
        setHeading(animal, turtle);
        move(animalID, animal, turtle);
        clearTrails(animal, animalID);
        setVisibility(animal, turtle);

        return turtle;
    }

    private void setHeading(Animal animal, ImageView turtle){
        turtle.setRotate(animal.getCurrentAngle());
    }

    private void move(String animalID, Animal animal, ImageView turtle){
        double x=animal.getX();
        double y=animal.getY();
        double newX=animal.getNewX();
        double newY=animal.getNewY();

        if(animal.isPenDown() && (x!=newX || y!=newY)){
            Line path = new Line();
            path.setStroke(Paint.valueOf(animal.getColor()));               //(String.format("-fx-stroke: %s", animal.getColor()));
            path.setStrokeWidth(animal.getLineWidth());
            path.setStartX(x + animal.getWidth());
            path.setStartY(y + animal.getHeight());
            path.setEndX(newX + animal.getWidth());
            path.setEndY(newY + animal.getHeight());
            if(!myTrails.containsKey(animalID)){
                myTrails.put(animalID, new ArrayList<>());
            }
            myTrails.get(animalID).add(path);
            myCanvas.getChildren().add(path);
        }
        animal.setX(newX);
        animal.setY(newY);
        turtle.setX(newX);
        turtle.setY(newY);
    }

    private void clearTrails(Animal animal, String animalID){
        if (animal.clearTrail()==true) {
            ArrayList<Line> trail=myTrails.get(animalID);
            for (Line path : trail) {
                myCanvas.getChildren().remove(path);
            }
            trail.clear();
        }
        animal.setClearTrail(false);
    }

    public void setVisibility(Animal animal, ImageView turtle){
        turtle.setVisible(animal.getVisibility());
    }

    private void setImage(Animal animal, ImageView turtle){
        turtle.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(animal.getShape())));
    }
}
