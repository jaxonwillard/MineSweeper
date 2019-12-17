import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;


public class Board extends GridPane {
    int boardSize;
    Tile[][] tiles;
    Tile onDeck;
    ArrayList<Position> bombPositions;
    ArrayList<Position> flaggedPositions;

    Board(int size, int height) {
        this.flaggedPositions = new ArrayList<>();
        this.bombPositions = new ArrayList<>();
        this.boardSize = size;
        this.tiles = new Tile[this.boardSize][this.boardSize];
        addTiles(height);
        setBombs();
        System.out.println("this.bombPositions.size() = " + this.bombPositions.size());
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                setCount(i, j, size);
            }
        }

    }

    private void addTiles(int height) {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                // TODO fix spacing between tiles
                this.tiles[i][j] = new Tile(25, this, i, j);
                this.add(this.tiles[i][j], i, j);
            }
        }
    }

    void setCount(int i, int j, int size) {
        if (this.tiles[i][j].bomb != null) return;
        int count = 0;

        if (i > 0 && this.tiles[i - 1][j].bomb != null) count++;
        if (i > 0 && j > 0 && this.tiles[i - 1][j - 1].bomb != null) count++;
        if (i > 0 && j < size - 1 && this.tiles[i - 1][j + 1].bomb != null) count++;

        if (j > 0 && this.tiles[i][j - 1].bomb != null) count++;
        if (j < size - 1 && this.tiles[i][j + 1].bomb != null) count++;

        if (i < size - 1 && j > 0 && this.tiles[i + 1][j - 1].bomb != null) count++;
        if (i < size - 1 && this.tiles[i + 1][j].bomb != null) count++;
        if (i < size - 1 && j < size - 1 && this.tiles[i + 1][j + 1].bomb != null) count++;

        this.tiles[i][j].setCount(count);
    }

    void setBombs() {
        for (int x = 0; x < this.boardSize * this.boardSize / 6; x++) {
            int i = (int) (Math.random() * boardSize);
            int j = (int) (Math.random() * boardSize);
            if (this.tiles[i][j].bomb == null) {
                this.bombPositions.add(new Position(i, j));
                this.tiles[i][j].setBomb();
            } else {
                x--;
            }
        }
    }

    void uncoverAdjacentTiles(int i, int j) {
        uncoverRightTile(i, j);
        uncoverLeftTile(i, j);
        uncoverTopTile(i, j);
        uncoverBottomTile(i, j);

    }

    void uncoverRightTile(int i, int j) {
        if (i < this.tiles.length - 1 && this.tiles[i + 1][j].count != null) {
            this.tiles[i + 1][j].uncover();
            if (this.tiles[i + 1][j].count.value == 0) {
                uncoverRightTile(i + 1, j);
            }
        }
    }

    void uncoverLeftTile(int i, int j) {
        if (i > 0 && this.tiles[i - 1][j].count != null) {
            this.tiles[i - 1][j].uncover();
            if (this.tiles[i - 1][j].count.value == 0) {
                uncoverLeftTile(i - 1, j);
            }
        }
    }

    void uncoverTopTile(int i, int j) {
        if (j > 0 && this.tiles[i][j - 1].count != null) {
            this.tiles[i][j - 1].uncover();
            if (this.tiles[i][j - 1].count.value == 0) {
                uncoverTopTile(i, j - 1);
            }
        }
    }

    void uncoverBottomTile(int i, int j) {
        if (j < this.tiles.length - 1 && this.tiles[i][j + 1].count != null) {
            this.tiles[i][j + 1].uncover();
            if (this.tiles[i][j + 1].count.value == 0) {
                uncoverBottomTile(i, j + 1);
            }
        }
    }

    void processKey(String key) {
        this.onDeck.stopFT();
        if (key.equals("D")) {
            this.onDeck.uncover();
            if (this.onDeck.bomb != null) {
                endGame();
            }
        }
        if (key.equals("F")) {
            if (this.onDeck.flag == null) {
                this.onDeck.setFlag();
                this.flaggedPositions.add(this.onDeck.position);
            } else {
                this.onDeck.getChildren().remove(this.onDeck.flag);
                this.flaggedPositions.remove(this.onDeck.position);
            }
        }

    }

    void checkWinningBoard() {
        int count = 0;
        for (Position p : bombPositions) {
            for (Position f : flaggedPositions) {
                if (p.equals(f)) count++;
            }
        }
        if (count == bombPositions.size()) System.out.println("Winner");
    }

    void endGame() {
        SimpleIntegerProperty index = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(150), ae -> {
            Position bombPosition = this.bombPositions.get(index.get());
            if (!this.tiles[bombPosition.i][bombPosition.j].getChildren().contains(
                    this.tiles[bombPosition.i][bombPosition.j].bomb)) {

                this.tiles[bombPosition.i][bombPosition.j].getChildren().add(
                        this.tiles[bombPosition.i][bombPosition.j].bomb);
            }

            this.tiles[bombPosition.i][bombPosition.j].getChildren()
                    .remove(this.tiles[bombPosition.i][bombPosition.j].cover);
            index.set(index.getValue() + 1);
        }));

        timeline.setCycleCount(this.bombPositions.size());
        timeline.play();
    }


}
