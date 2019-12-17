import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    Rectangle background;
    Rectangle cover;
    Bomb bomb;
    Count count;
    Board board;
    int i;
    int j;
    public Tile(int height, Board board, int i, int j){
        this.i = i;
        this.j = j;
        this.board = board;
        this.background = new Rectangle(height, height);
        this.background.setFill(Color.WHITE);
        this.background.setStroke(Color.BLACK);
        this.cover = new Rectangle(height, height);
        this.cover.setFill(Color.GREEN);
        this.getChildren().addAll(background, cover);
        this.cover.setOpacity(.4);
        this.setOnMouseClicked(event -> {
            uncover();

        });

    }
    public void uncover(){
        this.getChildren().remove(this.cover);
        if (this.bomb != null){
            this.getChildren().add(this.bomb);}
        else if (!this.getChildren().contains(this.count)) {
            this.getChildren().add(this.count);
            if (this.count.value == 0){
                this.board.uncoverAdjacentTiles(this.i, this.j);}
        }
    }

    public void setBomb(){
        this.bomb = new Bomb((int) background.getHeight() / 4);
//        this.getChildren().add(this.bomb);
    }

    void setCount(int count){
        this.count = new Count(count);
        this.count.value = count;
//        this.getChildren().add(this.count);
    }

}
