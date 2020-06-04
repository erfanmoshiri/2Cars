package Model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Node {

    int h;
    int wallA;
    int wallB;
    Pos posA;
    Pos posB;
    Board board;
    Pos wall1 = null;
    Pos wall2 = null;


    public Node(int wallA, int wallB, Pos posA, Pos posB, Board board) {
        this.wallA = wallA;
        this.wallB = wallB;
        this.posA = posA;
        this.posB = posB;
        this.board = board;
    }

//    @Override
//    public int compareTo(Object n) {
//        Node node = (Node) n;
//        if (this.h >= node.h)
//            return 1;
//        return -1;
//    }
}

class miniMAx {

    Node miniMaxAlphaBeta(Node node, int level, int depth, boolean isMaxTurn, int alpha, int beta) {

        if (level == depth) {
            return node;
        }

        if (isMaxTurn) {

            int best = -1 * Integer.MAX_VALUE;
            PriorityQueue<Node> pq = childGenerator(node, isMaxTurn, level);


        } else {

        }


        return null;
    }

    private PriorityQueue<Node> childGenerator(Node node, boolean isMaxTurn, int level) {

        PriorityQueue<Node> pq;

        if (isMaxTurn) {
            pq = new PriorityQueue<>(new MaxComperator());
        } else {
            pq = new PriorityQueue<>(new MinComperator());
        }


        return pq;
    }
}

class MaxComperator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        if (n1.h > n2.h)
            return 1;
        if(n1.h == n2.h)
            return 0;
        return -1;
    }
}

class MinComperator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.h > n2.h)
            return -1;
        if(n1.h == n2.h)
            return 0;
        return 1;
    }
}
