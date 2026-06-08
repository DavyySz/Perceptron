import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList<Double> inputs = new ArrayList<>(Arrays.asList(0.5, 0.3, 0.8)); // Aktivierungen der Eingabeneuronen
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



        ArrayList<Double> expected_values = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0));
        double learningrate = 0.1;

        //-----------------activation first tiefenlayer-------------------------------------------------------------//
        Input_layer inputLayer = new Input_layer(inputs); // erstellt die eingabeschicht mit Eingabeknoten


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





        //-------------------aktivations last layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Intermediate_layer intermediateLayer_3 = new Intermediate_layer(1, intermediateLayer_2); // erstellt die zweite Tiefenschicht
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        Calc_weigthed_sum calc_Weigthed_Sum_for_intermediateLayer_3 = new Calc_weigthed_sum(intermediateLayer_3, activation_for_intermediateLayer_2); // berechnet die Gewichtete summe für jedes Neuron der zweiten Tiefenschicht

        System.out.println(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer); // Gibt die Gewichtete Summen für die Neuronen der zweiten Tiefenschicht aus

        Activations_functions activation_for_intermediateLayer_3 = new Activations_functions();

        ArrayList<ArrayList<Double>> activations_for_neurons_tiefe_3 = new ArrayList<>();

        activations_for_neurons_tiefe_3.add(calc_Weigthed_Sum_for_intermediateLayer_3.weights_of_this_layer);

        activation_for_intermediateLayer_3.sigmoid(activations_for_neurons_tiefe_3);

        //-------------------aktivations second layer-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

        //-------------------loss berechnen-----------------------------------------------------------------------------------------------------------//
        Loss_Function loss = new Loss_Function();
        loss.binary_cross_entropy(intermediateLayer_3.number_of_neurons, activation_for_intermediateLayer_3.activations_for_neurons, expected_values);
        //--------------------------------------------------------------------------------------------------------------------------------------------//



        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/
        //


    }


}
