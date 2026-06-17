import java.util.ArrayList;

public class Calc_weigthed_sum
{
    ArrayList<Double> weights_of_this_layer = new ArrayList<>();
    double weigthed_sum = 0;
    Input_layer input_layer;
    ArrayList<Double> weights_of_this_neuron = new ArrayList<>();
    Activations_functions activations_from_the_previous_layer;

    public Calc_weigthed_sum(Intermediate_layer intermediate_layer)
    {

        /*for(int number_of_neuron = 0; number_of_neuron < intermediate_layer.inputLayer.input.size(); number_of_neuron++)
        {
            System.out.println("activation: " + " [" + number_of_neuron + "] --> " + intermediate_layer.inputLayer.input.get(number_of_neuron));
        }

        System.out.println("\n");

        for(int neuron_weights = 0; neuron_weights < intermediate_layer.neuron_list.size(); neuron_weights++)
        {
            System.out.println(intermediate_layer.neuron_list.get(neuron_weights));
        }



        System.out.println("--------------------------calc-------------------------------\n");

        */


        for(int neuron = 0; neuron < intermediate_layer.neuron_list.size(); neuron++)
        {

            //System.out.println(weigthed_sum + "\n");
            double bias_of_this_neuron = intermediate_layer.bias_list.get(neuron);
            //System.out.println("bias of neuron: " + neuron + " = " + bias_of_this_neuron + "\n");

            for(int weight = 0; weight < intermediate_layer.neuron_list.get(0).size(); weight++)
            {
                //System.out.println("Neuron: " + neuron + " Gewicht: " + weight + " " + intermediate_layer.neuron_list.get(neuron).get(weight));
                //System.out.println("activation: " + " [" + weight + "] --> " + intermediate_layer.inputLayer.input.get(weight));
                //System.out.println("\n");

                //---------------------------------------------------Berechnung der Gewichteten Summe------------------------------------------------------//



                double weight_neuron = intermediate_layer.neuron_list.get(neuron).get(weight).get(0);
                double activation = intermediate_layer.inputLayer.input.get(weight);



                double part_weigthed_sum = weight_neuron * activation;

                //System.out.println("Berechnung:   " + weight_neuron + " * " + activation + " = " + part_weigthed_sum + "\n");

                //System.out.println("part of weigthed sum: " + part_weigthed_sum + "\n");



                //System.out.println("Gewichtete Summe wird für das jeweilige Neuron aufaddiert: --> " + weigthed_sum + " + " + part_weigthed_sum + " = " + (weigthed_sum + part_weigthed_sum) + "\n");


                weigthed_sum = weigthed_sum + part_weigthed_sum;

                //System.out.println("--------------------------------------------------\n");
            }


            //System.out.println("weigthed_sum + bias: " + weigthed_sum + " + " + bias_of_this_neuron);

            //System.out.print("Gewichtete Summe für Neuron " + neuron + " = " + weigthed_sum + " + " + bias_of_this_neuron + " = ");

            weigthed_sum = weigthed_sum + bias_of_this_neuron;

            //System.out.println(weigthed_sum);


            weights_of_this_layer.add(weigthed_sum); // Hier stehen die berechneten Gewichte drin

            /*
            System.out.println("next neuron\n");

            for(int i = 0; i < 5; i++)
            {
                System.out.println("|||||||||||||\n");
            }
            */

            weigthed_sum = 0;
        }
    }


//--------------------------------------------------------------------------------------------------------------------//




    public Calc_weigthed_sum(Intermediate_layer intermediate_layer, Activations_functions activations_from_the_previous_layer )
    {
        /*
        for(int number_of_neuron = 0; number_of_neuron < intermediate_layer.intermediateLayer.number_of_neurons; number_of_neuron++)
        {
            System.out.println("activation (activation kommt ab hier nichtmehr aus den inputs): " + " [" + number_of_neuron + "] --> " + activations_from_the_previous_layer.activations_for_neurons.get(number_of_neuron));
        }

        System.out.println("\n");

        System.out.println("Hier stehen die initialisierten Gewichte: \n");
        for(int neuron_weights = 0; neuron_weights < intermediate_layer.neuron_list.size(); neuron_weights++)
        {
            System.out.println(intermediate_layer.neuron_list.get(neuron_weights));
        }




        System.out.println("--------------------------calc-------------------------------\n");

        */
        for(int neuron = 0; neuron < intermediate_layer.neuron_list.size(); neuron++)
        {
            //System.out.println(weigthed_sum + "\n");
            double bias_of_this_neuron = intermediate_layer.bias_list.get(neuron);
            //System.out.println("bias of neuron: " + neuron + " = " + bias_of_this_neuron + "\n");

            for(int weight = 0; weight < intermediate_layer.neuron_list.get(0).size(); weight++)
            {
                //System.out.println("Neuron: " + neuron + " Gewicht: " + weight + " " + intermediate_layer.neuron_list.get(neuron).get(weight));
                //System.out.println("activation: " + " [" + weight + "] --> " + activations_from_the_previous_layer.activations_for_neurons.get(weight));
                //System.out.println("\n");

                //---------------------------------------------------Berechnung der Gewichteten Summe------------------------------------------------------//



                double weight_neuron = intermediate_layer.neuron_list.get(neuron).get(weight).get(0);
                double activation = activations_from_the_previous_layer.activations_for_neurons.get(weight).get(0);



                double part_weigthed_sum = weight_neuron * activation;

                //System.out.println("Berechnung:   " + weight_neuron + " * " + activation + " = " + part_weigthed_sum + "\n");

                //System.out.println("part of weigthed sum: " + part_weigthed_sum + "\n");



                //System.out.println("Gewichtete Summe wird für das jeweilige Neuron aufaddiert: --> " + weigthed_sum + " + " + part_weigthed_sum + " = " + (weigthed_sum + part_weigthed_sum) + "\n");

                weigthed_sum = weigthed_sum + part_weigthed_sum;

                //System.out.println("--------------------------------------------------\n");
            }

            //System.out.println("weigthed_sum + bias: " + weigthed_sum + " + " + bias_of_this_neuron);

            //System.out.print("Gewichtete Summe für Neuron " + neuron + " = " + weigthed_sum + " + " + bias_of_this_neuron + " = ");

            weigthed_sum = weigthed_sum + bias_of_this_neuron;

            //System.out.println(weigthed_sum);


            weights_of_this_layer.add(weigthed_sum); // Hier stehen die berechneten Gewichte drin

            /*System.out.println("next neuron\n");
            for(int i = 0; i < 5; i++)
            {
                System.out.println("|||||||||||||\n");
            }
            */
            weigthed_sum = 0;
        }
    }








    public Calc_weigthed_sum(Intermediate_layer intermediate_layer, ArrayList<ArrayList<Double>> weights, ArrayList<ArrayList<Double>> new_biases_for_layer, ArrayList<Double> input )
    {

        for(int number_of_neuron = 0; number_of_neuron < input.size(); number_of_neuron++)
        {
            System.out.println("activation: " + " [" + number_of_neuron + "] --> " + input.get(number_of_neuron));
        }

        System.out.println("\n");

        System.out.println("Hier stehen die initialisierten Gewichte: \n");
        for(int neuron_weights = 0; neuron_weights < weights.size(); neuron_weights++)
        {
            System.out.println(weights.get(neuron_weights));
        }




        System.out.println("--------------------------calc-------------------------------\n");


        for(int neuron = 0; neuron < intermediate_layer.neuron_list.size(); neuron++)
        {
            System.out.println(weigthed_sum + "\n");
            double bias_of_this_neuron = new_biases_for_layer.get(neuron).get(0);
            System.out.println("bias of neuron: " + neuron + " = " + bias_of_this_neuron + "\n");

            for(int weight = 0; weight < intermediate_layer.neuron_list.get(0).size(); weight++)
            {
                System.out.println("Neuron: " + neuron + " Gewicht: " + weight + " " + weights.get(neuron).get(weight));
                System.out.println("activation: " + " [" + weight + "] --> " + input.get(weight));
                System.out.println("\n");

                //---------------------------------------------------Berechnung der Gewichteten Summe------------------------------------------------------//



                double weight_neuron = weights.get(neuron).get(weight);
                double activation = input.get(weight);



                double part_weigthed_sum = weight_neuron * activation;

                System.out.println("Berechnung:   " + weight_neuron + " * " + activation + " = " + part_weigthed_sum + "\n");

                System.out.println("part of weigthed sum: " + part_weigthed_sum + "\n");



                System.out.println("Gewichtete Summe wird für das jeweilige Neuron aufaddiert: --> " + weigthed_sum + " + " + part_weigthed_sum + " = " + (weigthed_sum + part_weigthed_sum) + "\n");

                weigthed_sum = weigthed_sum + part_weigthed_sum;

                System.out.println("--------------------------------------------------\n");
            }

            System.out.println("weigthed_sum + bias: " + weigthed_sum + " + " + bias_of_this_neuron);

            System.out.print("Gewichtete Summe für Neuron " + neuron + " = " + weigthed_sum + " + " + bias_of_this_neuron + " = ");

            weigthed_sum = weigthed_sum + bias_of_this_neuron;

            System.out.println(weigthed_sum);


            weights_of_this_layer.add(weigthed_sum); // Hier stehen die berechneten Gewichte drin

            System.out.println("next neuron\n");
            for(int i = 0; i < 5; i++)
            {
                System.out.println("|||||||||||||\n");
            }

            weigthed_sum = 0;
        }
    }








    public Calc_weigthed_sum(Intermediate_layer intermediate_layer, ArrayList<ArrayList<Double>> weights, ArrayList<ArrayList<Double>> new_biases_for_layer, ArrayList<ArrayList<Double>> activation_previous_layer, int nonsens)
    {

        for(int number_of_neuron = 0; number_of_neuron < activation_previous_layer.size(); number_of_neuron++)
        {
            System.out.println("activation: " + " [" + number_of_neuron + "] --> " + activation_previous_layer.get(number_of_neuron));
        }

        System.out.println("\n");

        System.out.println("Hier stehen die initialisierten Gewichte: \n");
        for(int neuron_weights = 0; neuron_weights < weights.size(); neuron_weights++)
        {
            System.out.println(weights.get(neuron_weights));
        }




        System.out.println("--------------------------calc-------------------------------\n");


        for(int neuron = 0; neuron < intermediate_layer.neuron_list.size(); neuron++)
        {
            System.out.println(weigthed_sum + "\n");
            double bias_of_this_neuron = new_biases_for_layer.get(neuron).get(0);
            System.out.println("bias of neuron: " + neuron + " = " + bias_of_this_neuron + "\n");

            for(int weight = 0; weight < intermediate_layer.neuron_list.get(0).size(); weight++)
            {
                System.out.println("Neuron: " + neuron + " Gewicht: " + weight + " " + weights.get(neuron).get(weight));
                System.out.println("activation: " + " [" + weight + "] --> " + activation_previous_layer.get(weight));
                System.out.println("\n");

                //---------------------------------------------------Berechnung der Gewichteten Summe------------------------------------------------------//



                double weight_neuron = weights.get(neuron).get(weight);
                double activation = activation_previous_layer.get(weight).get(0);



                double part_weigthed_sum = weight_neuron * activation;

                System.out.println("Berechnung:   " + weight_neuron + " * " + activation + " = " + part_weigthed_sum + "\n");

                System.out.println("part of weigthed sum: " + part_weigthed_sum + "\n");



                System.out.println("Gewichtete Summe wird für das jeweilige Neuron aufaddiert: --> " + weigthed_sum + " + " + part_weigthed_sum + " = " + (weigthed_sum + part_weigthed_sum) + "\n");

                weigthed_sum = weigthed_sum + part_weigthed_sum;

                System.out.println("--------------------------------------------------\n");
            }

            System.out.println("weigthed_sum + bias: " + weigthed_sum + " + " + bias_of_this_neuron);

            System.out.print("Gewichtete Summe für Neuron " + neuron + " = " + weigthed_sum + " + " + bias_of_this_neuron + " = ");

            weigthed_sum = weigthed_sum + bias_of_this_neuron;

            System.out.println(weigthed_sum);


            weights_of_this_layer.add(weigthed_sum); // Hier stehen die berechneten Gewichte drin

            System.out.println("next neuron\n");
            for(int i = 0; i < 5; i++)
            {
                System.out.println("|||||||||||||\n");
            }

            weigthed_sum = 0;
        }
    }

}