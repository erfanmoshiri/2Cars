package Model;

import java.util.LinkedList;

import static java.lang.StrictMath.abs;

public class Play {
    Board game = new Board();
    Board path = new Board();
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

    // defines random first player
    void rand() {
        if (Math.random() > 0.5)
            turnA = true;
        else
            turnB = true;
    }

    String puttingWall(int x1, int y1, int x2, int y2) {
        if ((turnA && wallA == 0) || (turnB && wallB == 0)) {
            System.out.println("player's walls are finished!");
            return "noWalls,only move";
        }
        int temp1 = (y1 + y2) / 2;
        int temp2 = (x1 + x2) / 2;
        if ((x1 % 2 == 1 && (game.board[x1 + 1][temp1] != 'W' || game.board[x1 - 1][temp1] != 'W')) || ((y1 % 2 == 1 && (game.board[temp2][y1 - 1] != 'W' || game.board[temp2][y1 + 1] != 'W')))) {
            if (game.board[x1][y1] == 0 && game.board[x2][y2] == 0) {
                if ((y1 == y2 && y1 % 2 == 1 && abs(x1 - x2) == 2) || (x1 == x2 && x1 % 2 == 1 && abs(y1 - y2) == 2)) {
                    path.putWall(x1, y1, x2, y2);
                    if (hasPath(path)) {
                        game.putWall(x1, y1, x2, y2);
                        if (turnA) {
                            wallA--;
                            System.out.println("number of wall A: " + wallA);
                        } else {
                            wallB--;
                            System.out.println("number of wall B: " + wallB);
                        }
                    } else {
                        System.out.println("player's path is closed!");
                        path.removeWall(x1, y1, x2, y2);
                        return "invalidWall";
                    }
                } else {
                    System.out.println("walls are not in a row/col!");
                    return "invalidWall";
                }
            } else {
                System.out.println("wall exists!");
                return "wallExists";
            }
        } else {
            System.out.println("cross walls!");
            return "invalidWall";
        }

        updateTurn();
        return "ok";
    }

    String move(int x, int y) {
        updateCurrent();

        if (turnA) {
            if (currY >= 2) {
                if (game.board[currX][currY - 1] != 'W') {
                    if (game.board[currX][currY - 2] != 'B') {
                        if ((currX == x) && (currY - 2 == y)) {
                            game.movePlayer('A', currX, currY - 2);
                            path.movePlayer('A', currX, currY - 2);
                            System.out.println("Moved");
                            return "ok";
                        }
                    } else {
                        if (currY >= 3 && game.board[currX][currY - 3] == 'W') {
                            if (currX >= 2 && game.board[currX - 1][currY - 2] != 'W') {
                                if (currX - 2 == x && currY - 2 == y) {
                                    game.movePlayer('A', currX - 2, currY - 2);
                                    path.movePlayer('A', currX - 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currX <= 15 && game.board[currX + 1][currY - 2] != 'W') {
                                if (currX + 2 == x && currY - 2 == y) {
                                    game.movePlayer('A', currX + 2, currY - 2);
                                    path.movePlayer('A', currX + 2, currY - 2);
                                    return "ok";
                                }
                            }
                        } else if (currY >= 4 && currY - 4 == y && currX == x) {
                            game.movePlayer('A', currX, currY - 4);
                            path.movePlayer('A', currX, currY - 4);
                            return "ok";
                        }
                    }
                }
            }
            if (currY <= 15) {
                if (game.board[currX][currY + 1] != 'W') {
                    if (game.board[currX][currY + 2] != 'B') {
                        if ((currX == x) && (currY + 2 == y)) {
                            game.movePlayer('A', currX, currY + 2);
                            path.movePlayer('A', currX, currY + 2);
                            return "ok";
                        }
                    } else {
                        if (currY <= 14 && game.board[currX][currY + 3] == 'W') {
                            if (currX >= 2 && game.board[currX - 1][currY + 2] != 'W') {
                                if (currX - 2 == x && currY + 2 == y) {
                                    game.movePlayer('A', currX - 2, currY + 2);
                                    path.movePlayer('A', currX - 2, currY + 2);
                                    return "ok";
                                }
                            }
                            if (currX <= 15 && game.board[currX + 1][currY + 2] != 'W') {
                                if (currX + 2 == x && currY + 2 == y) {
                                    game.movePlayer('A', currX + 2, currY + 2);
                                    path.movePlayer('A', currX + 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY <= 13 && currY + 4 == y && currX == x) {
                            game.movePlayer('A', currX, currY + 4);
                            path.movePlayer('A', currX, currY + 4);
                            return "ok";
                        }
                    }
                }
            }
            if (currX >= 2) {
                if (game.board[currX - 1][currY] != 'W') {
                    if (game.board[currX - 2][currY] != 'B') {
                        if ((currX - 2 == x) && (currY == y)) {
                            game.movePlayer('A', currX - 2, currY);
                            return "ok";
                        }
                    } else {
                        if (currX >= 3 && game.board[currX - 3][currY] == 'W') {
                            if (currY >= 2 && game.board[currX - 2][currY - 1] != 'W') {
                                if (currX - 2 == x && currY - 2 == y) {
                                    game.movePlayer('A', currX - 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currY <= 15 && game.board[currX - 2][currY + 1] != 'W') {
                                if (currX - 2 == x && currY + 2 == y) {
                                    game.movePlayer('A', currX - 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY >= 4 && currX - 4 == x && currY == y) {
                            game.movePlayer('A', currX - 4, currY);
                            return "ok";
                        }
                    }
                }
            }
            if (currX <= 15) {
                if (game.board[currX + 1][currY] != 'W') {
                    if (game.board[currX + 2][currY] != 'B') {
                        if ((currX + 2 == x) && (currY == y)) {
                            game.movePlayer('A', currX + 2, currY);
                            return "ok";
                        }
                    } else {
                        if (currX <= 14 && game.board[currX + 3][currY] == 'W') {
                            if (currY >= 2 && game.board[currX + 2][currY - 1] != 'W') {
                                if (currX + 2 == x && currY - 2 == y) {
                                    game.movePlayer('A', currX + 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currY <= 15 && game.board[currX + 2][currY + 1] != 'W') {
                                if (currX + 2 == x && currY + 2 == y) {
                                    game.movePlayer('A', currX + 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY <= 14 && currX + 4 == x && currY == y) {
                            game.movePlayer('A', currX + 4, currY);
                            return "ok";
                        }
                    }
                }
            }
            updateCurrent();
        } else {
            int currX = game.posB.x;
            int currY = game.posB.y;
            if (currY >= 2) {
                if (game.board[currX][currY - 1] != 'W') {
                    if (game.board[currX][currY - 2] != 'A') {
                        if ((currX == x) && (currY - 2 == y)) {
                            game.movePlayer('B', currX, currY - 2);
                            return "ok";
                        }
                    } else {
                        if (currY >= 3 && game.board[currX][currY - 3] == 'W') {
                            if (currX >= 2 && game.board[currX - 1][currY - 2] != 'W') {
                                if (currX - 2 == x && currY - 2 == y) {
                                    game.movePlayer('B', currX - 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currX <= 15 && game.board[currX + 1][currY - 2] != 'W') {
                                if (currX + 2 == x && currY - 2 == y) {
                                    game.movePlayer('B', currX + 2, currY - 2);
                                    return "ok";
                                }
                            }
                        } else if (currY >= 4 && currY - 4 == y && currX == x) {
                            game.movePlayer('B', currX, currY - 4);
                            return "ok";
                        }
                    }
                }
            }
            if (currY <= 15) {
                if (game.board[currX][currY + 1] != 'W') {
                    if (game.board[currX][currY + 2] != 'A') {
                        if ((currX == x) && (currY + 2 == y)) {
                            game.movePlayer('B', currX, currY + 2);
                            return "ok";
                        }
                    } else {
                        if (currY <= 14 && game.board[currX][currY + 3] == 'W') {
                            if (currX >= 2 && game.board[currX - 1][currY + 2] != 'W') {
                                if (currX - 2 == x && currY + 2 == y) {
                                    game.movePlayer('B', currX - 2, currY + 2);
                                    return "ok";
                                }
                            }
                            if (currX <= 15 && game.board[currX + 1][currY + 2] != 'W') {
                                if (currX + 2 == x && currY + 2 == y) {
                                    game.movePlayer('B', currX + 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY <= 13 && currY + 4 == y && currX == x) {
                            game.movePlayer('B', currX, currY + 4);
                            return "ok";
                        }
                    }
                }
            }
            if (currX >= 2) {
                if (game.board[currX - 1][currY] != 'W') {
                    if (game.board[currX - 2][currY] != 'A') {
                        if ((currX - 2 == x) && (currY == y)) {
                            game.movePlayer('B', currX - 2, currY);
                            return "ok";
                        }
                    } else {
                        if (currX >= 3 && game.board[currX - 3][currY] == 'W') {
                            if (currY >= 2 && game.board[currX - 2][currY - 1] != 'W') {
                                if (currX - 2 == x && currY - 2 == y) {
                                    game.movePlayer('B', currX - 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currY <= 15 && game.board[currX - 2][currY + 1] != 'W') {
                                if (currX - 2 == x && currY + 2 == y) {
                                    game.movePlayer('B', currX - 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY >= 4 && currX - 4 == x && currY == y) {
                            game.movePlayer('B', currX - 4, currY);
                            return "ok";
                        }
                    }
                }
            }
            if (currX <= 15) {
                if (game.board[currX + 1][currY] != 'W') {
                    if (game.board[currX + 2][currY] != 'A') {
                        if ((currX + 2 == x) && (currY == y)) {
                            game.movePlayer('B', currX + 2, currY);
                            return "ok";
                        }
                    } else {
                        if (currX <= 14 && game.board[currX + 3][currY] == 'W') {
                            if (currY >= 2 && game.board[currX + 2][currY - 1] != 'W') {
                                if (currX + 2 == x && currY - 2 == y) {
                                    game.movePlayer('B', currX + 2, currY - 2);
                                    return "ok";
                                }
                            }
                            if (currY <= 15 && game.board[currX + 2][currY + 1] != 'W') {
                                if (currX + 2 == x && currY + 2 == y) {
                                    game.movePlayer('B', currX + 2, currY + 2);
                                    return "ok";
                                }
                            }
                        } else if (currY <= 14 && currX + 4 == x && currY == y) {
                            game.movePlayer('B', currX + 4, currY);
                            return "ok";
                        }
                    }
                }
            }
            updateCurrent();
        }
        return "false";
    }

    boolean isValid(int row, int col) {
        return (row >= 0) && (row < 17) && (col >= 0) && (col < 17);
    }

    boolean
    BFS(char[][] board, Point src, Point dest) {
        if (board[dest.x][dest.y] == 'W')
            return false;

        boolean[][] visited = new boolean[17][17];

        visited[src.x][src.y] = true;

        LinkedList<Point> queue = new LinkedList<Point>();

        queue.push(src);

        int row, col;
        while (!queue.isEmpty()) {
            Point pt = queue.peek();
            if (pt.x == dest.x && pt.y == dest.y) // reach end line
                return true;

            queue.pop();

            for (int i = 0; i < 4; i++) {
                row = pt.x + rowNum[i];
                col = pt.y + colNum[i];
                if (isValid(row, col) && board[row][col] != 'W' && !visited[row][col]) {
                    visited[row][col] = true;
                    Point prvPoint = new Point(row, col);
                    queue.push(prvPoint);
                }
            }
        }
        return false;
    }

    boolean hasPath(Board path) {
        boolean pathA = false, pathB = false;
        Point pointA = new Point(path.posA.x, path.posA.y);
        Point pointB = new Point(path.posB.x, path.posB.y);

        for (int i = 0; i < 9; i++) { // open path for A
            Point dest = new Point(16, i * 2);
            if (BFS(path.board, pointA, dest))
                pathA = true;
        }

        for (int i = 0; i < 9; i++) { // open path for B
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