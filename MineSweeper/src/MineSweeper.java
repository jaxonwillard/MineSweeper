import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MineSweeper extends Application {
    public void start(Stage stage){
        stage.setHeight(500);
        stage.setWidth(500);
        System.out.println("stage.getHeight() = " + stage.getHeight());
        Board board = new Board(20, (int) stage.getHeight());
        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();

    }

    
}
