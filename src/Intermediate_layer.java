import java.util.ArrayList;

public class Intermediate_layer {
    int number_of_neurons;
    Intermediate_layer layer_before;
    Input_layer inputLayer;
    Intermediate_layer intermediateLayer;
    ArrayList<Double> inner_list = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Double>>> neuron_list = new ArrayList<>();
    ArrayList<Neuron> neuron = new ArrayList<>();
    ArrayList<Neuron> neurons = new ArrayList<>();


    Intermediate_layer(int number_of_neurons, Input_layer inputLayer)
    {
        this.number_of_neurons = number_of_neurons;
        this.inputLayer = inputLayer;

        for(int anzahl_neuronen = 0; anzahl_neuronen < number_of_neurons; anzahl_neuronen++)
        {
            ArrayList<ArrayList<Double>> initialize_weights = new ArrayList<>();
            for(int weight = 0; weight < inputLayer.input.size(); weight++)
            {
                inner_list = new ArrayList<>();
                inner_list.add(Math.random());

                initialize_weights.add(inner_list);
            }

            neuron_list.add(initialize_weights);

        }
    }


    Intermediate_layer(int number_of_neurons, Intermediate_layer intermediateLayer)
    {
        this.number_of_neurons = number_of_neurons;
        this.intermediateLayer = intermediateLayer;

        for(int anzahl_neuronen = 0; anzahl_neuronen < number_of_neurons; anzahl_neuronen++)
        {
            ArrayList<ArrayList<Double>> initialize_weights = new ArrayList<>();
            for(int weight = 0; weight < intermediateLayer.number_of_neurons; weight++)
            {
                inner_list = new ArrayList<>();
                inner_list.add(Math.random());

                initialize_weights.add(inner_list);
            }

            neuron_list.add(initialize_weights);

        }
    }
}
