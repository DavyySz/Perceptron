import java.util.ArrayList;

public class Activations_functions
{
    ArrayList<ArrayList<Double>> activations_for_neurons = new ArrayList<>();
    public void sigmoid(ArrayList<ArrayList<Double>> weigthed_sum)
    {
        for(int weigthed_sum_for_neuron = 0; weigthed_sum_for_neuron < weigthed_sum.get(0).size(); weigthed_sum_for_neuron++)
        {
            //System.out.println("hier activation fürs " +  weigthed_sum_for_neuron + " neoron: \n");
            //System.out.println("calc activation (SIGMOID) for neuron: " + weigthed_sum_for_neuron);

            //System.out.println("1.0 " + " / " + " ( " + " 1.0 " + " + " + " Math.exp( " + " ( " +  " - " + weigthed_sum.get(0).get(weigthed_sum_for_neuron) + " )) ");

            ArrayList<Double> calculation = new ArrayList<>();

            double sum = weigthed_sum.get(0).get(weigthed_sum_for_neuron);

            calculation.add(1.0 / (1.0 + Math.exp(- sum)));

            ArrayList<ArrayList<Double>> activate = new ArrayList<>();

            activate.add(calculation);

            //System.out.println(" = " + activate + "\n");

            activations_for_neurons.add(calculation);
        }
    }

}
