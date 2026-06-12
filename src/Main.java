import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList<Double> inputs = new ArrayList<>(Arrays.asList(0.5, 0.3, 0.8, 0.6)); // Aktivierungen der Eingabeneuronen
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



        ArrayList<Double> expected_values = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
        double learningrate = 0.1;

        //-----------------activation first tiefenlayer-------------------------------------------------------------//
        Input_layer inputLayer = new Input_layer(inputs); // erstellt die eingabeschicht mit Eingabeknoten


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_1 = new Intermediate_layer(10, inputLayer); // erstellt die erste Tiefenschicht
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
        Intermediate_layer intermediateLayer_2 = new Intermediate_layer(10, intermediateLayer_1); // erstellt die zweite Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_2 = new Calc_weigthed_sum(intermediateLayer_2, activation_for_intermediateLayer_1); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_2 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_2 = new ArrayList<>();

        activations_for_neurons_tiefe_2.add(calc_Weigthed_Sum_for_intermediateLayer_2.weights_of_this_layer);

        activation_for_intermediateLayer_2.sigmoid(activations_for_neurons_tiefe_2);


        //-------------------aktivations second layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//





        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_3 = new Intermediate_layer(10, intermediateLayer_2); // erstellt die zweite Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_3 = new Calc_weigthed_sum(intermediateLayer_3, activation_for_intermediateLayer_2); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_3 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_3 = new ArrayList<>();

        activations_for_neurons_tiefe_3.add(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer);

        activation_for_intermediateLayer_3.sigmoid(activations_for_neurons_tiefe_3);


        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//




        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_4 = new Intermediate_layer(10, intermediateLayer_3); // erstellt die zweite Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_4 = new Calc_weigthed_sum(intermediateLayer_4, activation_for_intermediateLayer_3); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_4 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_4 = new ArrayList<>();

        activations_for_neurons_tiefe_4.add(calc_Weigthed_Sum_for_intermediateLayer_4.weights_of_this_layer);

        activation_for_intermediateLayer_4.sigmoid(activations_for_neurons_tiefe_4);


        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//




        //-------------------loss berechnen-----------------------------------------------------------------------------------------------------------//
        Loss_Function loss = new Loss_Function();
        loss.binary_cross_entropy(intermediateLayer_4.number_of_neurons, activation_for_intermediateLayer_4.activations_for_neurons, expected_values);
        //--------------------------------------------------------------------------------------------------------------------------------------------//






        //-----------------------------Backpropagation weights letzten beiden Schichten--------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        Backpropagation_last_two_layers backpropagation_outputlayer_and_layer_before_it = new Backpropagation_last_two_layers(activation_for_intermediateLayer_4, activation_for_intermediateLayer_3, intermediateLayer_4, intermediateLayer_3.number_of_neurons, expected_values);

        System.out.println("Delta Outputschicht   :    " + backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_outputlayer);
        System.out.println("Delta vorletzter layer:    " + backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer);
        //-----------------------------Backpropagation letzten beiden Schichten--------------------------------------------------------------------------------------------------------------------------------------------------------------------//



        System.out.println("\n");
        System.out.println(intermediateLayer_1.neuron_list + "\n");


        System.out.println("||||||||||||||||||||||||||||  deltas für Schicht 2 werden berechnt  |||||||||||||||||||||||||||| \n");
        Backpropafation_depth_layer backpropagation_layer_2 = new Backpropafation_depth_layer(backpropagation_outputlayer_and_layer_before_it.loss_for_neurons_vorletzter_layer, intermediateLayer_3.number_of_neurons, intermediateLayer_2.number_of_neurons, intermediateLayer_3, activation_for_intermediateLayer_2);



        System.out.println("||||||||||||||||||||||||||||  deltas für Schicht 1 werden berechnt  |||||||||||||||||||||||||||| \n");
        Backpropafation_depth_layer backpropagation_layer_1 = new Backpropafation_depth_layer(backpropagation_layer_2.delta_of_this_layer, intermediateLayer_2.number_of_neurons, intermediateLayer_1.number_of_neurons, intermediateLayer_2, activation_for_intermediateLayer_1);

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
        //


    }


}
