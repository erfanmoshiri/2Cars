package Model;

class Position {

    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Board {

    char[][] board = new char[17][17];
    Position posA = new Position(0, 8);
    Position posB = new Position(16, 8);

    public Board() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    board[i][j] = '.';
                }
            }
        }
        board[posA.x][posA.y] = 'A'; //start point of first player
        board[posB.x][posB.y] = 'B'; //start point of second player
        // other cells are free in the start
    }

    public Board(char ban) {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    board[i][j] = '.';
                }
                if (i % 2 == 1 && j % 2 == 1) {
                    board[i][j] = ban;
                }
            }
        }
        board[posA.x][posA.y] = 'A'; //start point of first player
        board[posB.x][posB.y] = 'B'; //start point of second player
        // other cells are free in the start
    }


    void movePlayer(char player, int x, int y) { // move is valid , x,y are next state
        if (player == 'A') {
            board[posA.x][posA.y] = '.';
            board[x][y] = 'A';
            posA.setCoordinate(x, y);
        } else if (player == 'B') {
            board[posB.x][posB.y] = '.';
            board[x][y] = 'B';
            posB.setCoordinate(x, y);
        }
    }

    void putWall(int x1, int y1, int x2, int y2) //putting wall is valid
    {
        board[x1][y1] = 'W';
        board[x2][y2] = 'W';
    }

    void removeWall(int x1, int y1, int x2, int y2) {
        board[x1][y1] = 0;
        board[x2][y2] = 0;
    }
}