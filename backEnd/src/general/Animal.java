package general;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.lang.Math;
import java.util.ArrayList;

public class Animal {

    private ImageView node;
    private String animal_ID;
    private double[] direction_vector;
    private double current_angle;
    private boolean myPen;
    private Pane myPane;
    private ArrayList<Line> trail;
    private boolean isVisible;
    private int WIDTH_OF_TURTLE = 25;
    private int HEIGHT_OF_TURTLE = 25;

    public Animal(String name, double height_of_screen, double width_of_screen, Pane pane){
        animal_ID = name;
        myPane = pane;
        current_angle = 90;
        trail = new ArrayList<>();
        double radian = Math.toRadians(current_angle);
        direction_vector = new double[2];
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);
        isVisible = true;
        node = new ImageView();
        node.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("turtle.png")));
        node.setX(width_of_screen/2 - WIDTH_OF_TURTLE);
        node.setY(height_of_screen/2 - HEIGHT_OF_TURTLE);
    }


    public void penUp(){
        myPen = false;
    }
    public void penDown(){
        myPen = true;
    }

    public boolean getPenStatus(){
        return myPen;
    }

    public double getHeading(){
        return current_angle;
    }


    public double[] getCoordinates(){
        double[] coords = new double[2];
        coords[0] = node.getX();
        coords[1] = node.getY();
        return coords;
    }

    public double setPosition(double x, double y){
        double current_x = node.getX();
        double current_y = node.getY();
        node.setX(x); //TODO FIX in relation to (0,0)
        node.setY(y);
        return Math.sqrt(Math.pow(current_x-x,2) + Math.pow(current_y-y, 2));


    }

    public boolean isVisible(){
        return isVisible;
    }

    public void setVisibility(Boolean visible){
        isVisible = visible;
        node.setVisible(visible);
    }

    public double setToward(double x, double y){
        double radian = Math.tan(x/y);
        double degrees = Math.toDegrees(radian);
        setHeading(degrees);
        return degrees;
    }

    public void changePosition(double delta){

        double x_delta = delta * direction_vector[0];
        double y_delta = delta * direction_vector[1];


        double current_x = node.getX();
        double current_y = node.getY();

        double next_x = current_x + x_delta;
        double next_y = current_y - y_delta;


        node.setX(next_x);
        node.setY(next_y);

        if(myPen && delta != 0){
            Line path = new Line();
            path.setStartX(current_x + WIDTH_OF_TURTLE);
            path.setStartY(current_y + HEIGHT_OF_TURTLE);
            path.setEndX(next_x + WIDTH_OF_TURTLE);
            path.setEndY(next_y +  HEIGHT_OF_TURTLE);
            trail.add(path);
            myPane.getChildren().add(path);
        }
    }

    public void clearTrails(){
        for(Line path : trail){
            myPane.getChildren().remove(path);
        }
    }

    public void adjustHeading(double angle){

        current_angle -= angle;

        double radian = Math.toRadians(current_angle);

        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node.setRotate(node.getRotate() + angle);
    }

    public void setHeading(double angle){
        current_angle = angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node.setRotate(current_angle);
    }

    public void setImage(String fileName){

        node.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(fileName)));
    }

    public String getAnimalID(){
        return animal_ID;
    }

    public ImageView getImageView(){
        return node;
    }


    /*
    private Animation makeAnimation (Node agent) {
        // create something to follow
        var path = new Path();
        path.getElements().addAll(new MoveTo(50, 50), new HLineTo(350));
        // create an animation where the shape follows a path
        var pt = new PathTransition(Duration.millis(4000), path, agent);
        // create an animation that rotates the shape
        var rt = new RotateTransition(Duration.seconds(3));
        rt.setByAngle(90);
        // put them together in order
        return new SequentialTransition(agent, pt, rt);
    }

     */
}
