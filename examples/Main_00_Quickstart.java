import java.util.ArrayList;
import java.util.Arrays;

public class Main_00_Quickstart {
    public static void main(String[] args) {

        // ============================================================
        // QUICKSTART — Das einfachste mögliche Problem: AND
        //
        // AND gibt 1 zurück wenn BEIDE Inputs 1 sind, sonst 0.
        // Im Gegensatz zu XOR ist AND linear trennbar —
        // das Netz lernt es sehr schnell.
        //
        // [0,0] → 0
        // [0,1] → 0
        // [1,0] → 0
        // [1,1] → 1
        // ============================================================


        // ── SCHRITT 1: Trainingsdaten definieren ────────────────────
        // inputs:   Liste von Eingaben, jede Eingabe ist eine Liste von Zahlen
        // expected: Liste von erwarteten Ausgaben (eine pro Input)

        ArrayList<ArrayList<Double>> inputs   = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();

        inputs.add(new ArrayList<>(Arrays.asList(0.0, 0.0)));  expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 1.0)));  expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 0.0)));  expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 1.0)));  expected.add(new ArrayList<>(Arrays.asList(1.0)));


        // ── SCHRITT 2: Netz erstellen ────────────────────────────────
        // new NeuralNetwork(inputs, schichten, lernrate, abbruchkriterium)
        //
        //   2        → Anzahl Input-Neuronen (so viele Werte hat ein Trainingsbeispiel)
        //   {4, 1}   → Schichten: 4 Neuronen Hidden Layer, 1 Output-Neuron
        //   0.1      → Lernrate: wie groß die Schritte beim Lernen sind
        //              zu klein = lernt sehr langsam
        //              zu groß  = springt über das Minimum hinweg
        //   0.001    → Abbruchkriterium: Training stoppt wenn Avg Loss < 0.001

        NeuralNetwork net = new NeuralNetwork(2, new int[]{4, 1}, 0.1, 0.001);


        // ── SCHRITT 3: Training ──────────────────────────────────────
        // net.train(inputs, expected, maxEpochen, logIntervall)
        //
        //   inputs, expected → die Trainingsdaten
        //   100000           → maximale Epochen (Early Stopping greift meist früher)
        //   500              → alle 500 Epochen wird der aktuelle Loss geloggt

        net.train(inputs, expected, 100000, 500);


        // ── SCHRITT 4: Ergebnisse ausgeben ──────────────────────────
        // printPredictions zeigt für jeden Input:
        //   Input | Erwartet | Vorhergesagt | Gerundet | ✓/✗

        net.printPredictions(inputs, expected);


        // ── SCHRITT 5: Loss-Verlauf ausgeben ────────────────────────
        // Zeigt wie sich der Fehler über die Epochen entwickelt hat.
        // Ein gleichmäßig sinkender Loss = Netz lernt gut.
        // Ein stagnierender Loss = lokales Minimum, neu starten.

        net.printLossHistory();


        // ── SCHRITT 6: Einzelne Vorhersage ──────────────────────────
        // predict() gibt ArrayList<Double> zurück — alle Output-Neuronen.
        // result.get(0) = erstes Output-Neuron
        // result.get(1) = zweites Output-Neuron (falls vorhanden)
        // usw.

        ArrayList<Double> testInput = new ArrayList<>(Arrays.asList(1.0, 1.0));
        ArrayList<Double> result = net.predict(testInput);
        System.out.printf("%nEinzelne Vorhersage für [1,1]: %.4f (gerundet: %d)%n",
                result.get(0), result.get(0) > 0.5 ? 1 : 0);
    }
}
