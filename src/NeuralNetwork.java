import java.util.ArrayList;

public class NeuralNetwork {

    private Input_layer inputLayer;
    private ArrayList<Intermediate_layer> layers = new ArrayList<>();
    private double learningrate;
    private ArrayList<String> lossHistory = new ArrayList<>();

    // ----------------------------------------------------------------
    // Konstruktor
    // Beispiel: new NeuralNetwork(2, new int[]{4, 4, 1}, 0.1)
    //           = 2 Inputs -> 4 -> 4 -> 1 Output
    // ----------------------------------------------------------------
    public NeuralNetwork(int num_inputs, int[] layer_sizes, double learningrate) {
        this.learningrate = learningrate;

        ArrayList<Double> dummyInput = new ArrayList<>();
        for (int i = 0; i < num_inputs; i++) dummyInput.add(0.0);
        inputLayer = new Input_layer(dummyInput);

        layers.add(new Intermediate_layer(layer_sizes[0], inputLayer));
        for (int i = 1; i < layer_sizes.length; i++) {
            layers.add(new Intermediate_layer(layer_sizes[i], layers.get(i - 1)));
        }
    }

    // ----------------------------------------------------------------
    // Forward Pass — gibt Aktivierungen aller Schichten zurück
    // ----------------------------------------------------------------
    private ArrayList<Activations_functions> forward(ArrayList<Double> input) {
        inputLayer.input = input;
        ArrayList<Activations_functions> activations = new ArrayList<>();
        Activations_functions prev = null;

        for (int l = 0; l < layers.size(); l++) {
            ArrayList<ArrayList<Double>> temp = new ArrayList<>();
            Calc_weigthed_sum calc;

            if (l == 0) {
                calc = new Calc_weigthed_sum(layers.get(l));
            } else {
                calc = new Calc_weigthed_sum(layers.get(l), prev);
            }

            temp.add(calc.weights_of_this_layer);
            Activations_functions act = new Activations_functions();
            act.sigmoid(temp);
            activations.add(act);
            prev = act;
        }

        return activations;
    }

    // ----------------------------------------------------------------
    // Training
    // ----------------------------------------------------------------
    public void train(ArrayList<ArrayList<Double>> inputs,
                      ArrayList<ArrayList<Double>> expected_values,
                      int epochs,
                      int log_every) {

        int numLayers = layers.size();

        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0.0;

            for (int i = 0; i < inputs.size(); i++) {

                // --- Forward Pass ---
                ArrayList<Activations_functions> activations = forward(inputs.get(i));
                Activations_functions actOutput = activations.get(numLayers - 1);

                // --- Loss ---
                Intermediate_layer outputLayer = layers.get(numLayers - 1);
                Loss_Function loss = new Loss_Function();
                loss.sum = 0;
                loss.sum_divided_by_the_number_of_endoneurones = 0;
                loss.binary_cross_entropy(outputLayer.number_of_neurons,
                        actOutput.activations_for_neurons, expected_values.get(i));
                totalLoss += loss.sum_divided_by_the_number_of_endoneurones;

                // --- Backprop: Deltas für jede Schicht berechnen ---
                // deltas.get(l) = Deltas der Schicht l
                ArrayList<ArrayList<ArrayList<Double>>> deltas = new ArrayList<>();
                for (int l = 0; l < numLayers; l++) deltas.add(null);

                // Output-Schicht: delta = aktivierung - zielwert
                ArrayList<ArrayList<Double>> outputDeltas = new ArrayList<>();
                for (int n = 0; n < outputLayer.number_of_neurons; n++) {
                    double d = actOutput.activations_for_neurons.get(n).get(0)
                            - expected_values.get(i).get(n);
                    ArrayList<Double> dl = new ArrayList<>();
                    dl.add(d);
                    outputDeltas.add(dl);
                }
                deltas.set(numLayers - 1, outputDeltas);

                // Alle anderen Schichten rückwärts
                for (int l = numLayers - 2; l >= 0; l--) {
                    Intermediate_layer layerAbove = layers.get(l + 1);
                    ArrayList<ArrayList<Double>> deltasAbove = deltas.get(l + 1);
                    Activations_functions actThis = activations.get(l);
                    int neuronsThis = layers.get(l).number_of_neurons;
                    int neuronsAbove = layerAbove.number_of_neurons;

                    ArrayList<ArrayList<Double>> thisDeltas = new ArrayList<>();

                    for (int n = 0; n < neuronsThis; n++) {
                        // Summe: für jedes Neuron der Schicht darüber: delta * gewicht
                        double sum = 0;
                        for (int a = 0; a < neuronsAbove; a++) {
                            double w = layerAbove.neuron_list.get(a).get(n).get(0);
                            double d = deltasAbove.get(a).get(0);
                            sum += d * w;
                        }
                        // Sigmoid-Ableitung: a * (1 - a)
                        double act = actThis.activations_for_neurons.get(n).get(0);
                        double delta = sum * act * (1 - act);
                        ArrayList<Double> dl = new ArrayList<>();
                        dl.add(delta);
                        thisDeltas.add(dl);
                    }

                    deltas.set(l, thisDeltas);
                }

                // --- Gewichte und Biases aktualisieren ---
                for (int l = 0; l < numLayers; l++) {
                    Intermediate_layer layer = layers.get(l);
                    ArrayList<ArrayList<Double>> layerDeltas = deltas.get(l);

                    // Biases
                    ArrayList<ArrayList<Double>> newBiases =
                            Gradient_Decent.gradient_for_bias(layerDeltas, layer.bias_list, learningrate);
                    new Update_biases(newBiases, layer.bias_list);

                    // Gewichte
                    ArrayList<ArrayList<Double>> newWeights;
                    if (l == 0) {
                        newWeights = Gradient_Decent.gradient_for_weight(
                                layerDeltas, layer.neuron_list, inputs.get(i), learningrate);
                    } else {
                        newWeights = Gradient_Decent.gradient_for_weight(
                                layerDeltas, layer.neuron_list, activations.get(l - 1), learningrate);
                    }
                    new Update_weights(newWeights, layer.neuron_list);
                }
            }

            if ((epoch + 1) % log_every == 0) {
                double avg = totalLoss / inputs.size();
                lossHistory.add(String.format("Epoch %6d | Avg Loss: %.8f", epoch + 1, avg));
            }
        }
    }

    // ----------------------------------------------------------------
    // Vorhersage
    // ----------------------------------------------------------------
    public double predict(ArrayList<Double> input) {
        ArrayList<Activations_functions> activations = forward(input);
        return activations.get(layers.size() - 1).activations_for_neurons.get(0).get(0);
    }

    // ----------------------------------------------------------------
    // Ausgabe
    // ----------------------------------------------------------------
    public void printLossHistory() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     LOSS-VERLAUF                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        for (String entry : lossHistory) System.out.println(entry);
    }

    public void printPredictions(ArrayList<ArrayList<Double>> inputs,
                                 ArrayList<ArrayList<Double>> expected_values) {
        System.out.println("\nVORHERSAGEN:\n");
        System.out.println("Input  | Expected | Predicted | Rounded");
        System.out.println("-------|----------|-----------|----------");
        for (int i = 0; i < inputs.size(); i++) {
            double pred = predict(inputs.get(i));
            int rounded = pred > 0.5 ? 1 : 0;
            double exp = expected_values.get(i).get(0);
            ArrayList<Double> in = inputs.get(i);
            System.out.printf("[%.0f,%.0f] |   %.1f    |  %.4f   |    %d\n",
                    in.get(0), in.get(1), exp, pred, rounded);
        }
    }
}