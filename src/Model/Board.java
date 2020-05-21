package Model;

class Position {

    int x, y;

    Position(int x, int y) {
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
                if (i % 2 == 0) {
                    // if (j % 2 == 1)
                    //board[i][j] = '|';
                    if (j % 2 == 0)
                        board[i][j] = '.';
                }
//                if (i % 2 == 1) {
//                    if (j % 2 == 0)
//                        board[i][j] = '_';
//                    else
//                        board[i][j] = '*';
//                }
            }
        }
        board[posA.x][posA.y] = 'A'; //start point of first player
        board[posB.x][posB.y] = 'B'; //start point of second player
        // other cells are free in the start
        // W char for the walls

//        board[0][5]='W';
//        board[2][5]='W';
//
//        board[10][1]='W';
//        board[10][3]='W';
    }

    void movePlayer(char player, int x, int y) { // move is valid , x,y are next state
        if (player == 'A') {
            board[posA.x][posA.y] = '.';
            posA.x = x;
            posA.y = y;
            board[posA.x][posA.y] = 'A';
        } else if (player == 'B') {
            board[posB.x][posB.y] = '.';
            posB.x = x;
            posB.y = y;
            board[posB.x][posB.y] = 'B';
        }
    }

    void putWall(int x1, int y1, int x2, int y2) //putting wall is valid
    {
        if (x1 == x2) {
            board[x1][y1] = '_';
            board[x2][y2] = '_';
        } else if (y1 == y2) {
            board[x1][y1] = '|';
            board[x2][y2] = '|';
        }
    }
}