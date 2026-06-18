import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main_06_3DCluster {
    public static void main(String[] args) {

        // ============================================================
        // 3D PUNKTWOLKEN KLASSIFIKATION
        // 4 Cluster in einem 3D-Würfel (x, y, z zwischen 0 und 1)
        // Jeder Cluster liegt in einer anderen Ecke des Würfels.
        // 20 Punkte pro Cluster mit Gauß'schem Rauschen.
        //
        // Cluster 0: oben-vorne-links    (0.15, 0.15, 0.15)
        // Cluster 1: oben-hinten-rechts  (0.85, 0.15, 0.85)
        // Cluster 2: unten-vorne-rechts  (0.15, 0.85, 0.85)
        // Cluster 3: unten-hinten-links  (0.85, 0.85, 0.15)
        //
        // Spannend: Mittelpunkt (0.5,0.5,0.5) hat keine richtige Antwort!
        // ============================================================

        double[][] centers = {
            {0.15, 0.15, 0.15},
            {0.85, 0.15, 0.85},
            {0.15, 0.85, 0.85},
            {0.85, 0.85, 0.15},
        };
        String[] names = {"oben-vorne-links","oben-hinten-rechts","unten-vorne-rechts","unten-hinten-links"};

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();
        ArrayList<Integer> labels = new ArrayList<>();

        Random rng = new Random(42);
        double spread = 0.12;

        for (int c=0; c<4; c++) {
            for (int p=0; p<20; p++) {
                double x = clamp(centers[c][0]+gauss(rng)*spread);
                double y = clamp(centers[c][1]+gauss(rng)*spread);
                double z = clamp(centers[c][2]+gauss(rng)*spread);
                inputs.add(new ArrayList<>(Arrays.asList(x,y,z)));
                ArrayList<Double> lbl = new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0));
                lbl.set(c, 1.0);
                expected.add(lbl);
                labels.add(c);
            }
        }

        System.out.println("Cluster-Zentren:");
        for (int c=0;c<4;c++) System.out.printf("  C%d %-20s (%.2f, %.2f, %.2f)%n",
            c, names[c], centers[c][0], centers[c][1], centers[c][2]);
        System.out.println("\n" + inputs.size() + " Punkte. Starte Training...\n");

        NeuralNetwork net = new NeuralNetwork(3, new int[]{16, 8, 4}, 0.1, 0.01);
        net.train(inputs, expected, 100000000, 500);

        int correct=0;
        int[][] confusion = new int[4][4];
        for (int i=0;i<inputs.size();i++) {
            int pred = argmax(net.predict(inputs.get(i)));
            int actual = labels.get(i);
            if(pred==actual) correct++;
            confusion[actual][pred]++;
        }
        System.out.printf("Trainingsgenauigkeit: %d/%d = %.1f%%%n%n", correct, inputs.size(), 100.0*correct/inputs.size());

        System.out.println("TEST MIT NEUEN PUNKTEN:\n");
        double[][] testPoints = {
            {0.10,0.10,0.10},{0.90,0.10,0.90},{0.10,0.90,0.90},{0.90,0.90,0.10},
            {0.50,0.50,0.50},{0.20,0.20,0.20},{0.75,0.20,0.75}
        };
        int[] expClusters = {0,1,2,3,-1,0,1};

        for (int t=0;t<testPoints.length;t++) {
            ArrayList<Double> input = new ArrayList<>();
            for (double v:testPoints[t]) input.add(v);
            ArrayList<Double> pred = net.predict(input);
            int predicted = argmax(pred);
            double conf = pred.get(predicted)*100;
            String result = expClusters[t]==-1 ? "? (unklar)" : (predicted==expClusters[t]?"✓":"✗");
            System.out.printf("(%.2f,%.2f,%.2f) → C%d %-20s %.0f%% %s%n",
                testPoints[t][0],testPoints[t][1],testPoints[t][2],
                predicted, names[predicted], conf, result);
        }
        System.out.println("\nHinweis: (0.5,0.5,0.5) hat keine richtige Antwort —");
        System.out.println("niedrige Konfidenz dort wäre das korrekte Verhalten.");
        net.printLossHistory();
    }

    static double clamp(double v) { return Math.max(0.01,Math.min(0.99,v)); }
    static double gauss(Random rng) {
        double u=0,v=0;
        while(u==0) u=rng.nextDouble();
        while(v==0) v=rng.nextDouble();
        return Math.sqrt(-2*Math.log(u))*Math.cos(2*Math.PI*v);
    }
    static int argmax(ArrayList<Double> v) {
        int b=0; for(int j=1;j<v.size();j++) if(v.get(j)>v.get(b)) b=j; return b;
    }
}
