import java.util.ArrayList;
import java.util.Arrays;

public class Main_02_Paritaet {
    public static void main(String[] args) {

        // ============================================================
        // 4-BIT PARITÄTSPROBLEM
        // Output = 1 wenn die Anzahl der Einsen GERADE ist.
        // Historisch bekannt als schwieriges Problem für neuronale Netze
        // (Minsky & Papert, 1969). Alle 16 Kombinationen.
        // ============================================================

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();

        for (int n = 0; n < 16; n++) {
            double b3 = (n >> 3) & 1;
            double b2 = (n >> 2) & 1;
            double b1 = (n >> 1) & 1;
            double b0 = n & 1;
            int ones = (int)(b3 + b2 + b1 + b0);
            double parity = (ones % 2 == 0) ? 1.0 : 0.0;
            inputs.add(new ArrayList<>(Arrays.asList(b3, b2, b1, b0)));
            expected.add(new ArrayList<>(Arrays.asList(parity)));
            System.out.printf("[%.0f%.0f%.0f%.0f] = %d Einsen → erwartet %.0f%n", b3, b2, b1, b0, ones, parity);
        }

        System.out.println("\nStarte Training...\n");

        NeuralNetwork net = new NeuralNetwork(4, new int[]{16, 8, 1}, 0.1, 0.001);
        net.train(inputs, expected, 100000000, 1000);
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}
