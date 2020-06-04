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


    public Node(Board board) {
//        this.wallA = wallA;
//        this.wallB = wallB;
//        this.posA = posA;
//        this.posB = posB;
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

    int rowNum[] = {-2, 0, 0, 2};
    int colNum[] = {0, -2, 2, 0};

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

        if (isMaxTurn) { // AI
            pq = new PriorityQueue<>(new MaxComperator());
            for (int i = 0; i < 4; i++) {
                Node n1 = new Node(node.board);
                n1.board.movePlayer('A', n1.board.posA.x + rowNum[i], n1.board.posA.y + colNum[i]);
                pq.add(n1);
            }
            // agar harekate movarab dasht

            for (int j=1 ; j<16 ; j+=2) // horizontal wall
            {
                for (int k=0 ; k<15 ; k+=2)
                {
                    Node n1 = new Node(node.board);
                    n1.board.putWall(j,k,j,k+2);
                    pq.add(n1);
                }
            }

            for (int j=0 ; j<17 ; j+=2) // vertical wall
            {
                for (int k=1 ; k<15 ; k+=2)
                {
                    Node n1 = new Node(node.board);
                    n1.board.putWall(j,k,j+2,k);
                    pq.add(n1);
                }
            }


        } else { // Human
            pq = new PriorityQueue<>(new MinComperator());
            for (int i = 0; i < 4; i++) {
                node.board.movePlayer('B', node.board.posA.x + rowNum[i], node.board.posA.y + colNum[i]);
                pq.add(node);
            }

            // agar harekate movarab dasht

            for (int j=1 ; j<16 ; j+=2) // horizontal wall
            {
                for (int k=0 ; k<15 ; k+=2)
                {
                    Node n1 = new Node(node.board);
                    n1.board.putWall(j,k,j,k+2);
                    pq.add(n1);
                }
            }

            for (int j=0 ; j<17 ; j+=2) // vertical wall
            {
                for (int k=1 ; k<15 ; k+=2)
                {
                    Node n1 = new Node(node.board);
                    n1.board.putWall(j,k,j+2,k);
                    pq.add(n1);
                }
            }
        }

        return pq;
    }
}

class MaxComperator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        if (n1.h > n2.h)
            return 1;
        if (n1.h == n2.h)
            return 0;
        return -1;
    }
}

class MinComperator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.h > n2.h)
            return -1;
        if (n1.h == n2.h)
            return 0;
        return 1;
    }
}
