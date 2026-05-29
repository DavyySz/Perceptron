import javax.swing.*;
import java.awt.*;

public class PerceptronPlot {

    private static JFrame frame;
    private static PlotPanel panel;

    /** Einmalig aufrufen, bevor die Schleife losläuft */
    public static void init(double[][] inputs, double[] expected) {
        panel = new PlotPanel(inputs, expected);
        frame = new JFrame("Perzeptron – Entscheidungsgrenze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    /** In der Schleife jedes Mal aufrufen, wenn sich Gewichte/Bias geändert haben */
    public static void update(double[] weights, double bias) {
        if (panel != null) {
            panel.updateWeights(weights, bias);
            panel.repaint();
            // Kleine Pause, damit man die Bewegung sieht (optional)
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
        }
    }

    // -------- Inneres Panel (nur für die Zeichnung zuständig) --------
    private static class PlotPanel extends JPanel {
        private final double[][] inputs;
        private final double[] expected;
        private double[] weights;
        private double bias;
        private final double scale = 100;   // 1 Einheit = 100 Pixel

        PlotPanel(double[][] inputs, double[] expected) {
            this.inputs = inputs;
            this.expected = expected;
            this.weights = null;
            setBackground(Color.WHITE);
        }

        void updateWeights(double[] weights, double bias) {
            this.weights = weights.clone();
            this.bias = bias;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();

            // Koordinatenursprung in Fenstermitte, y-Achse nach oben
            g2.translate(w / 2.0, h / 2.0);
            g2.scale(1, -1);

            // Leichte Hilfsachsen
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(-w/2, 0, w/2, 0);
            g2.drawLine(0, -h/2, 0, h/2);

            // Datenpunkte
            for (int i = 0; i < inputs.length; i++) {
                int px = (int)(inputs[i][0] * scale);
                int py = (int)(inputs[i][1] * scale);
                if (expected[i] > 0) {
                    g2.setColor(Color.RED);
                    g2.fillOval(px-5, py-5, 10, 10);
                } else {
                    g2.setColor(Color.BLUE);
                    g2.drawLine(px-5, py-5, px+5, py+5);
                    g2.drawLine(px-5, py+5, px+5, py-5);
                }
            }

            // Entscheidungslinie (nur zeichnen, falls Gewichte gesetzt)
            if (weights != null && weights[1] != 0) {
                double w0 = weights[0];
                double w1 = weights[1];
                double b = bias;

                double x1Left  = -w / (2.0 * scale);
                double x1Right =  w / (2.0 * scale);
                double x2Left  = -(w0/w1)*x1Left  - b/w1;
                double x2Right = -(w0/w1)*x1Right - b/w1;

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine((int)(x1Left*scale), (int)(x2Left*scale),
                        (int)(x1Right*scale), (int)(x2Right*scale));
            }
        }
    }
}
