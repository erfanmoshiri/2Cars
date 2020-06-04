package Model;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.StrictMath.abs;

public class PathFinder {

    boolean[][] visited;
    Queue<Pos> queue;
    boolean turnA;

    Pos BFS(Point src, int des, char[][] board, boolean turn) {

        turnA = turn;

        visited = new boolean[17][17];
        queue = new LinkedList<Pos>();

        visited[src.x][src.y] = true;

        Pos pos = new Pos(src, 0, null);

        queue.add(pos);

        while (!queue.isEmpty()) {
            Pos pt = queue.peek();
            if (pt.point.x == des) { // reach end line
                System.out.println("counter : " + pt.counter);
                return pt;
            }
            queue.poll();
            Object[] o = valids(board, pt, visited);
            queue = (Queue<Pos>) o[0];
            visited = (boolean[][]) o[1];
        }

        return null;
    }


    Object[] valids(char[][] board, Pos pos, boolean[][] visited) {

        int currX = pos.point.x;
        int currY = pos.point.y;
        int counter = pos.counter;

//        if (turnA) {

        if (currX <= 15) {
            if (board[currX + 1][currY] != 'W') {
                if (board[currX + 2][currY] != 'B' && board[currX + 2][currY] != 'A') {
                    if (!visited[currX + 2][currY]) {
                        queue.add(new Pos(new Point(currX + 2, currY), counter + 1, pos));
                        visited[currX + 2][currY] = true;
                    }
                } else {
                    if (currX <= 13 && board[currX + 3][currY] == 'W') {
                        if (currY >= 2 && board[currX + 2][currY - 1] != 'W') {
                            if (!visited[currX + 2][currY - 2]) {
                                queue.add(new Pos(new Point(currX + 2, currY - 2), counter + 1, pos));
                                visited[currX + 2][currY - 2] = true;
                            }
                        }
                        if (currY <= 14 && board[currX + 2][currY + 1] != 'W') {
                            if (!visited[currX + 2][currY + 2]) {
                                queue.add(new Pos(new Point(currX + 2, currY + 2), counter + 1, pos));
                                visited[currX + 2][currY + 2] = true;
                            }
                        }
                    } else if (currX <= 12) {
                        if (!visited[currX + 4][currY]) {
                            queue.add(new Pos(new Point(currX + 4, currY), counter + 1, pos));
                            visited[currX + 4][currY] = true;
                        }
                    }
                }
            }
        }
        if (currY >= 2) {
            if (board[currX][currY - 1] != 'W') {
                if (board[currX][currY - 2] != 'B' && board[currX][currY - 2] != 'A') {
                    if (!visited[currX][currY - 2]) {
                        queue.add(new Pos(new Point(currX, currY - 2), counter + 1, pos));
                        visited[currX][currY - 2] = true;
                    }
                } else {
                    if (currY >= 3 && board[currX][currY - 3] == 'W') {
                        if (currX >= 2 && board[currX - 1][currY - 2] != 'W') {
                            if (!visited[currX - 2][currY - 2]) {
                                queue.add(new Pos(new Point(currX - 2, currY - 2), counter + 1, pos));
                                visited[currX - 2][currY - 2] = true;
                            }
                        }
                        if (currX <= 15 && board[currX + 1][currY - 2] != 'W') {
                            if (!visited[currX + 2][currY - 2]) {
                                queue.add(new Pos(new Point(currX + 2, currY - 2), counter + 1, pos));
                                visited[currX + 2][currY - 2] = true;
                            }
                        }
                    } else if (currY >= 4) {
                        if (!visited[currX][currY - 4]) {
                            queue.add(new Pos(new Point(currX, currY - 4), counter + 1, pos));
                            visited[currX][currY - 4] = true;
                        }
                    }
                }
            }
        }

        if (currY <= 15) {
            if (board[currX][currY + 1] != 'W') {
                if (board[currX][currY + 2] != 'B' && board[currX][currY + 2] != 'A') {
                    if (!visited[currX][currY + 2]) {
                        queue.add(new Pos(new Point(currX, currY + 2), counter + 1, pos));
                        visited[currX][currY + 2] = true;
                    }
                } else {
                    if (currY <= 14 && board[currX][currY + 3] == 'W') {
                        if (currX >= 2 && board[currX - 1][currY + 2] != 'W') {
                            if (!visited[currX - 2][currY + 2]) {
                                queue.add(new Pos(new Point(currX - 2, currY + 2), counter + 1, pos));
                                visited[currX - 2][currY + 2] = true;
                            }
                        }
                        if (currX <= 15 && board[currX + 1][currY + 2] != 'W') {
                            if (!visited[currX + 2][currY + 2]) {
                                queue.add(new Pos(new Point(currX + 2, currY + 2), counter + 1, pos));
                                visited[currX + 2][currY + 2] = true;
                            }
                        }
                    } else if (currY <= 12) {
                        if (!visited[currX][currY + 4]) {
                            queue.add(new Pos(new Point(currX, currY + 4), counter + 1, pos));
                            visited[currX][currY + 4] = true;
                        }
                    }
                }
            }
        }

        if (currX >= 2) {
            if (board[currX - 1][currY] != 'W') {
                if (board[currX - 2][currY] != 'B' && board[currX - 2][currY] != 'A') {
                    if (!visited[currX - 2][currY]) {
                        queue.add(new Pos(new Point(currX - 2, currY), counter + 1, pos));
                        visited[currX - 2][currY] = true;
                    }
                } else {
                    if (currX >= 3 && board[currX - 3][currY] == 'W') {
                        if (currY >= 2 && board[currX - 2][currY - 1] != 'W') {
                            if (!visited[currX - 2][currY - 2]) {
                                queue.add(new Pos(new Point(currX - 2, currY - 2), counter + 1, pos));
                                visited[currX - 2][currY - 2] = true;
                            }
                        }
                        if (currY <= 15 && board[currX - 2][currY + 1] != 'W') {
                            if (!visited[currX - 2][currY + 2]) {
                                queue.add(new Pos(new Point(currX - 2, currY + 2), counter + 1, pos));
                                visited[currX - 2][currY + 2] = true;
                            }
                        }
                    } else if (currX >= 4) {
                        if (!visited[currX - 4][currY]) {
                            queue.add(new Pos(new Point(currX - 4, currY), counter + 1, pos));
                            visited[currX - 4][currY] = true;
                        }
                    }
                }
            }
        }

//        } else {
//
//            if (currY >= 2) {
//                if (board[currX][currY - 1] != 'W') {
//                    if (board[currX][currY - 2] != 'B' && board[currX][currY - 2] != 'A') {
//                        if (!visited[currX][currY - 2]) {
//                            queue.add(new Point(currX, currY - 2));
//                            visited[currX][currY - 2] = true;
//                        }
//                    } else {
//                        if (currY >= 3 && board[currX][currY - 3] == 'W') {
//                            if (currX >= 2 && board[currX - 1][currY - 2] != 'W') {
//                                if (!visited[currX - 2][currY - 2]) {
//                                    queue.add(new Point(currX - 2, currY - 2));
//                                    visited[currX - 2][currY - 2] = true;
//                                }
//                            }
//                            if (currX <= 15 && board[currX + 1][currY - 2] != 'W') {
//                                if (!visited[currX + 2][currY - 2]) {
//                                    queue.add(new Point(currX + 2, currY - 2));
//                                    visited[currX + 2][currY - 2] = true;
//                                }
//                            }
//                        } else if (currY >= 4 && board[currX][currY - 3] != 'W') {
//                            if (!visited[currX][currY - 4]) {
//                                queue.add(new Point(currX, currY - 4));
//                                visited[currX][currY - 4] = true;
//                            }
//                        }
//                    }
//                }
//            }
//            if (currX >= 2) {
//                if (board[currX - 1][currY] != 'W') {
//                    if (board[currX - 2][currY] != 'B' && board[currX - 2][currY] != 'A') {
//                        if (!visited[currX - 2][currY]) {
//                            queue.add(new Point(currX - 2, currY));
//                            visited[currX - 2][currY] = true;
//                        }
//                    } else {
//                        if (currX >= 3 && board[currX - 3][currY] == 'W') {
//                            if (currY >= 2 && board[currX - 2][currY - 1] != 'W') {
//                                if (!visited[currX - 2][currY - 2]) {
//                                    queue.add(new Point(currX - 2, currY - 2));
//                                    visited[currX - 2][currY - 2] = true;
//                                }
//                            }
//                            if (currY <= 15 && board[currX - 2][currY + 1] != 'W') {
//                                if (!visited[currX - 2][currY + 2]) {
//                                    queue.add(new Point(currX - 2, currY + 2));
//                                    visited[currX - 2][currY + 2] = true;
//                                }
//                            }
//                        } else if (currX >= 4 && board[currX - 3][currY] != 'W') {
//                            if (!visited[currX - 4][currY]) {
//                                queue.add(new Point(currX - 4, currY));
//                                visited[currX - 4][currY] = true;
//                            }
//                        }
//                    }
//                }
//            }
//            if (currY <= 15) {
//                if (board[currX][currY + 1] != 'W') {
//                    if (board[currX][currY + 2] != 'B' && board[currX][currY + 2] != 'A') {
//                        if (!visited[currX][currY + 2]) {
//                            queue.add(new Point(currX, currY + 2));
//                            visited[currX][currY + 2] = true;
//                        }
//                    } else {
//                        if (currY <= 14 && board[currX][currY + 3] == 'W') {
//                            if (currX >= 2 && board[currX - 1][currY + 2] != 'W') {
//                                if (!visited[currX - 2][currY + 2]) {
//                                    queue.add(new Point(currX - 2, currY + 2));
//                                    visited[currX - 2][currY + 2] = true;
//                                }
//                            }
//                            if (currX <= 15 && board[currX + 1][currY + 2] != 'W') {
//                                if (!visited[currX + 2][currY + 2]) {
//                                    queue.add(new Point(currX + 2, currY + 2));
//                                    visited[currX + 2][currY + 2] = true;
//                                }
//                            }
//                        } else if (currY <= 12 && board[currX][currY + 3] != 'W') {
//                            if (!visited[currX][currY + 4]) {
//                                queue.add(new Point(currX, currY + 4));
//                                visited[currX][currY + 4] = true;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (currX <= 15) {
//                if (board[currX + 1][currY] != 'W') {
//                    if (board[currX + 2][currY] != 'B' && board[currX + 2][currY] != 'A') {
//                        if (!visited[currX + 2][currY]) {
//                            queue.add(new Point(currX + 2, currY));
//                            visited[currX + 2][currY] = true;
//                        }
//                    } else {
//                        if (currX <= 13 && board[currX + 3][currY] == 'W') {
//                            if (currY >= 2 && board[currX + 2][currY - 1] != 'W') {
//                                if (!visited[currX + 2][currY - 2]) {
//                                    queue.add(new Point(currX + 2, currY - 2));
//                                    visited[currX + 2][currY - 2] = true;
//                                }
//                            }
//                            if (currY <= 14 && board[currX + 2][currY + 1] != 'W') {
//                                if (!visited[currX][currY - 2]) {
//                                    queue.add(new Point(currX + 2, currY + 2));
//                                    visited[currX + 2][currY + 2] = true;
//                                }
//                            }
//                        } else if (currX <= 12 && board[currX + 3][currY] != 'W') {
//                            if (!visited[currX + 4][currY]) {
//                                queue.add(new Point(currX + 4, currY));
//                                visited[currX + 4][currY] = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        Object[] o = new Object[2];
        o[0] = queue;
        o[1] = visited;

        return o;
    }

    Queue<Node> movePlayer(Node node, char c) {


        Queue<Node> nodes = new LinkedList<>();
        int currX, currY;
        if (c == 'A') {
            currX = node.board.posA.x;
            currY = node.board.posA.y;
        } else {
            currX = node.board.posB.x;
            currY = node.board.posB.y;
        }



        if (currX <= 15) {
            if (node.board.board[currX + 1][currY] != 'W') {
                if (node.board.board[currX + 2][currY] != 'B' && node.board.board[currX + 2][currY] != 'A') {
//                    if (!visited[currX + 2][currY]) {
//                        queue.add(new Pos(new Point(currX + 2, currY), counter + 1, point));
//                        visited[currX + 2][currY] = true;
//                    }
                    nodes.add(makeNode(node, currX + 2, currY, c));

                } else {
                    if (currX <= 13 && node.board.board[currX + 3][currY] == 'W') {
                        if (currY >= 2 && node.board.board[currX + 2][currY - 1] != 'W') {
                            nodes.add(makeNode(node, currX + 2, currY - 2, c));
                        }
                        if (currY <= 14 && node.board.board[currX + 2][currY + 1] != 'W') {
                            nodes.add(makeNode(node, currX + 2, currY + 2, c));
                        }
                    } else if (currX <= 12) {
                        nodes.add(makeNode(node, currX + 4, currY, c));
                    }
                }
            }
        }
        if (currY >= 2) {
            if (node.board.board[currX][currY - 1] != 'W') {
                if (node.board.board[currX][currY - 2] != 'B' && node.board.board[currX][currY - 2] != 'A') {
                    nodes.add(makeNode(node, currX, currY - 2, c));
                } else {
                    if (currY >= 3 && node.board.board[currX][currY - 3] == 'W') {
                        if (currX >= 2 && node.board.board[currX - 1][currY - 2] != 'W') {
                            nodes.add(makeNode(node, currX - 2, currY - 2, c));
                        }
                        if (currX <= 15 && node.board.board[currX + 1][currY - 2] != 'W') {
                            nodes.add(makeNode(node, currX + 2, currY - 2, c));
                        }
                    } else if (currY >= 4) {
                        nodes.add(makeNode(node, currX, currY - 4, c));
                    }
                }
            }
        }

        if (currY <= 15) {
            if (node.board.board[currX][currY + 1] != 'W') {
                if (node.board.board[currX][currY + 2] != 'B' && node.board.board[currX][currY + 2] != 'A') {
                    nodes.add(makeNode(node, currX, currY + 2, c));
                } else {
                    if (currY <= 14 && node.board.board[currX][currY + 3] == 'W') {
                        if (currX >= 2 && node.board.board[currX - 1][currY + 2] != 'W') {
                            nodes.add(makeNode(node, currX - 2, currY + 2, c));
                        }
                        if (currX <= 15 && node.board.board[currX + 1][currY + 2] != 'W') {
                            nodes.add(makeNode(node, currX + 2, currY + 2, c));
                        }
                    } else if (currY <= 12) {
                        nodes.add(makeNode(node, currX, currY + 4, c));
                    }
                }
            }
        }

        if (currX >= 2) {
            if (node.board.board[currX - 1][currY] != 'W') {
                if (node.board.board[currX - 2][currY] != 'B' && node.board.board[currX - 2][currY] != 'A') {
                    nodes.add(makeNode(node, currX - 2, currY, c));
                } else {
                    if (currX >= 3 && node.board.board[currX - 3][currY] == 'W') {
                        if (currY >= 2 && node.board.board[currX - 2][currY - 1] != 'W') {
                            nodes.add(makeNode(node, currX - 2, currY - 2, c));

                        }
                        if (currY <= 15 && node.board.board[currX - 2][currY + 1] != 'W') {
                            nodes.add(makeNode(node, currX - 2, currY + 2, c));
                        }
                    } else if (currX >= 4) {
                        nodes.add(makeNode(node, currX - 4, currY, c));
                    }
                }
            }
        }
        return nodes;
    }


    Node puttingWall(Node node, int x1, int y1, int x2, int y2, boolean turnA) {

        int temp1 = (y1 + y2) / 2;
        int temp2 = (x1 + x2) / 2;
        if ((x1 % 2 == 1 && (node.board.board[x1 + 1][temp1] != 'W' || node.board.board[x1 - 1][temp1] != 'W')) || ((y1 % 2 == 1 && (node.board.board[temp2][y1 - 1] != 'W' || node.board.board[temp2][y1 + 1] != 'W')))) {
            if (node.board.board[x1][y1] == 0 && node.board.board[x2][y2] == 0) {
                if ((y1 == y2 && y1 % 2 == 1 && abs(x1 - x2) == 2) || (x1 == x2 && x1 % 2 == 1 && abs(y1 - y2) == 2)) {
                    node.board.putWall(x1, y1, x2, y2);
                    Pos posA = BFS(node.board.posA, 16, node.board.board, true);
                    Pos posB = BFS(node.board.posB, 0, node.board.board, false);
                    if (posA != null && posB != null) {
                        node.hA = posA.counter;
                        node.hB = posB.counter;
                        node.wall1.setCoordinate(x1, y1);
                        node.wall2.setCoordinate(x2, y2);
                        if (turnA) {
                            node.wallA--;

                        } else {
                            node.wallB--;
                        }
                        node.calculateHuristic();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return node;
    }

//    char[][] copyBoard(char[][] arr) {
//        char[][] board = new char[17][17];
//        for (int i = 0; i < 17; i++) {
//            for (int j = 0; j < 17; j++) {
//                board[i][j] = arr[i][j];
//            }
//        }
//        return board;
//    }

    Node makeNode(Node node, int x, int y, char c) {
//        Board b = new Board();
//        b.board = copyBoard(node.board.board);
//        b.posA = node.board.posA;
//        b.posB = node.board.posB;
        node.board.movePlayer(c, x, y);
        return node;
    }
}
