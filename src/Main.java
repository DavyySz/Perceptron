import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // ============================================================
        // TRAININGSDATEN (XOR Problem)
        // ============================================================
        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected_values = new ArrayList<>();

        inputs.add(new ArrayList<>(Arrays.asList(0.0, 0.0)));
        expected_values.add(new ArrayList<>(Arrays.asList(0.0)));

        inputs.add(new ArrayList<>(Arrays.asList(0.0, 1.0)));
        expected_values.add(new ArrayList<>(Arrays.asList(1.0)));

        inputs.add(new ArrayList<>(Arrays.asList(1.0, 0.0)));
        expected_values.add(new ArrayList<>(Arrays.asList(1.0)));

        inputs.add(new ArrayList<>(Arrays.asList(1.0, 1.0)));
        expected_values.add(new ArrayList<>(Arrays.asList(0.0)));

        double learningrate = 0.1;

        // ============================================================
        // NETZWERK-INITIALISIERUNG
        // ============================================================
        Input_layer inputLayer = new Input_layer(inputs.get(0));
        Intermediate_layer hiddenLayer = new Intermediate_layer(4, inputLayer);
        Intermediate_layer outputLayer = new Intermediate_layer(1, hiddenLayer);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          NEURONALES NETZWERK TRAINING GESTARTET            ║");
        System.out.println("║  Struktur: 2 Input -> 4 Hidden -> 1 Output (XOR-Problem)   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ============================================================
        // Loss-Verlauf sammeln (ein Eintrag pro 1000 Epochen)
        // ============================================================
        ArrayList<String> lossHistory = new ArrayList<>();

        // ============================================================
        // TRAINING
        // ============================================================
        int epochs = 100000;

        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0.0;

            for (int input = 0; input < inputs.size(); input++) {
                ArrayList<Double> currentInput = inputs.get(input);
                inputLayer.input = currentInput;

                // Forward Pass Hidden
                Calc_weigthed_sum calcHiddenSum = new Calc_weigthed_sum(hiddenLayer);
                ArrayList<ArrayList<Double>> tempHidden = new ArrayList<>();
                tempHidden.add(calcHiddenSum.weights_of_this_layer);

                Activations_functions actHidden = new Activations_functions();
                actHidden.sigmoid(tempHidden);

                // Forward Pass Output
                Calc_weigthed_sum calcOutputSum = new Calc_weigthed_sum(outputLayer, actHidden);
                ArrayList<ArrayList<Double>> tempOutput = new ArrayList<>();
                tempOutput.add(calcOutputSum.weights_of_this_layer);

                Activations_functions actOutput = new Activations_functions();
                actOutput.sigmoid(tempOutput);

                // Loss berechnen
                Loss_Function loss = new Loss_Function();
                loss.sum = 0;
                loss.sum_divided_by_the_number_of_endoneurones = 0;
                loss.binary_cross_entropy(outputLayer.number_of_neurons,
                        actOutput.activations_for_neurons, expected_values.get(input));
                totalLoss += loss.sum_divided_by_the_number_of_endoneurones;

                // Backpropagation
                Backpropagation_last_two_layers backprop =
                        new Backpropagation_last_two_layers(actOutput, actHidden,
                                outputLayer, hiddenLayer.number_of_neurons, expected_values.get(input));

                // Bias Updates
                ArrayList<ArrayList<Double>> newBiasesOut =
                        Gradient_Decent.gradient_for_bias(backprop.loss_for_neurons_outputlayer,
                                outputLayer.bias_list, learningrate);
                Update_biases updateBiasesOut = new Update_biases(newBiasesOut, outputLayer.bias_list);

                ArrayList<ArrayList<Double>> newBiasesHid =
                        Gradient_Decent.gradient_for_bias(backprop.loss_for_neurons_vorletzter_layer,
                                hiddenLayer.bias_list, learningrate);
                Update_biases updateBiasesHid = new Update_biases(newBiasesHid, hiddenLayer.bias_list);

                // Weight Updates
                ArrayList<ArrayList<Double>> newWeightsOut =
                        Gradient_Decent.gradient_for_weight(backprop.loss_for_neurons_outputlayer,
                                outputLayer.neuron_list, actHidden, learningrate);
                Update_weights updateWeightsOut = new Update_weights(newWeightsOut, outputLayer.neuron_list);

                ArrayList<ArrayList<Double>> newWeightsHid =
                        Gradient_Decent.gradient_for_weight(backprop.loss_for_neurons_vorletzter_layer,
                                hiddenLayer.neuron_list, currentInput, learningrate);
                Update_weights updateWeightsHid = new Update_weights(newWeightsHid, hiddenLayer.neuron_list);
            }

            // Alle 1000 Epochen den Loss merken (still — keine Ausgabe jetzt)
            if ((epoch + 1) % 1000 == 0) {
                double avgLoss = totalLoss / inputs.size();
                lossHistory.add(String.format("Epoch %6d | Avg Loss: %.8f", epoch + 1, avgLoss));
            }
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║               TRAINING ABGESCHLOSSEN                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ============================================================
        // VORHERSAGEN
        // ============================================================
        System.out.println("VORHERSAGEN:\n");
        System.out.println("Input  | Expected | Predicted | Rounded");
        System.out.println("-------|----------|-----------|----------");

        for (int i = 0; i < inputs.size(); i++) {
            ArrayList<Double> testInput = inputs.get(i);
            inputLayer.input = testInput;

            Calc_weigthed_sum calcTestHiddenSum = new Calc_weigthed_sum(hiddenLayer);
            ArrayList<ArrayList<Double>> tempTestHidden = new ArrayList<>();
            tempTestHidden.add(calcTestHiddenSum.weights_of_this_layer);

            Activations_functions actTestHidden = new Activations_functions();
            actTestHidden.sigmoid(tempTestHidden);

            Calc_weigthed_sum calcTestOutputSum = new Calc_weigthed_sum(outputLayer, actTestHidden);
            ArrayList<ArrayList<Double>> tempTestOutput = new ArrayList<>();
            tempTestOutput.add(calcTestOutputSum.weights_of_this_layer);

            Activations_functions actTestOutput = new Activations_functions();
            actTestOutput.sigmoid(tempTestOutput);

            double pred = actTestOutput.activations_for_neurons.get(0).get(0);
            int rounded = pred > 0.5 ? 1 : 0;
            double expected = expected_values.get(i).get(0);

            System.out.printf("[%.0f,%.0f] |   %.1f    |  %.4f   |    %d\n",
                    testInput.get(0), testInput.get(1), expected, pred, rounded);
        }

        // ============================================================
        // LOSS-VERLAUF (am Ende, übersichtlich gebündelt)
        // ============================================================
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   LOSS-VERLAUF (je 1000 Epochen)           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        for (String entry : lossHistory) {
            System.out.println(entry);
        }
    }
}