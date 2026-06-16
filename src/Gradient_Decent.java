import java.util.ArrayList;

public class Gradient_Decent
{

    public static ArrayList<ArrayList<Double>> gradient_for_bias(ArrayList<ArrayList<Double>> each_delta_for_each_neuron, ArrayList<Double> biases ,double leraningrate)
    {

        double new_biases_for_this_layer = 0;
        ArrayList<ArrayList<Double>> new_bias = new ArrayList<>();

        for(int delta = 0; delta < each_delta_for_each_neuron.size(); delta++)
        {
            System.out.print("new Bias for Neuron " + delta + ": -----> " + biases.get(delta) + " - (" + each_delta_for_each_neuron.get(delta).get(0) + " * " + leraningrate + ") ");
            new_biases_for_this_layer  = biases.get(delta) - (each_delta_for_each_neuron.get(delta).get(0) * leraningrate);
            System.out.println(" = " + new_biases_for_this_layer);
            ArrayList<Double> b = new ArrayList<>();
            b.add(new_biases_for_this_layer);
            new_bias.add(b);
        }
        System.out.println("\n");
        return new_bias;
    }

    public static ArrayList<ArrayList<Double>> gradient_for_weight(ArrayList<ArrayList<Double>> each_delta_for_each_neuron, ArrayList<ArrayList<ArrayList<Double>>> neurons_of_outputlayer, Activations_functions activation_layer_before, double leraningrate)
    {
        ArrayList<ArrayList<Double>> gradient_ = new ArrayList<>();
        ArrayList<ArrayList<Double>> new_weights = new ArrayList<>();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------\n");

        for(int neuron = 0; neuron < neurons_of_outputlayer.size(); neuron++)
        {
            for(int neuron_layer_before = 0; neuron_layer_before < activation_layer_before.activations_for_neurons.size(); neuron_layer_before++)
            {
                System.out.print("gradient Delta " + neuron + " to activation " + neuron_layer_before + " -----> " + each_delta_for_each_neuron.get(neuron).get(0) + " * " + activation_layer_before.activations_for_neurons.get(neuron_layer_before).get(0));
                double gradient = each_delta_for_each_neuron.get(neuron).get(0) * activation_layer_before.activations_for_neurons.get(neuron_layer_before).get(0);
                System.out.println(" = " + gradient);
                ArrayList<Double> g = new ArrayList<>();
                g.add(gradient);
                gradient_.add(g);

                System.out.println("weight to adjust: " + neurons_of_outputlayer.get(neuron).get(neuron_layer_before).get(0));
                double weight_to_adjust = neurons_of_outputlayer.get(neuron).get(neuron_layer_before).get(0);
                System.out.print("new_weight = " + weight_to_adjust + " -  (" + leraningrate + " * " + gradient + ")");
                double  new_weight = weight_to_adjust - (leraningrate * gradient);
                System.out.println(" = " + new_weight);
                System.out.println("\n");
                ArrayList<Double> new_weight_ = new ArrayList<>();
                new_weight_.add(new_weight);
                new_weights.add(new_weight_);
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
        return new_weights;
    }






    public static ArrayList<ArrayList<Double>> gradient_for_weight(ArrayList<ArrayList<Double>> each_delta_for_each_neuron, ArrayList<ArrayList<ArrayList<Double>>> neurons_of_outputlayer, ArrayList<Double> activation_layer_before, double leraningrate)
    {
        ArrayList<ArrayList<Double>> gradient_ = new ArrayList<>();
        ArrayList<ArrayList<Double>> new_weights = new ArrayList<>();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------\n");

        for(int neuron = 0; neuron < neurons_of_outputlayer.size(); neuron++)
        {
            for(int neuron_layer_before = 0; neuron_layer_before < activation_layer_before.size(); neuron_layer_before++)
            {
                System.out.print("gradient Delta " + neuron + " to activation " + neuron_layer_before + " -----> " + each_delta_for_each_neuron.get(neuron).get(0) + " * " + activation_layer_before.get(neuron_layer_before));
                double gradient = each_delta_for_each_neuron.get(neuron).get(0) * activation_layer_before.get(neuron_layer_before);
                System.out.println(" = " + gradient);
                ArrayList<Double> g = new ArrayList<>();
                g.add(gradient);
                gradient_.add(g);

                System.out.println("weight to adjust: " + neurons_of_outputlayer.get(neuron).get(neuron_layer_before).get(0));
                double weight_to_adjust = neurons_of_outputlayer.get(neuron).get(neuron_layer_before).get(0);
                System.out.print("new_weight = " + weight_to_adjust + " -  (" + leraningrate + " * " + gradient + ")");
                double  new_weight = weight_to_adjust - (leraningrate * gradient);
                System.out.println(" = " + new_weight);
                System.out.println("\n");
                ArrayList<Double> new_weight_ = new ArrayList<>();
                new_weight_.add(new_weight);
                new_weights.add(new_weight_);
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
        return new_weights;
    }

}


