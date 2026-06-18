import java.util.ArrayList;
import java.util.Arrays;

public class Main_01_XOR {
    public static void main(String[] args) {

        // ============================================================
        // XOR PROBLEM
        // Das klassische Problem das nicht linear trennbar ist.
        // Braucht mindestens eine Hidden Layer.
        //
        // [0,0] → 0
        // [0,1] → 1
        // [1,0] → 1
        // [1,1] → 0
        // ============================================================

        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();

        inputs.add(new ArrayList<>(Arrays.asList(0.0, 0.0))); expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 1.0))); expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 0.0))); expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 1.0))); expected.add(new ArrayList<>(Arrays.asList(0.0)));

        NeuralNetwork net = new NeuralNetwork(2, new int[]{4, 1}, 0.1, 0.001);
        net.train(inputs, expected, 100000, 1000);
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}
