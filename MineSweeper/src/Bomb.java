import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Bomb extends Circle {
    public Bomb(int size){
        this.setRadius(size);
        this.setFill(Color.BLACK);
    }
}
