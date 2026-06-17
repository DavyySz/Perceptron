import java.util.ArrayList;

public class Update_biases
{
    Update_biases(ArrayList<ArrayList<Double>> new_bias, ArrayList<Double> old_bias)
    {
        //System.out.println("\n" + "alten Gewichte durch neue ersetzen");
        for(int weight = 0; weight < new_bias.size(); weight++)
        {
            double w = new_bias.get(weight).get(0);
            //System.out.print(old_bias.get(weight) + " wird durch ");
            old_bias.set(weight, w);
            //System.out.println(w + " ersetzt \n");
        }

    }
}
