package Model;

public class Board {

    Cell[][] board;

    public Board(){
        board = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell(false, false, false);
            }
        }
    }

}
