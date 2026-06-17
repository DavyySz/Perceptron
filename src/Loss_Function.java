import java.util.ArrayList;


public class Loss_Function
{
    double sum;
    double sum_divided_by_the_number_of_endoneurones;
    ArrayList<Double> loss_of_o_single_neuron = new ArrayList<>();

    public void binary_cross_entropy(int sum_of_output_neurons, ArrayList<ArrayList<Double>> activations_for_neurons, ArrayList<Double> expected_values)
    {
        for(int current_neuron = 0; current_neuron < sum_of_output_neurons; current_neuron++)
        {
            //System.out.println("Berechne den Loss mit Binary Cross Entropy (Schritt 1 Summe aufaddieren): \n");
            //System.out.println("Berechnungsschritt: " + current_neuron + "\n");
            //System.out.print(sum + " + " +  " - 1 " + " * " + " ( " + "(" + expected_values.get(current_neuron) + ")" + " * " + Math.log(activations_for_neurons.get(current_neuron).get(0)) + " + " + "(" + " (1 " + " - " + expected_values.get(current_neuron) + ")  * " + "ln(" + " 1 " + " - " + (activations_for_neurons.get(current_neuron).get(0)) + " ) " + " ) " + "\n");


            sum =  sum + -1 * ((expected_values.get(current_neuron) * Math.log(activations_for_neurons.get(current_neuron).get(0))) + (1 - expected_values.get(current_neuron)) * Math.log(1 - activations_for_neurons.get(current_neuron).get(0)));


            //System.out.println(" = " + sum + "\n");

            //System.out.print("Loss für Neuron " + current_neuron + " der letzten Schicht: " + " ------> " +  " - 1 " + " * " + " ( " + "(" + expected_values.get(current_neuron) + ")" + " * " + Math.log(activations_for_neurons.get(current_neuron).get(0)) + " + " + "(" + " (1 " + " - " + expected_values.get(current_neuron) + ") * " + "ln(" + " 1 " + " - " + (activations_for_neurons.get(current_neuron).get(0)) + " ) " + " ) ");
            double loss_of_this_neuron = -1 * ((expected_values.get(current_neuron) * Math.log(activations_for_neurons.get(current_neuron).get(0))) + (1 - expected_values.get(current_neuron)) * Math.log(1 - activations_for_neurons.get(current_neuron).get(0)));
            //System.out.println(" = " + loss_of_this_neuron);
            loss_of_o_single_neuron.add(-1 * ((expected_values.get(current_neuron) * Math.log(activations_for_neurons.get(current_neuron).get(0))) + (1 - expected_values.get(current_neuron)) * Math.log(1 - activations_for_neurons.get(current_neuron).get(0))));


        }

        //System.out.println("Schritt 2 Summe durch anzahl der Endneuronen teilen\n");
        //System.out.print(sum + " / " + sum_of_output_neurons);
        sum_divided_by_the_number_of_endoneurones = sum / sum_of_output_neurons;
        //System.out.println(" = " + " ^^^^^^^^^^^^^^^^ " + sum_divided_by_the_number_of_endoneurones + " ^^^^^^^^^^^^^^^^ ");
    }
}
