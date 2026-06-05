import java.util.ArrayList;

public class Neuron
{
    ArrayList<Double> activations_from_the_previous_layer = new ArrayList<>();
    ArrayList<Double> weights_of_this_neuron = new ArrayList<>();
    ArrayList<Double> activation_of_this_neuron = new ArrayList<>();

    Neuron(ArrayList weights_of_this_neuron, ArrayList activations_from_the_previous_layer)
    {
        this.activations_from_the_previous_layer = activations_from_the_previous_layer;
        this.weights_of_this_neuron = weights_of_this_neuron;
    }
}
