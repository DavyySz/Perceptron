import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList<Double> inputs_0 = new ArrayList<>(Arrays.asList(0.0, 0.0)); // Aktivierungen der Eingabeneuronen
        ArrayList<Double> inputs_1 = new ArrayList<>(Arrays.asList(0.0, 1.0)); // Aktivierungen der Eingabeneuronen
        ArrayList<Double> inputs_2 = new ArrayList<>(Arrays.asList(1.0, 0.0)); // Aktivierungen der Eingabeneuronen
        ArrayList<Double> inputs_3 = new ArrayList<>(Arrays.asList(1.0, 1.0)); // Aktivierungen der Eingabeneuronen
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



        ArrayList<Double> expected_values_inputs_0 = new ArrayList<>(Arrays.asList(0.0));
        ArrayList<Double> expected_values_inputs_1 = new ArrayList<>(Arrays.asList(1.0));
        ArrayList<Double> expected_values_inputs_2 = new ArrayList<>(Arrays.asList(1.0));
        ArrayList<Double> expected_values_inputs_3 = new ArrayList<>(Arrays.asList(0.0));


        ArrayList<ArrayList<Double>> inputs = new ArrayList<>();


        inputs.add(inputs_0);
        inputs.add(inputs_1);
        inputs.add(inputs_2);
        inputs.add(inputs_3);



        ArrayList<ArrayList<Double>> expected_values = new ArrayList<>();


        expected_values.add(expected_values_inputs_0);
        expected_values.add(expected_values_inputs_1);
        expected_values.add(expected_values_inputs_2);
        expected_values.add(expected_values_inputs_3);


        double learningrate = 0.5;

        //-----------------activation first tiefenlayer-------------------------------------------------------------//
        Input_layer inputLayer = new Input_layer(inputs_0); // erstellt die eingabeschicht mit Eingabeknoten


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_1 = new Intermediate_layer(2, inputLayer); // erstellt die erste Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_1 = new Calc_weigthed_sum(intermediateLayer_1); // berechnet die Gewichtete summe für jedes Neuron der ersten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_1.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der ersten Tiefenschicht aus



        Activations_functions activation_for_intermediateLayer_1 = new Activations_functions();
        ArrayList<ArrayList<Double>> activations_for_neurons = new ArrayList<>();

        activations_for_neurons.add(calc_Weigthed_Sum_for_intermediateLayer_1.weights_of_this_layer);

        activation_for_intermediateLayer_1.sigmoid(activations_for_neurons);
        //-----------------activation first tiefenlayer-------------------------------------------------------------//








        //-------------------aktivations second layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_2 = new Intermediate_layer(2, intermediateLayer_1); // erstellt die zweite Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_2 = new Calc_weigthed_sum(intermediateLayer_2, activation_for_intermediateLayer_1); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_2 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_2 = new ArrayList<>();

        activations_for_neurons_tiefe_2.add(calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer);

        activation_for_intermediateLayer_2.sigmoid(activations_for_neurons_tiefe_2);


        //-------------------aktivations second layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//





        //-------------------aktivations 3 layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_3 = new Intermediate_layer(2, intermediateLayer_2); // erstellt die dritte Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_3 = new Calc_weigthed_sum(intermediateLayer_3, activation_for_intermediateLayer_2); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_3 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_3 = new ArrayList<>();

        activations_for_neurons_tiefe_3.add(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer);

        activation_for_intermediateLayer_3.sigmoid(activations_for_neurons_tiefe_3);


        //-------------------aktivations 3 layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//




        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_4 = new Intermediate_layer(1, intermediateLayer_3); // erstellt die vierte Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_4 = new Calc_weigthed_sum(intermediateLayer_4, activation_for_intermediateLayer_3); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_4 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_4 = new ArrayList<>();

        activations_for_neurons_tiefe_4.add(calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer);

        activation_for_intermediateLayer_4.sigmoid(activations_for_neurons_tiefe_4);


        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//




        //-------------------loss berechnen-----------------------------------------------------------------------------------------------------------//
        System.out.println("loss berechnen");
        Loss_Function loss = new Loss_Function();
        loss.binary_cross_entropy(intermediateLayer_4.number_of_neurons, activation_for_intermediateLayer_4.activations_for_neurons, expected_values_inputs_0);
        //--------------------------------------------------------------------------------------------------------------------------------------------//




        System.out.println("|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n");
















    for(int epoch = 0; epoch < 10000; epoch++) {
        for (int input = 0; input < inputs.size(); input++) {
            System.out.println("\n\n");
            System.out.println("jetzt werden für input " + input + " die gewichte und biases angepasst\n-\n\n-\n\n-\n\n-\n\n-\n\n-\n\n-\n\n-\n");


            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
            //-----------------------------Backpropagation weights letzten 4 Schichten--------------------------------------------------------------------------------------------------------------------------------------------------------------------//
            Backpropagation_last_two_layers backpropagation_outputlayer_and_layer_before_it = new Backpropagation_last_two_layers(activation_for_intermediateLayer_4, activation_for_intermediateLayer_3, intermediateLayer_4, intermediateLayer_3.number_of_neurons, expected_values.get(input));

            System.out.println("Delta Outputschicht   :    " + backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_outputlayer);
            System.out.println("Delta vorletzter layer:    " + backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer);
            //-----------------------------Backpropagation letzten beiden Schichten--------------------------------------------------------------------------------------------------------------------------------------------------------------------//


            System.out.println("\n");
            System.out.println(intermediateLayer_1.neuron_list + "\n");


            System.out.println("||||||||||||||||||||||||||||  deltas für Schicht 2 werden berechnt  |||||||||||||||||||||||||||| \n");
            Backpropafation_depth_layer delta_layer_2 = new Backpropafation_depth_layer(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer, intermediateLayer_3.number_of_neurons, intermediateLayer_2.number_of_neurons, intermediateLayer_3, activation_for_intermediateLayer_2);


            System.out.println("||||||||||||||||||||||||||||  deltas für Schicht 1 werden berechnt  |||||||||||||||||||||||||||| \n");
            Backpropafation_depth_layer delta_layer_1 = new Backpropafation_depth_layer(delta_layer_2.delta_of_this_layer, intermediateLayer_2.number_of_neurons, intermediateLayer_1.number_of_neurons, intermediateLayer_2, activation_for_intermediateLayer_1);


            //---------------------------calc gradient--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            //---------------------------calc new biases--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            System.out.println("New biases for outputlayer");
            ArrayList<ArrayList<Double>> new_biases_for_outputlayer = Gradient_Decent.gradient_for_bias(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_outputlayer, intermediateLayer_4.bias_list, learningrate);
            Update_biases update_biases_for_outputlayer = new Update_biases(new_biases_for_outputlayer, intermediateLayer_4.bias_list);

            System.out.println("New biases for layer 3");
            ArrayList<ArrayList<Double>> new_biases_for_layer_3 = Gradient_Decent.gradient_for_bias(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer, intermediateLayer_3.bias_list, learningrate);
            Update_biases update_biases_for_layer_3 = new Update_biases(new_biases_for_layer_3, intermediateLayer_3.bias_list);

            System.out.println("New biases for layer 2");
            ArrayList<ArrayList<Double>> new_biases_for_layer_2 = Gradient_Decent.gradient_for_bias(delta_layer_2.delta_of_this_layer, intermediateLayer_2.bias_list, learningrate);
            Update_biases update_biases_for_layer_2 = new Update_biases(new_biases_for_layer_2, intermediateLayer_2.bias_list);

            System.out.println("New biases for layer 1");
            ArrayList<ArrayList<Double>> new_biases_for_layer_1 = Gradient_Decent.gradient_for_bias(delta_layer_1.delta_of_this_layer, intermediateLayer_1.bias_list, learningrate);
            Update_biases update_biases_for_layer_1 = new Update_biases(new_biases_for_layer_1, intermediateLayer_1.bias_list);

            System.out.println("new biases layer 4 (outputlayer): " + new_biases_for_outputlayer + "\n");
            System.out.println("new biases layer 3: " + new_biases_for_layer_3 + "\n");
            System.out.println("new biases layer 2: " + new_biases_for_layer_2 + "\n");
            System.out.println("new biases layer 1: " + new_biases_for_layer_1 + "\n");
            //---------------------------calc new biases--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            //---------------------------Backpropagation weights letzten 4 Schichten-----------------------------------------------------------------------------------------------------------------------------------------------------------/
            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
            //


            //---------------------------calc new weigths--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            //---------------------------calc new weights outputlayer--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            ArrayList<ArrayList<Double>> new_weights_for_outputlayer = Gradient_Decent.gradient_for_weight(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_outputlayer, intermediateLayer_4.neuron_list, activation_for_intermediateLayer_3, learningrate);
            System.out.println("new weights for layer 4 (outputlayer): " + new_weights_for_outputlayer);
            Update_weights update_weights_for_outputlayer = new Update_weights(new_weights_for_outputlayer, intermediateLayer_4.neuron_list);
            //---------------------------calc new weights outputlayer--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------calc new weights layer 3--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            ArrayList<ArrayList<Double>> new_weights_for_layer_3 = Gradient_Decent.gradient_for_weight(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer, intermediateLayer_3.neuron_list, activation_for_intermediateLayer_2, learningrate);
            System.out.println("new weights for layer 3: " + new_weights_for_layer_3);
            Update_weights update_weights_for_layer_3 = new Update_weights(new_weights_for_layer_3, intermediateLayer_3.neuron_list);
            //---------------------------calc new weights layer 3--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------calc new weights layer 2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            ArrayList<ArrayList<Double>> new_weights_for_layer_2 = Gradient_Decent.gradient_for_weight(delta_layer_2.delta_of_this_layer, intermediateLayer_2.neuron_list, activation_for_intermediateLayer_1, learningrate);
            System.out.println("new weights for layer 2: " + new_weights_for_layer_2);
            Update_weights update_weights_for_layer_2 = new Update_weights(new_weights_for_layer_2, intermediateLayer_2.neuron_list);
            //---------------------------calc new weights layer 2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------calc new weights layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            ArrayList<ArrayList<Double>> new_weights_for_layer_1 = Gradient_Decent.gradient_for_weight(delta_layer_1.delta_of_this_layer, intermediateLayer_1.neuron_list, inputs.get(input), learningrate);
            System.out.println("new weights for layer 1: " + new_weights_for_layer_1);
            Update_weights update_weights_for_layer_1 = new Update_weights(new_weights_for_layer_1, intermediateLayer_1.neuron_list);
            //---------------------------calc new weights layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            //---------------------------calc new weigths--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            activations_for_neurons.clear();

            activation_for_intermediateLayer_1.activations_for_neurons.clear();
            activation_for_intermediateLayer_2.activations_for_neurons.clear();
            activation_for_intermediateLayer_3.activations_for_neurons.clear();
            activation_for_intermediateLayer_4.activations_for_neurons.clear();


            System.out.println("Für die erste Schicht (forward pass) -------------------------------------------------------------------------------------------------------------------------)\n");
            activations_for_neurons.clear(); // alte aktivierungen entfernen
            activation_for_intermediateLayer_1.activations_for_neurons.clear();
            //---------------------------calc weigthed sum for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            calc_Weigthed_Sum_for_intermediateLayer_1 = new Calc_weigthed_sum(intermediateLayer_1, new_weights_for_layer_1, new_biases_for_layer_1, inputs.get(input));
            System.out.println("Weigthed_Sum_for_intermediateLayer_1: " + calc_Weigthed_Sum_for_intermediateLayer_1.weights_of_this_layer);
            //---------------------------calc weigthed sum for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------activation for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            activations_for_neurons.add(calc_Weigthed_Sum_for_intermediateLayer_1.weights_of_this_layer);
            activation_for_intermediateLayer_1.sigmoid(activations_for_neurons);
            //---------------------------activation for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            System.out.println("\n\n\n\n\n");
            System.out.println("foerwardpass nächster layer\n\n\n\n\n");


            System.out.println("Für die zweite Schicht (forward pass) -------------------------------------------------------------------------------------------------------------------------)\n");
            activations_for_neurons.clear(); // alte aktivierungen entfernen
            activation_for_intermediateLayer_2.activations_for_neurons.clear();
            //---------------------------calc weigthed sum for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            calc_Weigthed_Sum_for_intermediateLayer_2 = new Calc_weigthed_sum(intermediateLayer_2, new_weights_for_layer_2, new_biases_for_layer_2, activation_for_intermediateLayer_1.activations_for_neurons, 2);
            System.out.println("Weigthed_Sum_for_intermediateLayer_2: " + calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer);
            //---------------------------calc weigthed sum for layer 2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------activation for layer 2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            activations_for_neurons.add(calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer);
            activation_for_intermediateLayer_2.sigmoid(activations_for_neurons);
            //---------------------------activation for layer 2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            System.out.println("\n\n\n\n\n");
            System.out.println("foerwardpass nächster layer\n\n\n\n\n");


            System.out.println("Für die dritte (forward pass) -------------------------------------------------------------------------------------------------------------------------)\n");
            activations_for_neurons.clear(); // alte aktivierungen entfernen
            activation_for_intermediateLayer_3.activations_for_neurons.clear();
            //---------------------------calc weigthed sum for layer 1--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            calc_Weigthed_Sum_for_intermediateLayer_3 = new Calc_weigthed_sum(intermediateLayer_3, new_weights_for_layer_3, new_biases_for_layer_3, activation_for_intermediateLayer_2.activations_for_neurons, 2);
            System.out.println("Weigthed_Sum_for_intermediateLayer_3: " + calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer);
            //---------------------------calc weigthed sum for layer 3--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------activation for layer 3--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            activations_for_neurons.add(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer);
            activation_for_intermediateLayer_3.sigmoid(activations_for_neurons);
            //---------------------------activation for layer 3--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            System.out.println("\n\n\n\n\n");
            System.out.println("foerwardpass nächster layer\n\n\n\n\n");


            System.out.println("loss berechnen");
            loss.loss_of_o_single_neuron.clear();
            System.out.println("Für die vierte Schicht (outputlayer) (forward pass -------------------------------------------------------------------------------------------------------------------------)\n");
            activations_for_neurons.clear(); // alte aktivierungen entfernen
            activation_for_intermediateLayer_4.activations_for_neurons.clear();
            //---------------------------calc weigthed sum for layer 4--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            calc_Weigthed_Sum_for_intermediateLayer_4 = new Calc_weigthed_sum(intermediateLayer_4, new_weights_for_outputlayer, new_biases_for_outputlayer, activation_for_intermediateLayer_3.activations_for_neurons, 2);
            System.out.println("Weigthed_Sum_for_intermediateLayer_4: " + calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer);
            //---------------------------calc weigthed sum for layer 4--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //---------------------------activation for layer 4--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            activations_for_neurons.add(calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer);
            activation_for_intermediateLayer_4.sigmoid(activations_for_neurons);
            //---------------------------activation for layer 4--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




            //-------------------loss berechnen-----------------------------------------------------------------------------------------------------------//
            loss.sum = 0;
            loss.sum_divided_by_the_number_of_endoneurones = 0;
            loss.binary_cross_entropy(intermediateLayer_4.number_of_neurons, activation_for_intermediateLayer_4.activations_for_neurons, expected_values.get(input));
            //--------------------------------------------------------------------------------------------------------------------------------------------//


        }

    }






























    }


}
