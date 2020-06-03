package Model;

import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {

    boolean[][] visited;
    Queue<Pos> queue;
    boolean turnA;

    Pos BFS(Point src, int des, char[][] board, boolean turn) {

        turnA = turn;

        visited = new boolean[17][17];
        queue = new LinkedList<Pos>();

        visited[src.x][src.y] = true;

        System.out.println("src : " + src.x + " , " + src.y);
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

}
