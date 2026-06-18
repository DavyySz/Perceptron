import java.util.ArrayList;
import java.util.Arrays;

public class Main_04_Temperatur {
    public static void main(String[] args) {

        // ============================================================
        // TEMPERATUR-KLASSIFIKATION
        // Input: normalisierte Temperatur (0.0 = -20°C, 1.0 = 50°C)
        // Output: [kalt, warm, heiß]
        //   kalt  = unter 10°C  → [1,0,0]
        //   warm  = 10°C-25°C   → [0,1,0]
        //   heiß  = über 25°C   → [0,0,1]
        //
        // Erstes Problem mit kontinuierlichem Input (keine Binärzahlen).
        // ============================================================

        double[] temps  = {-20,-10,-5,0,5,9,10,14,18,20,23,25,26,30,35,40,45,50};
        int[]    labels = {  0,  0, 0,0,0,0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        String[] names  = {"kalt","warm","heiß"};

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();

        for (int i = 0; i < temps.length; i++) {
            double norm = (temps[i] + 20.0) / 70.0;
            inputs.add(new ArrayList<>(Arrays.asList(norm)));
            double k = labels[i]==0?1.0:0.0, w = labels[i]==1?1.0:0.0, h = labels[i]==2?1.0:0.0;
            expected.add(new ArrayList<>(Arrays.asList(k, w, h)));
            System.out.printf("%.0f°C (norm=%.2f) → %s%n", temps[i], norm, names[labels[i]]);
        }

        System.out.println("\nStarte Training...\n");

        NeuralNetwork net = new NeuralNetwork(1, new int[]{8, 4, 3}, 0.1, 0.01);
        net.train(inputs, expected, 100000000, 500);
        net.printPredictions(inputs, expected);

        System.out.println("\nTEST MIT UNBEKANNTEN TEMPERATUREN:\n");
        double[] testTemps = {-15.0, 3.0, 12.0, 22.0, 28.0, 42.0};
        for (double temp : testTemps) {
            double norm = (temp + 20.0) / 70.0;
            ArrayList<Double> input = new ArrayList<>(Arrays.asList(norm));
            ArrayList<Double> pred = net.predict(input);
            int best = 0;
            for (int j=1; j<pred.size(); j++) if (pred.get(j)>pred.get(best)) best=j;
            System.out.printf("%.0f°C → %s  (kalt=%.3f warm=%.3f heiß=%.3f)%n",
                temp, names[best], pred.get(0), pred.get(1), pred.get(2));
        }

        net.printLossHistory();
    }
}
