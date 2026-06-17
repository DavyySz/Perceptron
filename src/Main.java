import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double the_accuracy_at_which_the_process_is_terminated = 0.001;

        // Trainingsdaten
        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 0.0)));  expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 1.0)));  expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 0.0)));  expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 1.0)));  expected.add(new ArrayList<>(Arrays.asList(0.0)));

        // ============================================================
        // Hier das Netz frei konfigurieren:
        //
        // new NeuralNetwork(inputs, schichten, learningrate)
        //
        // Beispiele:
        //   new NeuralNetwork(2, new int[]{4, 1}, 0.1)        // klassisch
        //   new NeuralNetwork(2, new int[]{8, 4, 1}, 0.1)     // tiefer
        //   new NeuralNetwork(2, new int[]{16, 8, 4, 1}, 0.1) // sehr tief
        // ============================================================
        NeuralNetwork net = new NeuralNetwork(2, new int[]{4, 1}, 0.2, the_accuracy_at_which_the_process_is_terminated);

        net.train(inputs, expected, 10000000, 100);
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}