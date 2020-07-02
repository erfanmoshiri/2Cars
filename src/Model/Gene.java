package Model;

import java.util.ArrayList;

public class Gene {

    final int chromosomeSize = 8;

    double[] chromosome;
    int[][] statics;

    public Gene(int j) {
        statics = new int[chromosomeSize][];
        for (int i = 0; i < 8; i++) {
            statics[i] = new int[2];
            if (i == j) {
                statics[i][0] = statics[i][1] = 0;
            } else {
                statics[i][0] = statics[i][1] = -2;
            }
        }
    }

}


 class Generation {

     final int generationSize = 6;

     ArrayList<Gene> genes;

 }
