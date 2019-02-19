import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Math;

public class Animal {

    final private ImageView node;
    final private String animal_name;
    private double[] direction_vector;
    private double current_angle;

    public Animal(String name, int HEIGHT, int WIDTH){
        animal_name = name;

        current_angle = 90;
        double radian = Math.toRadians(current_angle);
        direction_vector = new double[2];
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node = new ImageView();
        node.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("turtle.png")));
        node.setX(WIDTH/2);
        node.setY(HEIGHT/2);
    }

    public void changePosition(int delta){
        double x_delta = delta * direction_vector[0];
        double y_delta = delta * direction_vector[1];

        double current_x = node.getX();
        double current_y = node.getY();

        node.setX(current_x + x_delta);
        node.setY(current_y + y_delta);
    }

    public void adjustHeading(int angle){
        current_angle += angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node.setRotate(node.getRotate() + current_angle);
    }

    public void setHeading(int angle){
        current_angle = angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node.setRotate(current_angle);
    }

    public void setImage(String fileName){
        node.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(fileName)));
        return;
    }

    public String getAnimalName(){
        return animal_name;
    }

    public ImageView getImageView(){
        return node;
    }
}
