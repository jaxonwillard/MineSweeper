import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    Rectangle background;
    Rectangle cover;
    Bomb bomb;

    public Tile(boolean addBomb, int height){
        this.background = new Rectangle(height, height);
        this.background.setFill(Color.WHITE);
        this.background.setStroke(Color.BLACK);
        this.getChildren().add(background);
        if (addBomb) {
            this.bomb = new Bomb((int) background.getHeight() / 4);
            this.getChildren().add(this.bomb); }




    }
}
