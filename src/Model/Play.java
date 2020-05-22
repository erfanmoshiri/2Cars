package Model;

import java.util.LinkedList;

import static java.lang.StrictMath.abs;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Play {
    Board game = new Board('W');
    Board path = new Board('W');
    int currX, currY;
    boolean turnA = false, turnB = false;
    char player;
    int wallB = 10, wallA = 10;
    int rowNum[] = {-1, 0, 0, 1};
    int colNum[] = {0, -1, 1, 0};

    void showState() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(game.board[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("---------------------------------------------");
    }

    // random first player
    void rand() {
        if (Math.random() > 0.5)
            turnA = true;
        else
            turnB = true;
    }

    String puttingWall(boolean turnA1, int x1, int y1, int x2, int y2) {
        if ((turnA1 && wallA == 0) || (turnB && wallB == 0)) {
            System.out.println("Your walls are finished, only moving is valid!");
            return "noWalls";
        }
        int temp1 = (y1 + y2) / 2;
        int temp2 = (x1 + x2) / 2;
        if ((x1 % 2 == 1 && (game.board[x1 + 1][temp1] != 'W' || game.board[x1 - 1][temp1] != 'W')) || ((y1 % 2 == 1 && (game.board[temp2][y1 - 1] != 'W' || game.board[temp2][y1 + 1] != 'W')))) {
            if (game.board[x1][y1] == 0 && game.board[x2][y2] == 0) {
                if ((y1 == y2 && y1 % 2 == 1 && abs(x1 - x2) == 2) || (x1 == x2 && x1 % 2 == 1 && abs(y1 - y2) == 2)) {
                    path.putWall(x1, y1, x2, y2);
                    if (hasPath(path)) {
                        game.putWall(x1, y1, x2, y2);
                        if (turnA1) {
                            wallA--;
                            System.out.println("number of wall A: " + wallA);
                        } else {
                            wallB--;
                            System.out.println("number of wall B: " + wallB);
                        }
                    } else {
                        System.out.println("illegal wall!");
                        path.removeWall(x1, y1, x2, y2);
                        return "invalidWall";
                    }
                } else {
                    System.out.println("illegal wall!");
                    return "invalidWall";
                }
            } else {
                System.out.println("illegal wall!");
                return "wallExists";
            }
        } else {
            System.out.println("illegal wall!");
            return "invalidWall";
        }

        updateTurn();
        return "ok";
    }

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
                            path.movePlayer('A', currX, currY - 2);
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
                                    path.movePlayer('A', currX - 2, currY - 2);
                                    System.out.println("Yppp3");
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy - 2] != 'W') {
                                if (posx + 2 == x && posy - 2 == y) {
                                    game.movePlayer('A', currX + 2, currY - 2);
                                    path.movePlayer('A', currX + 2, currY - 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy >= 4 && posy - 4 == y && posx == x) {
                            game.movePlayer('A', currX, currY - 4);
                            path.movePlayer('A', currX, currY - 4);
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
                            path.movePlayer('A', currX, currY + 2);
                            updateCurrent();
                            return "ok";
                        }
                    } else {
                        if (posy <= 14 && game.board[posx][posy + 3] == 'W') {
                            if (posx >= 2 && game.board[posx - 1][posy + 2] != 'W') {
                                if (posx - 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX - 2, currY + 2);
                                    path.movePlayer('A', currX - 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                            if (posx <= 15 && game.board[posx + 1][posy + 2] != 'W') {
                                if (posx + 2 == x && posy + 2 == y) {
                                    game.movePlayer('A', currX + 2, currY + 2);
                                    path.movePlayer('A', currX + 2, currY + 2);
                                    updateCurrent();
                                    return "ok";
                                }
                            }
                        } else if (posy <= 13 && posy + 4 == y && posx == x) {
                            game.movePlayer('A', currX, currY + 4);
                            path.movePlayer('A', currX, currY + 4);
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
                                    game.movePlayer('B', currX + 2, currY + 2);
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

    boolean isValid(int row, int col) {
        return (row >= 0) && (row < 17) && (col >= 0) && (col < 17);
    }

    boolean BFS(char[][] board, Point src, Point dest) {
        if (board[dest.x][dest.y] == 'W')
            return false;

        boolean[][] visited = new boolean[17][17];

        visited[src.x][src.y] = true;

        LinkedList<Point> q = new LinkedList<Point>();

        q.push(src);

        int row, col;
        while (!q.isEmpty()) {
            Point pt = q.peek();
            if (pt.x == dest.x && pt.y == dest.y) // reach end
                return true;

            q.pop();

            for (int i = 0; i < 4; i++) {
                row = pt.x + rowNum[i];
                col = pt.y + colNum[i];

                if (isValid(row, col) && board[row][col] != 'W' && !visited[row][col]) { // 0 = WALL
                    visited[row][col] = true;
                    Point prvPoint = new Point(row, col);
                    q.push(prvPoint);
                }
            }
        }
        return false;
    }

    boolean hasPath(Board path) {
        boolean pathA = false, pathB = false;
        Point pointA = new Point(path.posA.x, path.posA.y);
        Point pointB = new Point(path.posB.x, path.posB.y);

        for (int i = 0; i < 9; i++) { // path for A
            Point dest = new Point(16, i * 2);
            if (BFS(path.board, pointA, dest))
                pathA = true;
        }

        for (int i = 0; i < 9; i++) { // path for B
            Point dest = new Point(0, i * 2);
            if (BFS(path.board, pointB, dest))
                pathB = true;
        }
        return pathA && pathB;
    }

    void updateCurrent() {
        if (turnA) {
            currX = game.posA.x;
            currY = game.posA.y;
            player = 'A';
        } else {
            currX = game.posB.x;
            currY = game.posB.y;
            player = 'B';
        }
    }

    void updateTurn() {
        turnA = !turnA;
        turnB = !turnB;
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

//class Test {
//    public static void main(String[] args) {
//
//        Play play = new Play();
//        boolean stop = false;
//        play.rand();
//        while (!stop) {
//            play.showState();
//            if (play.goalState())
//                stop = true;
//        }
//    }
//}