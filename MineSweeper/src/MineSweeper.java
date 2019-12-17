import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MineSweeper extends Application {
    public void start(Stage stage){
        Board board = new Board(16, (int) stage.getHeight());
        stage.setHeight(452);
        stage.setWidth(430.6666);
//        BorderPane menu = new BorderPane();
//        Rectangle background = new Rectangle(stage.getHeight(), stage.getWidth());
//        background.setFill(Color.GREEN);
//        Button start = new Button("START");
//        menu.getChildren().addAll(background);
//        menu.setCenter(start);
        Scene scene = new Scene(board);
        scene.setOnKeyPressed(event -> {
            board.processKey(event.getCode().toString());
        });
        stage.setScene(scene);
        stage.show();
//        start.setOnMouseClicked(event -> {
//            Scene sc = new Scene(board);
//            stage.setScene(sc);
//        });
    }
}
