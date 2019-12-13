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
        for (int i=0; i < this.boardSize * this.boardSize / 30; i++){
//            this.tiles[(int) (Math.random() * boardSize)][(int) (Math.random() * boardSize)].setBomb();
        }
    }
    void uncoverAdjacentTiles(int i, int j){

        if (i > 0 && this.tiles[i-1][j].count != null) {
            this.tiles[i-1][j].getChildren().remove(this.tiles[i-1][j].cover);
            this.tiles[i-1][j].getChildren().add(this.tiles[i-1][j].count);
            if (this.tiles[i-1][j].count.value == 0){
                uncoverAdjacentTiles(i-1, j);
            }
        }


        if (i > 0 && j > 0 && this.tiles[i-1][j-1].count != null) {
            if (this.tiles[i-1][j-1].count.value == 0){
                uncoverAdjacentTiles(i-1, j-1);
            }
            this.tiles[i-1][j-1].getChildren().remove(this.tiles[i-1][j-1].cover);
            this.tiles[i-1][j-1].getChildren().add(this.tiles[i-1][j-1].count);
            
        }


//        if (i > 0 && j < this.boardSize-1 && this.tiles[i-1][j-1].count != null) {
//            if (this.tiles[i-1][j+1].count.value == 0){
//                uncoverAdjacentTiles(i-1, j+1);
//            }
//            this.tiles[i-1][j-1].getChildren().remove(this.tiles[i-1][j+1].cover);
//            this.tiles[i-1][j-1].getChildren().add(this.tiles[i-1][j+1].count);
//        }













    }





}
