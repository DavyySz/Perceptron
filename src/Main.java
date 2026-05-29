public class Main {
    public static void main(String[] args) {
        double[][] inputs = {
    // +1 Punkte (oberhalb der Linie)
    {-5.0, 3.1},
    {-4.8, 3.0},
    {-4.6, 2.9},
    {-4.4, 2.8},
    {-4.2, 2.7},
    {-4.0, 2.6},
    {-3.8, 2.5},
    {-3.6, 2.4},
    {-3.4, 2.3},
    {-3.2, 2.2},
    {-3.0, 2.1},
    {-2.8, 2.0},
    {-2.6, 1.9},
    {-2.4, 1.8},
    {-2.2, 1.7},
    {-2.0, 1.6},
    {-1.8, 1.5},
    {-1.6, 1.4},
    {-1.4, 1.3},
    {-1.2, 1.2},
    {-1.0, 1.1},
    {-0.8, 1.0},
    {-0.6, 0.9},
    {-0.4, 0.8},
    {-0.2, 0.7},
    { 0.0, 0.6},
    { 0.2, 0.5},
    { 0.4, 0.4},
    { 0.6, 0.3},
    { 0.8, 0.2},
    { 1.0, 0.1},
    { 1.2, 0.0},
    { 1.4, -0.1},
    { 1.6, -0.2},
    { 1.8, -0.3},
    { 2.0, -0.4},
    { 2.2, -0.5},
    { 2.4, -0.6},
    { 2.6, -0.7},
    { 2.8, -0.8},
    { 3.0, -0.9},
    { 3.2, -1.0},
    { 3.4, -1.1},
    { 3.6, -1.2},
    { 3.8, -1.3},
    { 4.0, -1.4},
    { 4.2, -1.5},
    { 4.4, -1.6},
    { 4.6, -1.7},
    { 4.8, -1.8},

    // -1 Punkte (unterhalb der Linie)
    {-5.0, 2.9},
    {-4.8, 2.8},
    {-4.6, 2.7},
    {-4.4, 2.6},
    {-4.2, 2.5},
    {-4.0, 2.4},
    {-3.8, 2.3},
    {-3.6, 2.2},
    {-3.4, 2.1},
    {-3.2, 2.0},
    {-3.0, 1.9},
    {-2.8, 1.8},
    {-2.6, 1.7},
    {-2.4, 1.6},
    {-2.2, 1.5},
    {-2.0, 1.4},
    {-1.8, 1.3},
    {-1.6, 1.2},
    {-1.4, 1.1},
    {-1.2, 1.0},
    {-1.0, 0.9},
    {-0.8, 0.8},
    {-0.6, 0.7},
    {-0.4, 0.6},
    {-0.2, 0.5},
    { 0.0, 0.4},
    { 0.2, 0.3},
    { 0.4, 0.2},
    { 0.6, 0.1},
    { 0.8, 0.0},
    { 1.0, -0.1},
    { 1.2, -0.2},
    { 1.4, -0.3},
    { 1.6, -0.4},
    { 1.8, -0.5},
    { 2.0, -0.6},
    { 2.2, -0.7},
    { 2.4, -0.8},
    { 2.6, -0.9},
    { 2.8, -1.0},
    { 3.0, -1.1},
    { 3.2, -1.2},
    { 3.4, -1.3},
    { 3.6, -1.4},
    { 3.8, -1.5},
    { 4.0, -1.6},
    { 4.2, -1.7},
    { 4.4, -1.8},
    { 4.6, -1.9},
    { 4.8, -2.0}
};

        double[] weights = {-0.2, 0.6};
        double bias = 5.0; // pro Neuron ein bias
        double learningrate = 0.001;
        double[] expected_value = {
    1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
    1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
    1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
    1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
    1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
    -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0,
    -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0,
    -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0,
    -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0,
    -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0
};
        boolean finished = false;


        // ★ Einmalig das Plot-Fenster starten
        PerceptronPlot.init(inputs, expected_value);

        long epoch = 1;


        for(int i = 0; i < inputs.length; i++)
        {
            System.out.println("input = " + inputs[i]);
        }

        System.out.println("\n");

        //for(long epoch = 0; epoch < 1000000000; epoch++)
        while (1 == 1)
        {
                double[] z = new double[inputs.length];
                System.out.println("Epoche " + epoch);
                double[] weigthed_sum = perceptron.weigthed_sum(inputs, weights, bias, z);
                for(int zeile = 0; zeile < weigthed_sum.length; zeile++)
                {
                    System.out.println("weigthed_sum zeile --> " + zeile + " = " + weigthed_sum[zeile]);
                }
                System.out.println("\n");

                double[] output_activation_function = perceptron.activation_function(weigthed_sum);

                for(int zeile_activation_function = 0; zeile_activation_function < output_activation_function.length; zeile_activation_function++)
                {
                    System.out.println("Aktivierungsfunktion Zeile " + zeile_activation_function + " = " + output_activation_function[zeile_activation_function]);
                }
                System.out.println("\n");

                if(perceptron.is_finished(expected_value, output_activation_function) == true)
                {
                    break;
                }

                weights = perceptron.backpropagation_weights(inputs, weights, bias, learningrate, expected_value, output_activation_function);
                for(int i = 0; i < weights.length; i++)
                {
                    System.out.println("iteration " + i + " weight = " + weights[i]);
                }
               bias =perceptron.backpropagation_bias(inputs, weights, bias, learningrate, expected_value, output_activation_function);
               System.out.println("bias = " + bias);
               System.out.println("\n");

                PerceptronPlot.update(weights, bias);

               epoch ++;


        }

    }
}
