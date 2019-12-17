import javafx.scene.layout.GridPane;

public class Board extends GridPane {
    int boardSize;
    Tile[][] tiles;

    Board(int size, int height){
        this.boardSize = size;
        this.tiles = new Tile[this.boardSize][this.boardSize];
        addTiles(height);
        setBombs();

        for (int i=0; i<this.tiles.length; i++){
            for (int j=0; j<this.tiles.length; j++){
                setCount(i,j, size);
            }
        }

    }
    private void addTiles(int height){
        for (int i=0; i<this.tiles.length; i++){
            for (int j=0; j<this.tiles[0].length; j++){
                // TODO fix spacing between tiles
                this.tiles[i][j] = new Tile( height / boardSize - 10, this, i, j);
                this.add(this.tiles[i][j], i, j);
            }
        }
    }

    void setCount(int i, int j, int size){
        if (this.tiles[i][j].bomb != null) return;
        int count = 0;

        if (i > 0 && this.tiles[i-1][j].bomb != null) count++;
        if (i > 0 && j > 0 && this.tiles[i-1][j-1].bomb != null) count++;
        if (i > 0 && j < size - 1 && this.tiles[i-1][j+1].bomb != null) count++;

        if (j > 0 && this.tiles[i][j-1].bomb != null) count++;
        if (j < size - 1 && this.tiles[i][j+1].bomb != null) count++;

        if (i < size - 1 && j > 0 && this.tiles[i+1][j-1].bomb != null) count++;
        if (i < size - 1 && this.tiles[i+1][j].bomb != null) count++;
        if (i < size - 1 && j < size - 1 && this.tiles[i+1][j+1].bomb != null) count++;

        this.tiles[i][j].setCount(count);
    }
    void setBombs(){
        for (int i=0; i < this.boardSize * this.boardSize / 10; i++){
            this.tiles[(int) (Math.random() * boardSize)][(int) (Math.random() * boardSize)].setBomb();
        }
    }
    void uncoverAdjacentTiles(int i, int j){
        uncoverRightTile  (i,j);
        uncoverLeftTile   (i,j);
        uncoverTopTile    (i,j);
        uncoverBottomTile (i,j);

    }
    void uncoverRightTile(int i, int j){
        if (i < this.tiles.length-1 && this.tiles[i+1][j].count != null) {
            this.tiles[i+1][j].uncover();
            if (this.tiles[i+1][j].count.value == 0){
                uncoverRightTile(i+1, j);
            }
        }
    }
    void uncoverLeftTile(int i, int j){
        if (i > 0 && this.tiles[i-1][j].count != null) {
            this.tiles[i-1][j].uncover();
            if (this.tiles[i-1][j].count.value == 0){
                uncoverLeftTile(i-1, j);
            }
        }
    }
    void uncoverTopTile(int i, int j){
        if (j > 0 && this.tiles[i][j-1].count != null) {
            this.tiles[i][j-1].uncover();
            if (this.tiles[i][j-1].count.value == 0){
                uncoverTopTile(i, j-1);
            }
        }
    }
    void uncoverBottomTile(int i, int j){
        if (j < this.tiles.length-1 && this.tiles[i][j+1].count != null) {
            this.tiles[i][j+1].uncover();
            if (this.tiles[i][j+1].count.value == 0){
                uncoverBottomTile(i, j+1);
            }
        }
    }





}
