package general;

import java.lang.Math;
import java.util.ArrayList;

public class Animal {

    private String animal_ID;
    private double[] direction_vector;
    private double current_angle;
    private boolean myPen;
    private boolean isVisible;
    private final int WIDTH_OF_TURTLE = 25;
    private final int HEIGHT_OF_TURTLE = 25;
    private final String DEFAULT_LINE_COLOR = "black";
    private final String DEFAULT_SHAPE = "frog.png";
    private final int DEFAULT_LINE_WIDTH = 2;
    private int myLineWidth;
    private double myX;
    private double myY;
    private double myNextX;
    private double myNextY;
    private boolean clearTrails;
    private String myShape;
    private String myLineColor;



    public Animal(String name, double height_of_screen, double width_of_screen){
        animal_ID = name;
        current_angle = 0;
        myShape=DEFAULT_SHAPE;
        myLineColor = DEFAULT_LINE_COLOR;
        myLineWidth = DEFAULT_LINE_WIDTH;
        double radian = Math.toRadians(current_angle);
        direction_vector = new double[2];
        direction_vector[0] = Math.sin(radian);
        direction_vector[1] = Math.cos(radian);
        isVisible = true;
        myX=width_of_screen/2 - WIDTH_OF_TURTLE;
        myY=height_of_screen/2 - HEIGHT_OF_TURTLE;
        myNextX=myX;
        myNextY=myY;
        clearTrails=false;
        myPen=false;
    }

    public void penUp(){
        myPen = false;
    }
    public void penDown(){
        myPen = true;
    }

    public void setPenColor(String color){
        myLineColor = color;
    }

    public void setPenSize(int pixels){
        myLineWidth = pixels;
    }

    public boolean getPenStatus(){
        return myPen;
    }

    public double getHeading(){
        return current_angle;
    }

    public String getColor(){
        return myLineColor;
    }

    public double[] getCoordinates(){
        double[] coords = new double[2];
        coords[0] = myX;
        coords[1] = myY;
        return coords;
    }

    public double setPosition(double x, double y){
        myNextX = x;
        myNextY = y;
        return Math.sqrt(Math.pow(myX-x,2) + Math.pow(myY-y, 2));
    }

    public boolean isVisible(){
        return isVisible;
    }

    public void setVisibility(Boolean visible){
        isVisible = visible;
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

        myNextX = myNextX + x_delta;
        myNextY = myNextY - y_delta;
    }

    public void adjustHeading(double angle){

        current_angle -= angle;

        double radian = Math.toRadians(current_angle);

        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

    }

    public void setHeading(double angle){
        current_angle = angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);
    }

    public void setImage(String fileName){
        myShape=fileName;
    }

    public String getAnimalID(){
        return animal_ID;
    }

    public boolean getVisibility() {
        return isVisible;
    }

    public double getCurrentAngle() {
        return current_angle;
    }

    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public double getNewX() {
        return myNextX;
    }

    public double getNewY() {
        return myNextY;
    }

    public double getLineWidth() {
        return myLineWidth;
    }

    public double getWidth() {
        return WIDTH_OF_TURTLE;
    }

    public double getHeight() {
        return HEIGHT_OF_TURTLE;
    }

    public void setX(double newX) {
        myX=newX;
    }

    public void setY(double newY) {
        myY=newY;
    }

    public boolean isPenDown() {
        return myPen;
    }

    public boolean clearTrail() {
        return clearTrails;
    }

    public void setClearTrail(boolean b) {
        clearTrails=b;
    }

    public String getShape() {
        return myShape;
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
