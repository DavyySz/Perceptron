import java.util.ArrayList;

public class Update_weights
{
    Update_weights(ArrayList<ArrayList<Double>> new_weights, ArrayList<ArrayList<ArrayList<Double>>> old_weights)
    {
        //System.out.println("\n" + "alten Gewichte durch neue ersetzen");

        for(int neuron = 0; neuron < new_weights.size(); neuron++)
        {
            for(int weight = 0; weight < new_weights.get(neuron).size(); weight++)
            {
                double w = new_weights.get(neuron).get(weight);
                //System.out.print(old_weights.get(neuron).get(weight).get(0) + " wird durch ");
                old_weights.get(neuron).get(weight).set(0, w);
                //System.out.println(w + " ersetzt \n");
            }
        }
    }
}