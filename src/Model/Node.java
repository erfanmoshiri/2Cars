package Model;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Node {

    int hA;
    int hB;
    int huristic;
    int wallA;
    int wallB;
    Board board;
    Point wall1 = null;
    Point wall2 = null; // badan befahmim divar ezafe shode ya player jabeja shode


    public Node(Board board) {
        this.board = board;
    }

    public Node(int wallA, int wallB, Board board) {
        this.wallA = wallA;
        this.wallB = wallB;
        this.board = board;
    }

    void calculateHuristic() {
        this.huristic = this.hA - this.hB + this.wallA - this.wallB;
    }

}

class miniMAx {

    PathFinder pathFinder = new PathFinder();
    int rowNum[] = {-2, 0, 0, 2};
    int colNum[] = {0, -2, 2, 0};

    Node miniMaxAlphaBeta(Node node, int level, int depth, boolean isMaxTurn, int alpha, int beta) {

        if (level == depth) {
            return node;
        }

        if (isMaxTurn) {

            int best = -1 * Integer.MAX_VALUE;
            PriorityQueue<Node> pq = childGenerator(node, isMaxTurn, level, depth);


        } else {

        }


        return null;
    }

    PriorityQueue<Node> childGenerator(Node node, boolean isMaxTurn, int level, int depth) {

        PriorityQueue<Node> pq;
        Queue<Node> nodes;
        Node n, n1, n2;

        if (isMaxTurn) { // AI

            pq = new PriorityQueue<>(new MaxComperator());
            n = new Node(node.wallA, node.wallB, node.board);
            nodes = pathFinder.movePlayer(n, 'A');
            while (!nodes.isEmpty()) {
                n1 = nodes.poll();
                n1.hA = pathFinder.BFS(n1.board.posA, 16, n1.board.board, true).counter;
                n1.hB = pathFinder.BFS(n1.board.posB, 0, n1.board.board, false).counter;
                n1.calculateHuristic();
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

                    for (int j = 0; j < 17; j += 2) // vertical wall
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
            n = new Node(node.wallA, node.wallB, node.board);
            nodes = pathFinder.movePlayer(n, 'B');
            while (!nodes.isEmpty()) {
                n1 = nodes.poll();
                n1.hA = pathFinder.BFS(n1.board.posA, 16, n1.board.board, true).counter;
                n1.hB = pathFinder.BFS(n1.board.posB, 0, n1.board.board, false).counter;
                n1.calculateHuristic();
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

                    for (int j = 0; j < 17; j += 2) // vertical wall
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
        if (n1.huristic > n2.huristic)
            return 1;
        if (n1.huristic == n2.huristic)
            return 0;
        return -1;
    }
}

class MinComperator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.huristic > n2.huristic)
            return -1;
        if (n1.huristic == n2.huristic)
            return 0;
        return 1;

    }
}
