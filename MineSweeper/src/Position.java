public class Position {
    int i;
    int j;
    Position(int i, int j){
        this.i = i;
        this.j = j;
    }
    boolean equals(Position other){
        return this.i == other.i && this.j == other.j;
    }
}
