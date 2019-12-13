import javafx.scene.text.Text;

public class Count extends Text {
    int value;
    public Count(int count){
        this.value = count;
        this.setText(""+count);
    }
}
