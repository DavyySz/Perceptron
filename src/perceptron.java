import javax.swing.*;
import java.awt.*;

class perceptron
{
    private double[] inputs;
    private double[] weights;
    private double bias;
    private double learningrate;
    private double z;


    public static double number_weights(double[] weights)
    {
        return weights.length;
    }

    public static double[] weigthed_sum(double[][] inputs, double[] weights, double bias, double[] z)
    {
        System.out.println("gewichtete summen for i = 0, i = 1, ... i = n\n");
        for(int zeile = 0; zeile < inputs.length; zeile++)
            for(int spalte = 0; spalte < weights.length; spalte++)
            {
                {
                    System.out.print("z[zeile " + zeile + "]" + " = " + z[zeile]);
                    z[zeile] = z[zeile] +  (inputs[zeile][spalte] * weights[spalte]);
                    System.out.println(" + " + "(" + inputs[zeile][spalte] + " * " + weights[spalte] + ")" + " = " + z[zeile]);
                    System.out.println("\n");
                }
            }
        System.out.println("gewichtete summen mit bias\n");
        for(int zeile = 0; zeile < inputs.length; zeile++)
        {
            z[zeile] = z[zeile] + bias;
            System.out.println("\n");
        }
        return z;
    }

    public static double[] activation_function(double[] z)
    {
        double[] activated = new double[z.length];
        for(int zeile = 0; zeile < z.length; zeile++)
        {
            if(z[zeile] < 0)
            {
                activated[zeile] = -1;
            }
            else if(z[zeile] >= 0)
            {
                activated[zeile] = 1;
            }
        }
        return activated;
    }

    public static void weigthed_sum(int z)
    {
        System.out.println(z);
    }

    public static boolean is_finished(double[] expected_value, double[] output_activation_function)
    {
        for(int zeile = 0; zeile < expected_value.length; zeile++)
        {
            if(Math.abs(expected_value[zeile] - output_activation_function[zeile]) <= 0.00000001)
            {
                System.out.println("zeile" + zeile + "-->" +  "genauigkeit erreicht");
            }
            else
            {
                return false;
            }
        }
        System.out.println("alle Gewichte wurden auf die gewünschte Genauigkeit angepasst");
        return true;
    }


    public static double[] backpropagation_weights(double[][] inputs, double[] weights, double bias, double learningrate, double[] expected_value, double[] output_activation_function)
    {
        double[] fehlervektor = new double[expected_value.length];
        double[] fehler_mal_input = new double[weights.length];
        double[] fehler_mal_learningrate = new double[weights.length];

        for(int zeile = 0; zeile < expected_value.length; zeile++) // initialisiert den fehlervektor
        {
            fehlervektor[zeile] = expected_value[zeile] - output_activation_function[zeile];
        }

        for(int spalten_von_input = 0; spalten_von_input < inputs[0].length; spalten_von_input++) // rechne learningrate * x^T
        {
            for(int zeile_von_input = 0; zeile_von_input < inputs.length; zeile_von_input++)
            {
                fehler_mal_input[spalten_von_input] = fehler_mal_input[spalten_von_input] +  (inputs[zeile_von_input][spalten_von_input] * fehlervektor[zeile_von_input]);
            }
        }

        for(int zeilen_fehler_mal_learningrate = 0; zeilen_fehler_mal_learningrate < weights.length; zeilen_fehler_mal_learningrate++)
        {
            fehler_mal_learningrate[zeilen_fehler_mal_learningrate] = learningrate * fehler_mal_input[zeilen_fehler_mal_learningrate];
        }

        for(int zeilen_endvektor = 0; zeilen_endvektor < weights.length; zeilen_endvektor++)
        {
            fehler_mal_learningrate[zeilen_endvektor] = fehler_mal_learningrate[zeilen_endvektor] + weights[zeilen_endvektor];
        }

        return fehler_mal_learningrate; // equal to updated weights
    }

    public static double backpropagation_bias(double[][] inputs, double[] weights, double bias, double learningrate, double[] expected_value, double[] output_activation_function)
    {
        //bias = bias + learningrate * (expected_value[i] - output_activation_function);

        double sum_fehlervektor = 0;

        for(int zeile = 0; zeile < expected_value.length; zeile++) // initialisiert den fehlervektor
        {
            sum_fehlervektor = sum_fehlervektor + (expected_value[zeile] - output_activation_function[zeile]);
        }

        bias = bias + (learningrate * sum_fehlervektor);
        return bias;
    }
}
