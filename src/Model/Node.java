package Model;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Node {

    int hA;
    int hB;
    double heuristic;
    int wallA;
    int wallB;
    Board board;
    Point wall1 = null;
    Point wall2 = null; // badan befahmim divar ezafe shode ya player jabeja shode
    PriorityQueue<Node> childs = null;


    public Node(Board board) {
        this.board = board;
    }

    public Node(int wallA, int wallB, Board board) {
        this.wallA = wallA;
        this.wallB = wallB;
        this.board = board;
    }

    void calculateHeuristic() {
        this.heuristic = (this.hB - 2 * this.hA) + 2 * this.wallA - this.wallB;
    }

}

class MiniMAx {

    Node finalNode = null;

    PathFinder pathFinder = new PathFinder();

    double miniMaxAlphaBeta(Node node, int level, int depth, boolean isMaxTurn, double alpha, double beta) {

        if (node.wallB == 0) {
            if (node.wallA == 0) {
                Pos a = pathFinder.BFS(node.board.posA, 16, node.board.board, true);
                while (a.prevPos.prevPos != null) {
                    a = a.prevPos;
                }
                Board b = new Board(node.board);
                b.movePlayer('A', a.point.x, a.point.y);
                Node n = new Node(node.wallA, node.wallB, b);
                finalNode = n;
            } else {
                Pos a = pathFinder.BFS(node.board.posA, 16, node.board.board, true);
                Pos b = pathFinder.BFS(node.board.posB, 0, node.board.board, false);
                if (a.counter < b.counter) {
                    while (a.prevPos.prevPos != null) {
                        a = a.prevPos;
                    }
                    Board b1 = new Board(node.board);
                    b1.movePlayer('A', a.point.x, a.point.y);
                    Node n = new Node(node.wallA, node.wallB, b1);
                    finalNode = n;
                }
            }
            return 0;
        } else {
            if (level == depth) {
                node.calculateHeuristic();
                return node.heuristic;
            }
            Node n, n1 = null;

            if (isMaxTurn) {

                double best = -1 * Double.MAX_VALUE;
                double value;
                node.childs = childGenerator(node, true, level, depth);
                while (!node.childs.isEmpty()) {
                    n = node.childs.poll();
                    value = miniMaxAlphaBeta(n, level + 1, depth, false, alpha, beta);
                    if (value > best) {
                        best = value;
                        n1 = n;
                    }
                    if (best > alpha)
                        alpha = best;
                    if (beta < alpha)
                        break;
                }
                if (level == 0) {
                    System.out.println("node set!");
                    finalNode = n1;
                }
                return best;


            } else {

                double best = Double.MAX_VALUE;
                double value;
                node.childs = childGenerator(node, false, level, depth);
                while (!node.childs.isEmpty()) {
                    n = node.childs.poll();
                    value = miniMaxAlphaBeta(n, level + 1, depth, true, alpha, beta);
                    if (value < best)
                        best = value;
                    if (best < beta)
                        beta = best;
                    if (beta <= alpha)
                        break;
                }
                return best;

            }
        }
    }

    PriorityQueue<Node> childGenerator(Node node, boolean isMaxTurn, int level, int depth) {

        PriorityQueue<Node> pq;
        Queue<Node> nodes;
        Node n, n1, n2;

        if (isMaxTurn) { // AI

            pq = new PriorityQueue<>(new MaxComperator());
            Board b1 = new Board(node.board);
            n = new Node(node.wallA, node.wallB, b1);
            nodes = pathFinder.movePlayer(n, 'A');
            while (!nodes.isEmpty()) {
                n1 = nodes.poll();
//                n1.hA = pathFinder.BFS(n1.board.posA, 16, n1.board.board, true).counter;
//                n1.hB = pathFinder.BFS(n1.board.posB, 0, n1.board.board, false).counter;
                Pos a = pathFinder.BFS(n1.board.posA, 16, n1.board.board, true);
                Pos b = pathFinder.BFS(n1.board.posB, 0, n1.board.board, false);
                if (a != null) {
                    n1.hA = a.counter;
                } else
                    System.out.println(" a weird null path for A");
                if (b != null) {
                    n1.hB = b.counter;
                } else
                    System.out.println(" a weird null path for B");
                n1.calculateHeuristic();
                pq.add(n1);
            }

            if (node.wallA != 0) {


                if (level == 1) {

                    for (int j = 1; j < 16; j += 2) // horizontal wall
                    {
                        for (int k = 0; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j, k + 2, true);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }

                    for (int j = 0; j <= 14; j += 2) // vertical wall
                    {
                        for (int k = 1; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j + 2, k, true);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }
                } else {
                    // sade sazii
                    for (int j = 1; j < node.board.posB.x; j += 2) // horizontal wall
                    {
                        for (int k = 0; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j, k + 2, true);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }

                    for (int j = 0; j <= node.board.posB.x - 2; j += 2) // vertical wall
                    {
                        for (int k = 1; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j + 2, k, true);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }
                }
            }


        } else { // Human

            pq = new PriorityQueue<>(new MinComperator());
            Board b1 = new Board(node.board);
            n = new Node(node.wallA, node.wallB, b1);
            nodes = pathFinder.movePlayer(n, 'B');
            while (!nodes.isEmpty()) {
                n1 = nodes.poll();
                Pos a = pathFinder.BFS(n1.board.posA, 16, n1.board.board, true);
                Pos b = pathFinder.BFS(n1.board.posB, 0, n1.board.board, false);
                if (a != null) {
                    n1.hA = a.counter;
                } else
                    System.out.println(" a weird null path for A");
                if (b != null) {
                    n1.hB = b.counter;
                } else
                    System.out.println(" a weird null path for B");

                n1.calculateHeuristic();
                pq.add(n1);
            }

            if (node.wallB != 0) {


                if (level == 1) {

                    for (int j = 1; j < 16; j += 2) // horizontal wall
                    {
                        for (int k = 0; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j, k + 2, false);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }

                    for (int j = 0; j <= 14; j += 2) // vertical wall
                    {
                        for (int k = 1; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j + 2, k, false);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }
                } else {
                    // sade sazii
                    for (int j = node.board.posA.x + 1; j < 16; j += 2) // horizontal wall
                    {
                        for (int k = 0; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j, k + 2, false);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }

                    for (int j = node.board.posA.x; j <= 14; j += 2) // vertical wall
                    {
                        for (int k = 1; k < 15; k += 2) {
                            n2 = pathFinder.puttingWall(node, j, k, j + 2, k, false);
                            if (n2 != null) {
                                pq.add(n2);
                            }
                        }
                    }
                }
            }
        }

        return pq;
    }
}

class MaxComperator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        if (n1.heuristic > n2.heuristic)
            return 1;
        if (n1.heuristic == n2.heuristic)
            return 0;
        return -1;
    }
}

class MinComperator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.heuristic > n2.heuristic)
            return -1;
        if (n1.heuristic == n2.heuristic)
            return 0;
        return 1;

    }
}
