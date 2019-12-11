import javafx.scene.layout.GridPane;

public class Board extends GridPane {
    int size;
    Tile[][] tiles;

    public Board(int size, int height){
        this.size = size;
        this.tiles = new Tile[this.size][this.size];
        addTiles(height);
        for (int i=0; i<this.tiles.length; i++){
            for (int j=0; j<this.tiles.length; j++){
                setCount(i,j);
            }
        }

    }
    private void addTiles(int height){
        boolean addBomb = true;
        for (int i=0; i<this.tiles.length; i++){
            for (int j=0; j<this.tiles[0].length; j++){
                // TODO fix spacing between tiles
                this.tiles[i][j] = new Tile(addBomb, height / size - 10);
                this.add(this.tiles[i][j], i, j);
                addBomb = !addBomb;
            }
            addBomb = !addBomb;
        }
    }

    public void setCount(int i, int j){
        if (this.tiles[i][j].bomb != null) return;
        int count = 0;
        if (i > 0){
        if (this.tiles[i-1][j] != null && this.tiles[i-1][j].bomb != null) count++;
        if (this.tiles[i-1][j-1] != null && this.tiles[i-1][j-1].bomb != null) count++;
        if (this.tiles[i-1][j+1] != null && this.tiles[i-1][j+1].bomb != null) count++;}

        if (this.tiles[i][j-1] != null && this.tiles[i][j-1].bomb != null) count++;
        if (this.tiles[i][j+1] != null && this.tiles[i][j+1].bomb != null) count++;

        if (this.tiles[i+1][j-1] != null && this.tiles[i+1][j-1].bomb != null) count++;
        if (this.tiles[i+1][j] != null && this.tiles[i+1][j].bomb != null) count++;
        if (this.tiles[i+1][j+1] != null && this.tiles[i+1][j+1].bomb != null) count++;

        this.tiles[i][j].getChildren().add(new Count(count));
    }
}
