import javafx.scene.image.ImageView;
import java.lang.Math;

public class Animal {

    final private String FILE_NAME = "turtle.png";
    final private ImageView node;
    final private int animal_number;
    private double[] direction_vector;
    private double current_angle;

    public Animal(int num){
        animal_number = num;

        current_angle = 90;
        double radian = Math.toRadians(current_angle);
        direction_vector = new double[2];
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);

        node = new ImageView();
        //node.setImage(this.getClass().ClassLoader());
    }

    public void changePosition(int delta){
        int x_delta = (int) (delta * direction_vector[0]);
        int y_delta = (int) (delta * direction_vector[1]);
        node.setX(x_delta);
        node.setY(y_delta);
    }

    public void adjustHeading(int angle){
        current_angle += angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);
    }

    public void setHeading(int angle){
        current_angle = angle;
        double radian = Math.toRadians(current_angle);
        direction_vector[0] = Math.cos(radian);
        direction_vector[1] = Math.sin(radian);
    }

    public void setImage(String file){

        //node.setImage(file);
    }

    public int getAnimalNumber(){
        return animal_number;
    }

    public ImageView getImageView(){
        return node;
    }
}