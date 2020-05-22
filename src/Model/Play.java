package Model;

import java.util.Scanner;

public class Play {
    Scanner input = new Scanner(System.in);
    Board game = new Board();
    int x1, y1, x2, y2;
    int currX, currY;
    boolean turnA = false, turnB = false;
    char player, moveType, dir;
    int wallB = 10, wallA = 10;
    int xa,xb,ya,yb;

    void showState() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(game.board[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    void getMove() {
        // random first player
        if (Math.random() > 0.5)
            turnA = true;
        else
            turnB = true;

        if (turnA) {
            System.out.println("A turn.");
            currX = game.posA.x;
            currY = game.posA.y;
            player = 'A';
        } else {
            System.out.println("B turn.");
            currX = game.posB.x;
            currY = game.posB.y;
            player = 'B';
        }

        System.out.println("enter move type:");
        dir = input.next().charAt(0);
        if (dir == 'W') // put the wall
        {
            if ((turnA && wallA == 0) || (turnB && wallB == 0)) {
                System.out.println("Your walls are finished, only moving is valid!");
                repetitiveMove();
            } else
                putthingWall(player);
        } else if (dir == 'M') // move player
            repetitiveMove();

        turnA = !turnA;
        turnB = !turnB;
    }

    void putthingWall(char player) {
        System.out.println("enter coordinates of the start point:");
        x1 = input.nextInt();
        y1 = input.nextInt();

        System.out.println("enter coordinates of the start point:");
        x2 = input.nextInt();
        y2 = input.nextInt();

        if (!(x1 % 2 == 1 && y1 % 2 == 1) && !(x2 % 2 == 1 && y2 % 2 == 1)) {
            // inja bayad check konim mishe divar gozasht ya na
            // if ok ...
            if (game.board[x1][y1] == 0 && game.board[x2][y2] == 0) {
                if (x1 == x2 || y1 == y2) {
                    game.putWall(x1, y1, x2, y2);
                    if (player == 'A')
                        wallA--;
                    else if (player == 'B')
                        wallB--;
                } else {
                    System.out.println("invalid value! enter a new value");
                    putthingWall(player);
                }
            } else {
                System.out.println("invalid value! enter a new value");
                putthingWall(player);
            }
        } else {
            System.out.println("invalid value! enter a new value");
            putthingWall(player);
        }
    }

    void repetitiveMove() {
        System.out.println("what direction?");
        moveType = input.next().charAt(0);

        if (moveType == 'U') {
            if (currX - 2 < 0 || game.board[currX - 2][currY] != '.') { // dealing with the top line
                System.out.println("invalid move! enter a new move");
                repetitiveMove();
            } else  // cell is empty and no dealing
                game.movePlayer(player, currX - 2, currY);
        }

        if (moveType == 'D') {
            if (currX + 2 > 16 || game.board[currX + 2][currY] != '.') { // dealing with the bottom line
                System.out.println("invalid move! enter a new move");
                repetitiveMove();
            } else {
                game.movePlayer(player, currX + 2, currY);
            }
        }

        if (moveType == 'L') {
            if (currY - 2 < 0 || game.board[currX][currY - 2] != '.') { // dealing with the left line
                System.out.println("invalid move! enter a new move");
                repetitiveMove();
            } else
                game.movePlayer(player, currX, currY - 2);
        }

        if (moveType == 'R') {
            if (currY + 2 > 16 || game.board[currX][currY + 2] != '.') { // dealing with the right line
                System.out.println("invalid move! enter a new move");
                repetitiveMove();
            } else
                game.movePlayer(player, currX, currY + 2);
        }

        if (moveType == 'J') { // jump move

            // coordinate
            // check different moves
        }
    }

    void jumpMove(){
        if (turnA){
            xa = game.posA.x;
            ya = game.posA.y;
            xb= game.posB.x;
            yb=game.posB.y;
            if (ya==yb && xa+2==xb && game.board[xa+1][ya]!='_') // b below a , no wall between them
            {
                if (game.board[xb-1][yb]=='_') // wall below b
                {

                }
                else if (game.board[xb-1][yb]!='_') // nothing below b
                {
                    if (currX+4<17)
                        game.movePlayer(player, currX+4, currY);
                }
            }
        }
        else
        {

        }
    }


    boolean goalState() {
        if (turnA) {
            if (game.posA.y == 16) {
                System.out.println("player A is winner!");
                return true;
            }
        } else if (turnB) {
            if (game.posB.y == 0) {
                System.out.println("player B is winner!");
                return true;
            }
        }
        return false;
    }
}

class Test {
    public static void main(String[] args) {
        Play play = new Play();
        boolean stop = false;
        while (!stop) {
            play.showState();
            play.getMove();
            if (play.goalState())
                stop = true;
        }
    }
}