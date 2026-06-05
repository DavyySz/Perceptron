/* In der class Input_layer wird die Inputschicht initialisiert, jedem Inputknoten wird ein activation
* Wert zugeordnet */

import java.util.ArrayList;

public class Input_layer
{
    ArrayList<Double> input = new ArrayList<>();
    Input_layer(ArrayList input)
    {
        this.input = input;
    }
}
