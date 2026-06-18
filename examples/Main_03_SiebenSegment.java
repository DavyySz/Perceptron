import java.util.ArrayList;
import java.util.Arrays;

public class Main_03_SiebenSegment {
    public static void main(String[] args) {

        // ============================================================
        // SIEBEN-SEGMENT-ANZEIGE
        // Input: 4-Bit Binärzahl (0-9)
        // Output: 7 Segmente [a, b, c, d, e, f, g]
        //
        //   aaa
        //  f   b
        //   ggg
        //  e   c
        //   ddd
        // ============================================================

        double[][] segments = {
            {1,1,1,1,1,1,0}, // 0
            {0,1,1,0,0,0,0}, // 1
            {1,1,0,1,1,0,1}, // 2
            {1,1,1,1,0,0,1}, // 3
            {0,1,1,0,0,1,1}, // 4
            {1,0,1,1,0,1,1}, // 5
            {1,0,1,1,1,1,1}, // 6
            {1,1,1,0,0,0,0}, // 7
            {1,1,1,1,1,1,1}, // 8
            {1,1,1,1,0,1,1}, // 9
        };

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();

        for (int n = 0; n < 10; n++) {
            double b3=(n>>3)&1, b2=(n>>2)&1, b1=(n>>1)&1, b0=n&1;
            inputs.add(new ArrayList<>(Arrays.asList(b3, b2, b1, b0)));
            ArrayList<Double> seg = new ArrayList<>();
            for (double s : segments[n]) seg.add(s);
            expected.add(seg);
            System.out.printf("Ziffer %d [%d%d%d%d] → [a=%d b=%d c=%d d=%d e=%d f=%d g=%d]%n",
                n,(int)b3,(int)b2,(int)b1,(int)b0,
                (int)segments[n][0],(int)segments[n][1],(int)segments[n][2],
                (int)segments[n][3],(int)segments[n][4],(int)segments[n][5],(int)segments[n][6]);
        }

        System.out.println("\nStarte Training...\n");

        NeuralNetwork net = new NeuralNetwork(4, new int[]{16, 12, 7}, 0.1, 0.01);
        net.train(inputs, expected, 100000000, 1000);
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}
