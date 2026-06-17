import java.util.ArrayList;

public class Backpropagation_last_two_layers
{
    ArrayList<ArrayList<Double>> loss_for_neurons_outputlayer;
    ArrayList<ArrayList<Double>> loss_for_neurons_vorletzter_layer;
    boolean wiederholungsvariable = true;

    // binary cross entropy
    public Backpropagation_last_two_layers(Activations_functions activation_for_intermediateLayer_output, Activations_functions activation_for_intermediateLayer_vorletzte,  Intermediate_layer intermediate_layer, double anzahl_neuronen_vorletzte_schicht, ArrayList<Double> expected_values)
    {
        double activations_neuron_eine_schicht_weiter_innen = 0;
        //System.out.println("\n");
        //System.out.println("Anzahl Neuronen vorletzte Schicht = " + anzahl_neuronen_vorletzte_schicht + "\n");

        double part_of_sum = 0;
        this.loss_for_neurons_outputlayer = new ArrayList<>();
        this.loss_for_neurons_vorletzter_layer = new ArrayList<>();



        for(int neuron_vorletzte_schicht = 0; neuron_vorletzte_schicht < anzahl_neuronen_vorletzte_schicht; neuron_vorletzte_schicht++)
        {


            //System.out.println("\n");
            //System.out.println("Berechne Delta für Neuron Nr. " + neuron_vorletzte_schicht + " der vorletzten Schicht \n");
            ArrayList<Double> neuron = new ArrayList<>();



            for(int output_neuron = 0; output_neuron < activation_for_intermediateLayer_output.activations_for_neurons.size(); output_neuron++)
            {
                activations_neuron_eine_schicht_weiter_innen = activation_for_intermediateLayer_vorletzte.activations_for_neurons.get(neuron_vorletzte_schicht).get(0);





                //-------------------------------------------------------------------------------------------------------------------------------------------//
                //-------------------------------------------------------------------------------------------------------------------------------------------//
                //-------------------------------------------------------------------------------------------------------------------------------------------//
                // delta der ersten Schicht
                double delta = activation_for_intermediateLayer_output.activations_for_neurons.get(output_neuron).get(0) - expected_values.get(output_neuron); // binary cross entropy
                //-------------------------------------------------------------------------------------------------------------------------------------------//
                //-------------------------------------------------------------------------------------------------------------------------------------------//
                //-------------------------------------------------------------------------------------------------------------------------------------------//




                //System.out.println("\n");

                //System.out.println("delta für outputneuron " + output_neuron + " ---> " + activation_for_intermediateLayer_output.activations_for_neurons.get(output_neuron).get(0) + " - " + expected_values.get(output_neuron) + " = " + " <<<<< " + delta + " >>>>> ");

                ArrayList<Double> neuron_output_layer = new ArrayList<>();
                neuron_output_layer.add(delta);

                if(wiederholungsvariable == true)
                {
                    loss_for_neurons_outputlayer.add(neuron_output_layer);
                }



                //Problem
                double weight = intermediate_layer.neuron_list.get(output_neuron).get(neuron_vorletzte_schicht).get(0);



                //System.out.println("weight für outputneuron " + output_neuron + " ---> " + weight + "\n");


                //System.out.print("part_of_sum = " + part_of_sum + " + " + delta + " * " + weight );
                part_of_sum = part_of_sum + delta * weight;
                //System.out.println(" = " + part_of_sum);


            }

            wiederholungsvariable = false;
            //System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            //System.out.println(part_of_sum + " * " + activations_neuron_eine_schicht_weiter_innen + " * " + " (1 - " + activations_neuron_eine_schicht_weiter_innen + ")");




            //-------------------------------------------------------------------------------------------------------------------------------------------//
            //-------------------------------------------------------------------------------------------------------------------------------------------//
            //----------------Berechne Delta des Neurons der Vorletzten Schicht mit sigmoid und binary cross entropy------------------------//
            part_of_sum = part_of_sum * (activations_neuron_eine_schicht_weiter_innen * (1 - activations_neuron_eine_schicht_weiter_innen));
            //----------------Berechne Delta des Neurons der Vorletzten Schicht mit sigmoid und binary cross entropy------------------------//
            //-------------------------------------------------------------------------------------------------------------------------------------------//
            //-------------------------------------------------------------------------------------------------------------------------------------------//





            //System.out.println("Für Neuron " + neuron_vorletzte_schicht + " ist das delta = " + part_of_sum);
            neuron.add(part_of_sum);


            loss_for_neurons_vorletzter_layer.add(neuron);


            part_of_sum = 0;
            //System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }

    }

}
