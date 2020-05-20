package Model;

public class Board {

    Cell[][] board;

    {
        board = new Cell[9][9];
    }

    public Board(Cell[][] board) {
        this.board = board;
    }
}
