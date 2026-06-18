import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main_05_MiniMNIST {
    public static void main(String[] args) {

        // ============================================================
        // MINI-MNIST: Handschriftliche Ziffern auf 5x5 Pixel
        // 25 Inputs, 10 Outputs (eine pro Ziffer 0-9)
        // Jede Ziffer in 11 Varianten: sauber + verrauscht + handgeschrieben
        // Testet ob das Netz wirklich generalisiert oder nur auswendig lernt.
        // ============================================================

        int[][][] base = {
            {{0,1,1,1,0},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{0,1,1,1,0}},
            {{0,0,1,0,0},{0,1,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,1,1,1,0}},
            {{0,1,1,1,0},{1,0,0,0,1},{0,0,1,1,0},{0,1,0,0,0},{1,1,1,1,1}},
            {{1,1,1,1,0},{0,0,0,0,1},{0,1,1,1,0},{0,0,0,0,1},{1,1,1,1,0}},
            {{1,0,0,1,0},{1,0,0,1,0},{1,1,1,1,1},{0,0,0,1,0},{0,0,0,1,0}},
            {{1,1,1,1,1},{1,0,0,0,0},{1,1,1,1,0},{0,0,0,0,1},{1,1,1,1,0}},
            {{0,1,1,1,0},{1,0,0,0,0},{1,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0}},
            {{1,1,1,1,1},{0,0,0,1,0},{0,0,1,0,0},{0,1,0,0,0},{0,1,0,0,0}},
            {{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0}},
            {{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,1},{0,0,0,0,1},{0,1,1,1,0}}
        };

        int[][][][] handwritten = {
            {{{0,0,1,1,0},{0,1,0,0,1},{1,0,0,0,1},{0,1,0,0,1},{0,0,1,1,0}},{{0,1,1,0,0},{1,0,0,1,0},{1,0,0,1,0},{1,0,0,1,0},{0,1,1,0,0}}},
            {{{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}},{{0,1,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,1,1,1,0}}},
            {{{1,1,1,0,0},{0,0,0,1,0},{0,1,1,0,0},{1,0,0,0,0},{1,1,1,1,0}},{{0,1,1,1,0},{0,0,0,1,0},{0,0,1,0,0},{0,1,0,0,0},{1,1,1,1,1}}},
            {{{0,1,1,1,0},{0,0,0,0,1},{0,0,1,1,0},{0,0,0,0,1},{0,1,1,1,0}},{{1,1,1,0,0},{0,0,0,1,0},{0,1,1,0,0},{0,0,0,1,0},{1,1,1,0,0}}},
            {{{0,0,1,1,0},{0,1,0,1,0},{1,0,0,1,0},{1,1,1,1,1},{0,0,0,1,0}},{{1,0,1,0,0},{1,0,1,0,0},{1,1,1,1,0},{0,0,1,0,0},{0,0,1,0,0}}},
            {{{1,1,1,1,0},{1,0,0,0,0},{0,1,1,1,0},{0,0,0,0,1},{1,1,1,1,0}},{{1,1,1,0,0},{1,0,0,0,0},{1,1,1,0,0},{0,0,0,1,0},{1,1,1,0,0}}},
            {{{0,0,1,1,0},{0,1,0,0,0},{1,1,1,0,0},{1,0,0,1,0},{0,1,1,0,0}},{{0,1,1,0,0},{1,0,0,0,0},{1,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0}}},
            {{{1,1,1,1,0},{0,0,0,1,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}},{{1,1,1,1,1},{0,0,1,0,0},{0,0,1,0,0},{0,1,0,0,0},{0,1,0,0,0}}},
            {{{1,1,1,0,0},{1,0,0,1,0},{1,1,1,0,0},{1,0,0,1,0},{1,1,1,0,0}},{{0,1,1,1,0},{1,0,0,1,0},{0,1,1,0,0},{1,0,0,1,0},{0,1,1,1,0}}},
            {{{0,1,1,0,0},{1,0,0,1,0},{0,1,1,1,0},{0,0,0,1,0},{0,1,1,0,0}},{{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,1},{0,0,0,1,0},{0,1,1,0,0}}}
        };

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();
        Random rng = new Random(42);

        System.out.println("Basisformen:");
        for (int d = 0; d < 10; d++) {
            System.out.println("Ziffer " + d + ":");
            for (int row=0; row<5; row++) {
                System.out.print("  ");
                for (int col=0; col<5; col++) System.out.print(base[d][row][col]==1?"█":"░");
                System.out.println();
            }
            for (int v=0; v<5; v++) { inputs.add(addNoise(base[d],0.10,rng)); expected.add(oneHot(d)); }
            for (int v=0; v<3; v++) { inputs.add(addNoise(base[d],0.20,rng)); expected.add(oneHot(d)); }
            for (int v=0; v<2; v++) { inputs.add(toInput(handwritten[d][v])); expected.add(oneHot(d)); }
            inputs.add(toInput(base[d])); expected.add(oneHot(d));
        }

        System.out.println("\n" + inputs.size() + " Trainingsbeispiele. Starte Training...\n");

        NeuralNetwork net = new NeuralNetwork(25, new int[]{64, 32, 10}, 0.1, 0.05);
        net.train(inputs, expected, 100000000, 1000);

        int correct=0;
        for (int i=0; i<inputs.size(); i++) {
            ArrayList<Double> pred = net.predict(inputs.get(i));
            if (argmax(pred)==i/11) correct++;
        }
        System.out.printf("Trainingsgenauigkeit: %d/%d = %.1f%%%n%n", correct, inputs.size(), 100.0*correct/inputs.size());

        // Testbilder
        System.out.println("TEST MIT NEUEN UNBEKANNTEN BILDERN:\n");
        int[][][] testDigits = {
            {{0,1,1,1,0},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{0,1,1,1,0}},
            {{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{1,1,1,0,0}},
            {{1,1,1,1,0},{0,0,0,1,0},{0,1,1,0,0},{1,0,0,0,0},{1,1,1,1,1}},
            {{1,1,1,0,0},{0,0,1,0,0},{0,1,1,0,0},{0,0,1,0,0},{1,1,1,0,0}},
            {{1,0,1,0,0},{1,0,1,0,0},{1,1,1,1,0},{0,0,1,0,0},{0,0,1,0,0}},
            {{1,1,1,1,0},{1,1,0,0,0},{1,1,1,1,0},{0,0,1,1,0},{1,1,1,1,0}},
            {{0,1,1,0,0},{1,0,0,0,0},{1,1,1,0,0},{1,0,0,1,0},{0,1,1,0,0}},
            {{1,1,1,1,1},{0,0,0,1,0},{0,0,1,1,0},{0,0,1,0,0},{0,1,0,0,0}},
            {{0,1,1,0,0},{1,0,0,1,0},{0,1,1,0,0},{1,0,0,1,0},{0,1,1,1,0}},
            {{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,1},{0,0,0,1,0},{0,0,1,0,0}}
        };

        int testCorrect=0;
        for (int t=0; t<testDigits.length; t++) {
            ArrayList<Double> input = toInput(testDigits[t]);
            ArrayList<Double> pred = net.predict(input);
            int predicted = argmax(pred);
            boolean ok = predicted==t;
            if(ok) testCorrect++;
            System.out.println("Soll " + t + " sein:");
            for (int row=0; row<5; row++) {
                System.out.print("  ");
                for (int col=0; col<5; col++) System.out.print(testDigits[t][row][col]==1?"█":"░");
                System.out.println();
            }
            System.out.printf("  → Erkannt als %d (%.0f%% Konfidenz)  %s%n%n",
                predicted, pred.get(predicted)*100, ok?"✓":"✗");
        }
        System.out.printf("Testgenauigkeit: %d/%d = %.0f%%%n", testCorrect, testDigits.length, 100.0*testCorrect/testDigits.length);
        net.printLossHistory();
    }

    static ArrayList<Double> toInput(int[][] g) {
        ArrayList<Double> r = new ArrayList<>();
        for (int[] row:g) for (int p:row) r.add((double)p);
        return r;
    }
    static ArrayList<Double> addNoise(int[][] g, double rate, Random rng) {
        ArrayList<Double> r = new ArrayList<>();
        for (int[] row:g) for (int p:row) r.add(rng.nextDouble()<rate?1.0-p:(double)p);
        return r;
    }
    static ArrayList<Double> oneHot(int d) {
        ArrayList<Double> r = new ArrayList<>();
        for (int j=0;j<10;j++) r.add(j==d?1.0:0.0);
        return r;
    }
    static int argmax(ArrayList<Double> v) {
        int b=0; for(int j=1;j<v.size();j++) if(v.get(j)>v.get(b)) b=j; return b;
    }
}
