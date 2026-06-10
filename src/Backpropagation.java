import java.util.ArrayList;

public class Backpropagation
{
    Intermediate_layer intermediateLayer;


    public static ArrayList<ArrayList<Double>> output_layer_binary_cross_entropy(Activations_functions activation_for_intermediateLayer_output, Activations_functions activation_for_intermediateLayer_vorletzte,  Intermediate_layer intermediate_layer, double anzahl_neuronen_vorletzte_schicht)
    {
        double activations_neuron_eine_schicht_weiter_innen = 0;
        System.out.println("\n");
        System.out.println("Anzahl Neuronen vorletzte Schicht = " + anzahl_neuronen_vorletzte_schicht + "\n");

        double part_of_sum = 0;
        ArrayList<ArrayList<Double>> loss_for_neurons_vorletzter_layer = new ArrayList<>();



        for(int neuron_vorletzte_schicht = 0; neuron_vorletzte_schicht < anzahl_neuronen_vorletzte_schicht; neuron_vorletzte_schicht++)
        {


            System.out.println("\n");
            System.out.println("Berechne Delta für Neuron Nr. " + neuron_vorletzte_schicht + " der vorletzten Schicht \n");
            ArrayList<Double> neuron = new ArrayList<>();



            for(int output_neuron = 0; output_neuron < activation_for_intermediateLayer_output.activations_for_neurons.size(); output_neuron++)
            {
                activations_neuron_eine_schicht_weiter_innen = activation_for_intermediateLayer_vorletzte.activations_for_neurons.get(neuron_vorletzte_schicht).get(0);


                double delta = activation_for_intermediateLayer_output.activations_for_neurons.get(output_neuron).get(0) - 1;

                System.out.println("\n");

                System.out.println("delta für outputneuron " + output_neuron + " ---> " + activation_for_intermediateLayer_output.activations_for_neurons.get(output_neuron).get(0) + " - 1 " + " = " + " <<<<< " + delta + " >>>>> ");

                double weight = intermediate_layer.neuron_list.get(output_neuron).get(neuron_vorletzte_schicht).get(0);
                System.out.println("weight für outputneuron " + output_neuron + " ---> " + weight + "\n");


                System.out.print("part_of_sum = " + part_of_sum + " + " + delta + " * " + weight );
                part_of_sum = part_of_sum + delta * weight;
                System.out.println(" = " + part_of_sum);


            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            System.out.println(part_of_sum + " * " + activations_neuron_eine_schicht_weiter_innen + " * " + " (1 - " + activations_neuron_eine_schicht_weiter_innen + ")");

            part_of_sum = part_of_sum * (activations_neuron_eine_schicht_weiter_innen * (1 - activations_neuron_eine_schicht_weiter_innen));

            //System.out.println(part_of_sum + " * " + activations_neuron_eine_schicht_weiter_innen + " * " + " (1 - " + activations_neuron_eine_schicht_weiter_innen + ")");

            System.out.println("Für Neuron " + neuron_vorletzte_schicht + " ist das delta = " + part_of_sum);
            neuron.add(part_of_sum);
            loss_for_neurons_vorletzter_layer.add(neuron);
            part_of_sum = 0;
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }

        return loss_for_neurons_vorletzter_layer;

    }

}
