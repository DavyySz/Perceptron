import java.util.ArrayList;

public class Backpropafation_depth_layer
{
    double sum_of_weights_and_delta;
    double delta_of_this_neuron;
    ArrayList<ArrayList<Double>> delta_of_this_layer = new ArrayList<>();
    ArrayList<ArrayList<Double>> delta_of_this_layer_for_bias = new ArrayList<>();


    public Backpropafation_depth_layer(ArrayList<ArrayList<Double>> delta_for_neurons_der_letzten_berechneten_schicht, int anzahl_neuronen_der_Schicht_der_zuletzt_berechneten_deltas, int anzahl_neuronen_der_Schicht_fuer_die_die_deltas_berechnet_werden, Intermediate_layer neuronen_der_schicht_fuer_die_deltas_berechnet_wurden, Activations_functions aktivierung_der_schicht_fuer_die_delta_berechnet_wird)
    {
        for(int neuron_aus_der_schicht_fuer_die_delta_berechnet_wird = 0; neuron_aus_der_schicht_fuer_die_delta_berechnet_wird < anzahl_neuronen_der_Schicht_fuer_die_die_deltas_berechnet_werden; neuron_aus_der_schicht_fuer_die_delta_berechnet_wird++)
        {
            sum_of_weights_and_delta = 0;
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for(int neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde = 0; neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde < anzahl_neuronen_der_Schicht_der_zuletzt_berechneten_deltas; neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde++)
            {
                double weight = neuronen_der_schicht_fuer_die_deltas_berechnet_wurden.neuron_list.get(neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde).get(neuron_aus_der_schicht_fuer_die_delta_berechnet_wird).get(0);
                System.out.println("weight vom Neuron " + neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde + " fuer das Delta schon berechnet wurde, hinzu Neuron " + neuron_aus_der_schicht_fuer_die_delta_berechnet_wird + " fuer das Delta jetzt berechnet wird = " + weight);

                double delta = delta_for_neurons_der_letzten_berechneten_schicht.get(neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde).get(0);
                System.out.println("Delta fuer das akzuelle Neuron " + neuron_aus_der_schicht_fuer_die_delta_schon_berechnet_wurde + " was mit den Gewichten multipliziert wird = " + delta);

                System.out.println("\n");

                System.out.print("sum_of_weights_and_delta für <<<<<<<Neuron " + neuron_aus_der_schicht_fuer_die_delta_berechnet_wird + ">>>>>>> -----> " + sum_of_weights_and_delta + " + " + weight + " * " + delta);
                this.sum_of_weights_and_delta = this.sum_of_weights_and_delta + weight * delta;
                System.out.println(" = " + sum_of_weights_and_delta);

                System.out.println("\n");

            }
            System.out.print("Delta für Neuron " + neuron_aus_der_schicht_fuer_die_delta_berechnet_wird + " ----> " + sum_of_weights_and_delta + " * " + "(" + aktivierung_der_schicht_fuer_die_delta_berechnet_wird.activations_for_neurons.get(neuron_aus_der_schicht_fuer_die_delta_berechnet_wird).get(0) + " * " + "(1 - " + aktivierung_der_schicht_fuer_die_delta_berechnet_wird.activations_for_neurons.get(neuron_aus_der_schicht_fuer_die_delta_berechnet_wird).get(0) + ")");
            delta_of_this_neuron = sum_of_weights_and_delta * (aktivierung_der_schicht_fuer_die_delta_berechnet_wird.activations_for_neurons.get(neuron_aus_der_schicht_fuer_die_delta_berechnet_wird).get(0) * (1 - aktivierung_der_schicht_fuer_die_delta_berechnet_wird.activations_for_neurons.get(neuron_aus_der_schicht_fuer_die_delta_berechnet_wird).get(0)));
            System.out.println(" = " + delta_of_this_neuron);

            ArrayList<Double> sum = new ArrayList<>();
            sum.add(sum_of_weights_and_delta);
            this.delta_of_this_layer_for_bias.add(sum);

            ArrayList<Double> d = new ArrayList<>();
            d.add(delta_of_this_neuron);
            this.delta_of_this_layer.add(d);

            sum_of_weights_and_delta = 0;
        }

        System.out.println("Delatas für weights: \n" + delta_of_this_layer);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------\n");

    }

}