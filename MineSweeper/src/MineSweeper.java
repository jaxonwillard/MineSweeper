import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MineSweeper extends Application {
    public void start(Stage stage){
        stage.setHeight(250);
        stage.setWidth(250);
        System.out.println("stage.getHeight() = " + stage.getHeight());
        Board board = new Board(10, (int) stage.getHeight());
        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();
        scene.setOnMouseClicked(event -> {
            System.out.println("stage.getHeight() = " + stage.getHeight());
        });
    }

    
}
