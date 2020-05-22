package Model;


import java.util.Scanner;

import static java.lang.StrictMath.abs;


public class Play {
    Scanner input = new Scanner(System.in);
    Board game = new Board();
    int x1, y1, x2, y2;
    int currX, currY;
    boolean turnA = false, turnB = false;
    char player, moveType, dir;
    int wallB = 10, wallA = 10;
    int xa, xb, ya, yb;

    void showState() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(game.board[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    // random first player
    void rand() {
        if (Math.random() > 0.5)
            turnA = true;
        else
            turnB = true;
    }

    void getMove() {


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

        System.out.println("enter move type (W or M):");

//        dir = input.next().charAt(0);

        if (dir == 'W') // put the wall
        {
            if ((turnA && wallA == 0) || (turnB && wallB == 0)) {
                System.out.println("Your walls are finished, only moving is valid!");
                //repetitiveMove();
            } else {
                //putthingWall(player);
            }

        } else if (dir == 'M') {
        } // move player
        //repetitiveMove();

    }

    String putthingWall(boolean turnA1, int x1, int y1, int x2, int y2) {

        if ((turnA1 && wallA == 0) || (turnB && wallB == 0)) {
            System.out.println("Your walls are finished, only moving is valid!");
            return "noWalls";
        }
        int temp1 = (y1 + y2) / 2;
        int temp2 = (x1 + x2) / 2;
        if ((x1 % 2 == 1 && (game.board[x1 + 1][temp1] != 'W' || game.board[x1 - 1][temp1] != 'W')) || ((y1 % 2 == 1 && (game.board[temp2][y1 - 1] != 'W' || game.board[temp2][y1 + 1] != 'W')))) {
            // inja bayad check konim mishe divar gozasht ya na
            // if ok ...
            if (game.board[x1][y1] == 0 && game.board[x2][y2] == 0) {
                if ((y1 == y2 && y1 % 2 == 1 && abs(x1 - x2) == 2) || (x1 == x2 && x1 % 2 == 1 && abs(y1 - y2) == 2)) {
                    game.putWall(x1, y1, x2, y2);
                    if (turnA1) {
                        wallA--;
                        System.out.println("number of wall A: " + wallA);
                    } else {
                        wallB--;
                        System.out.println("number of wall B: " + wallB);

                    }
                } else {
                    System.out.println("invalid value! enter a new value");
                    return "invalidWall";
                    //putthingWall(player);
                }
            } else {
                System.out.println("invalid value! enter a new value");
                return "wallExists";
                //putthingWall(player);
            }
        } else {
            System.out.println("invalid value! enter a new value");
            return "invalidWall";
            //putthingWall(player);
        }

        turnA = !turnA;
        turnB = !turnB;
        return "ok";
    }

//    void repetitiveMove() {
//        System.out.println("what direction?");
//        moveType = input.next().charAt(0);
//
//        if (moveType == 'U') {
//            if (currX - 2 < 0 || game.board[currX - 2][currY] != '.') { // dealing with the top line
//                System.out.println("invalid move! enter a new move");
//                repetitiveMove();
//            } else  // cell is empty and no dealing
//                game.movePlayer(player, currX - 2, currY);
//        }
//
//        if (moveType == 'D') {
//            if (currX + 2 > 16 || game.board[currX + 2][currY] != '.') { // dealing with the bottom line
//                System.out.println("invalid move! enter a new move");
//                repetitiveMove();
//            } else {
//                game.movePlayer(player, currX + 2, currY);
//            }
//        }
//
//        if (moveType == 'L') {
//            if (currY - 2 < 0 || game.board[currX][currY - 2] != '.') { // dealing with the left line
//                System.out.println("invalid move! enter a new move");
//                repetitiveMove();
//            } else
//                game.movePlayer(player, currX, currY - 2);
//        }
//
//        if (moveType == 'R') {
//            if (currY + 2 > 16 || game.board[currX][currY + 2] != '.') { // dealing with the right line
//                System.out.println("invalid move! enter a new move");
//                repetitiveMove();
//            } else
//                game.movePlayer(player, currX, currY + 2);
//        }
//
//        if (moveType == 'J') { // jump move
//
//            // coordinate
//            // check different moves
//        }
//    }

    String move(boolean turnA1, int x, int y) {

        updateCurrent();
        if (turnA1) {

            int posx = game.posA.x;
            int posy = game.posA.y;
            if (posy >= 2) {
                if (game.board[posx][posy - 1] != 'W') {
                    if (game.board[posx][posy - 2] != 'B') {
                        if ((posx == x) && (posy - 2 == y)) {
                            game.movePlayer('A', currX, currY - 2);
                            updateCurrent();
                            System.out.println("moved");
                            return "ok";
                        }
                    } else {
                        if (posy >= 3 && game.board[posx][posy - 3] == 'W') {
                            System.out.println("Yppp");
                            if (posx >= 2 && game.board[posx - 1][posy - 2] != 'W') {
                                System.out.println("Yppp1");
                                if (posx - 2 == x && posy - 2 == y) {
                                    System.out.println("Yppp2");
                                    game.movePlayer('A', currX - 2, currY - 2);
                                    System.out.println("Yppp3");
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy - 2] != 'W') {
                                if (posx + 2 == x && posy - 2 == y) {
                                    game.movePlayer('A', currX + 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy >= 4 && posy - 4 == y && posx == x) {
                            game.movePlayer('A', currX, currY - 4);
                            return "ok";
                        }


                    }
                }
            }
            if (posy <= 15) {
                if (game.board[posx][posy + 1] != 'W') {
                    if (game.board[posx][posy + 2] != 'B') {
                        if ((posx == x) && (posy + 2 == y)) {
                            game.movePlayer('A', currX, currY + 2);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posy <= 14 && game.board[posx][posy + 3] == 'W') {
                            if (posx >= 2 && game.board[posx - 1][posy + 2] != 'W') {
                                if (posx - 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX - 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy + 2] != 'W') {
                                if (posx + 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX + 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy <= 13 && posy + 4 == y && posx == x) {
                            game.movePlayer('A', currX, currY + 4);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }
            if (posx >= 2) {
                if (game.board[posx - 1][posy] != 'W') {
                    if (game.board[posx - 2][posy] != 'B') {
                        if ((posx - 2 == x) && (posy == y)) {
                            game.movePlayer('A', currX - 2, currY);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posx >= 3 && game.board[posx - 3][posy] == 'W') {
                            if (posy >= 2 && game.board[posx - 2][posy - 1] != 'W') {
                                if (posx - 2 == x && posy - 2 == y) {
                                    game.movePlayer('A', currX - 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posy <= 15 && game.board[posx - 2][posy + 1] != 'W') {
                                if (posx - 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX - 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy >= 4 && posx - 4 == x && posy == y) {
                            game.movePlayer('A', currX - 4, currY);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }
            if (posx <= 15) {
                if (game.board[posx + 1][posy] != 'W') {
                    if (game.board[posx + 2][posy] != 'B') {
                        if ((posx + 2 == x) && (posy == y)) {
                            game.movePlayer('A', currX + 2, currY);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posx <= 14 && game.board[posx + 3][posy] == 'W') {
                            if (posy >= 2 && game.board[posx + 2][posy - 1] != 'W') {
                                if (posx + 2 == x && posy - 2 == y) {
                                    game.movePlayer('A', currX + 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posy <= 15 && game.board[posx + 2][posy + 1] != 'W') {
                                if (posx + 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX + 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy <= 14 && posx + 4 == x && posy == y) {
                            game.movePlayer('A', currX + 4, currY);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }


        } else {

            int posx = game.posB.x;
            int posy = game.posB.y;
            if (posy >= 2) {
                if (game.board[posx][posy - 1] != 'W') {
                    if (game.board[posx][posy - 2] != 'A') {
                        if ((posx == x) && (posy - 2 == y)) {
                            game.movePlayer('B', currX, currY - 2);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posy >= 3 && game.board[posx][posy - 3] == 'W') {
                            if (posx >= 2 && game.board[posx - 1][posy - 2] != 'W') {
                                if (posx - 2 == x && posy - 2 == y) {
                                    game.movePlayer('B', currX - 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy - 2] != 'W') {
                                if (posx + 2 == x && posy - 2 == y) {
                                    game.movePlayer('B', currX + 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy >= 4 && posy - 4 == y && posx == x) {
                            game.movePlayer('B', currX, currY - 4);
                            return "ok";
                        }

                    }
                }
            }
            if (posy <= 15) {
                if (game.board[posx][posy + 1] != 'W') {
                    if (game.board[posx][posy + 2] != 'A') {
                        if ((posx == x) && (posy + 2 == y)) {
                            game.movePlayer('B', currX, currY + 2);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posy <= 14 && game.board[posx][posy + 3] == 'W') {
                            if (posx >= 2 && game.board[posx - 1][posy + 2] != 'W') {
                                if (posx - 2 == x && posy + 2 == y) {
                                    game.movePlayer('B', currX - 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy + 2] != 'W') {
                                if (posx + 2 == x && posy + 2 == y) {
                                    game.movePlayer('B', currX + 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy <= 13 && posy + 4 == y && posx == x) {
                            game.movePlayer('B', currX, currY + 4);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }
            if (posx >= 2) {
                if (game.board[posx - 1][posy] != 'W') {
                    if (game.board[posx - 2][posy] != 'A') {
                        if ((posx - 2 == x) && (posy == y)) {
                            game.movePlayer('B', currX - 2, currY);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posx >= 3 && game.board[posx - 3][posy] == 'W') {
                            if (posy >= 2 && game.board[posx - 2][posy - 1] != 'W') {
                                if (posx - 2 == x && posy - 2 == y) {
                                    game.movePlayer('B', currX - 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posy <= 15 && game.board[posx - 2][posy + 1] != 'W') {
                                if (posx - 2 == x && posy + 2 == y) {
                                    game.movePlayer('B', currX - 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy >= 4 && posx - 4 == x && posy == y) {
                            game.movePlayer('B', currX - 4, currY);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }
            if (posx <= 15) {
                if (game.board[posx + 1][posy] != 'W') {
                    if (game.board[posx + 2][posy] != 'A') {
                        if ((posx + 2 == x) && (posy == y)) {
                            game.movePlayer('B', currX + 2, currY);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posx <= 14 && game.board[posx + 3][posy] == 'W') {
                            if (posy >= 2 && game.board[posx + 2][posy - 1] != 'W') {
                                if (posx + 2 == x && posy - 2 == y) {
                                    game.movePlayer('B', currX + 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posy <= 15 && game.board[posx + 2][posy + 1] != 'W') {
                                if (posx + 2 == x && posy + 2 == y) {
                                    game.movePlayer('B', currX + 2, currY + 1);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy <= 14 && posx + 4 == x && posy == y) {
                            game.movePlayer('B', currX + 4, currY);
                            updateCurrent();
                            return "ok";
                        }

                    }
                }
            }

        }

        return "false";
    }

    void updateCurrent() {
        if (turnA) {
            //System.out.println("A turn.");

            currX = game.posA.x;
            currY = game.posA.y;
            player = 'A';
        } else {
//            System.out.println("B turn.");
            currX = game.posB.x;
            currY = game.posB.y;
            player = 'B';
        }
    }

    void updateTurn() {
        turnA = !turnA;
        turnB = !turnB;
    }

    void jumpMove() {
        if (turnA) {
            xa = game.posA.x;
            ya = game.posA.y;
            xb = game.posB.x;
            yb = game.posB.y;
            if (ya == yb && xa + 2 == xb && game.board[xa + 1][ya] != '_') // b below a , no wall between them
            {
                if (game.board[xb - 1][yb] == '_') // wall below b
                {

                } else if (game.board[xb - 1][yb] != '_') // nothing below b
                {
                    if (currX + 4 < 17)
                        game.movePlayer(player, currX + 4, currY);
                }
            }
        } else {

        }
    }

    boolean goalState() {
        if (turnA) {
            if (game.posA.x == 16) {
                System.out.println("player A is winner!");
                return true;
            }
        } else if (turnB) {
            if (game.posB.x == 0) {
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
        play.rand();
        while (!stop) {
            play.showState();
            play.getMove();
            if (play.goalState())
                stop = true;
        }
    }
}