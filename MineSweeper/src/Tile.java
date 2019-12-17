import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.naming.TimeLimitExceededException;

public class Tile extends StackPane {
    FadeTransition ft;
    Rectangle background;
    Rectangle cover;
    Bomb bomb;
    Count count;
    Board board;
    Rectangle highlight;
    Rectangle flag;
    Position position;
    int i;
    int j;
    public Tile(int height, Board board, int i, int j){
        this.position = new Position(i,j);
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
            System.out.println("this.getW = " + this.getWidth());
            if (this.board.onDeck != null) this.board.onDeck.stopFT();
            this.board.onDeck = this;
            this.highlight = new Rectangle(this.getWidth(), this.getHeight(), Color.YELLOW);
            this.ft = new FadeTransition();
            ft.setNode(highlight);
            ft.setFromValue(0.01);
            ft.setToValue(0.8);
            ft.setDuration(Duration.millis(1000));
            ft.setCycleCount(Animation.INDEFINITE);
            ft.setAutoReverse(true);
            this.getChildren().add(highlight);
            ft.play();
            });

//            uncover();
//        });


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

    void stopFT(){
        this.getChildren().remove(this.highlight);
        this.ft.stop();
    }
    void setFlag(){
        this.flag = new Rectangle(this.getWidth() / 2, this.getHeight() / 2);
        this.flag.setRotate(45);
        this.flag.setFill(Color.BLUE);
        this.getChildren().add(this.flag);
    }

}
