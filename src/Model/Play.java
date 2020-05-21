package Model;

import java.util.Scanner;

public class Play {
    Scanner input = new Scanner(System.in);
    Board game = new Board();
    int x1, y1, x2, y2;
    int currX, currY;
    boolean turnA, turnB;
    char player, moveType;
//    int x = 1;

    void showState() {
//        System.out.println("1     2     3     4     5     6     7     8     9");
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(game.board[i][j] + "  ");
            }
//            if (i % 2 == 0) {
//                System.out.print(x);
//                x++;
//            }
            System.out.println("");
        }
    }

    void getMove(char move) {
        if (move == 'W') // put the wall
        {
            System.out.println("enter coordinates of the start point:");
            x1 = input.nextInt();
            y1 = input.nextInt();

            System.out.println("enter coordinates of the start point:");
            x2 = input.nextInt();
            y2 = input.nextInt();

            // inja bayad check konim mishe divar gozasht ya na
            // if ok ...
            game.putWall(x1, x2, y1, y2);
            // else print "invlid" -> while till valid move
        } else if (move == 'P') // move player
        {
            if (turnA) {
                currX = game.posA.x;
                currY = game.posA.y;
                player = 'A';
            } else if (turnB) {
                currX = game.posB.x;
                currY = game.posB.y;
                player = 'B';
            }
            System.out.println("what type of move");
            moveType = input.next().charAt(0);
            switch (moveType) {
                case 'U':

                    while (currY - 1 < 0 && moveType == 'U') { // dealing with the top line
                        System.out.println("invalid move! enter a new move");
                        moveType = input.next().charAt(0);
                    }
                    if (game.board[currX][currY - 1] == 0) // cell is empty
                        game.movePlayer(player, currX, currY - 1);

                    break;

                case 'D':

                    while (currY + 1 > 16 && moveType == 'D') { // dealing with the bottom line
                        System.out.println("invalid move! enter a new move");
                        moveType = input.next().charAt(0);
                    }
                    if (game.board[currX][currY + 1] == 0) // cell is empty
                        game.movePlayer(player, currX, currY + 1);

                    break;

                case 'L':

                    while (currX - 1 < 0 && moveType == 'L') { // dealing with the left line
                        System.out.println("invalid move! enter a new move");
                        moveType = input.next().charAt(0);
                    }
                    if (game.board[currX - 1][currY] == 0) // cell is empty
                        game.movePlayer(player, currX - 1, currY);

                    break;

                case 'R':

                    while (currX + 1 > 16 && moveType == 'R') { // dealing with the right line
                        System.out.println("invalid move! enter a new move");
                        moveType = input.next().charAt(0);
                    }
                    if (game.board[currX + 1][currY] == 0) // cell is empty
                        game.movePlayer(player, currX + 1, currY);

                    break;

                case 'S': // skew move
                    // coordinate
                    // check different moves
                    break;
            }
        }
//        if (goalState())
//        {
//            // break while
//            // game is over
//        }
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