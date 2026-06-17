import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

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
        NeuralNetwork net = new NeuralNetwork(2, new int[]{16, 8, 4, 1}, 0.1);

        net.train(inputs, expected, 10000, 1000);
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}