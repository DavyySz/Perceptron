# Neural Network Framework — Java (from scratch)

Selbst implementiertes neuronales Netz in Java, ohne externe Bibliotheken.  
Gebaut als Lernprojekt um die Mathematik hinter neuronalen Netzen von Grund auf zu verstehen.

---

## Über das Projekt

Ziel war es, ein vollständiges neuronales Netz **ohne Frameworks wie PyTorch oder TensorFlow** zu implementieren — nur Java und Mathematik. Jede Klasse entspricht einem konkreten Schritt im Lernprozess: Forward Pass, Aktivierung, Loss, Backpropagation, Gradientenabstieg.

Das Framework unterstützt:
- Beliebig viele Schichten und Neuronen
- Beliebig viele Input- und Output-Neuronen
- Sigmoid-Aktivierung
- Binary Cross Entropy Loss
- Stochastic Gradient Descent
- Early Stopping

---

## Transparenz: Was selbst implementiert wurde

| Klasse                       | Inhalt                                 | Eigenanteil                                   |
|------------------------------|----------------------------------------|-----------------------------------------------|
| `Input_layer.java`           | Speichert den Input-Vektor             | ✅ selbst                                      |
| `Intermediate_layer.java`    | Neuronen, Gewichte, Bias, Xavier-Init  | ✅ selbst                                      |
| `Calc_weigthed_sum.java`     | Forward Pass (gewichtete Summe)        | ✅ selbst                                      |
| `Activations_functions.java` | Sigmoid                                | ✅ selbst                                      |
| `Loss_Function.java`         | Binary Cross Entropy                   | ✅ selbst                                      |
| `Gradient_Decent.java`       | Berechnet neue Gewichte und Biases     | ✅ selbst                                      |
| `Update_weights.java`        | Schreibt neue Gewichte zurück          | 🤝   (bug wurde durch ki gefunden und gelöst) |
| `Update_biases.java`         | Schreibt neue Biases zurück            | ✅ selbst                                      |
| `NeuralNetwork.java`         | Generischer Wrapper, Backprop-Schleife | 🤝 mit KI-Unterstützung                       |
| `Main.java`                  | Erstellung des Netzes                  | 🤝 mit KI-Unterstützung
**Wie KI eingesetzt wurde:**  
KI wurde als Lernwerkzeug benutzt — zum Erklären von Konzepten, Generieren von Formelblättern, Überprüfen von Rechnungen und Logs, sowie Finden von Syntaxfehlern. Den Code und das mathematische Verständnis habe ich mir selbst erarbeitet. Zusätzlich wurden viele Erklärvideos auf YouTube geschaut und eigene Formelblätter zur Backpropagation erstellt.

---

## Mathematischer Hintergrund

### Forward Pass
```
z(l) = W(l) * a(l-1) + b(l)
a(l) = sigmoid(z(l))
```

### Backpropagation (Output Layer)
```
δ(L) = a(L) - y
```

### Backpropagation (Hidden Layer)
```
δ(l) = (W(l+1))^T * δ(l+1) ⊙ σ'(z(l))
```

### Gewichts-Update
```
W(l) ← W(l) - η * δ(l) * a(l-1)^T
```

---

## Projektstruktur

```
neural-network-java/
├── README.md
├── FRAMEWORK_ANLEITUNG.md
├── PROJEKTVORSCHLAG.md
├── docs/
│   ├── Backpropagation.pdf
│   ├── Backpropagation-1.pdf
│   ├── Gewichtsmatrix.pdf
│   ├── ij.pdf
│   └── Matrix.pdf
├── src/
│   ├── NeuralNetwork.java
│   ├── Input_layer.java
│   ├── Intermediate_layer.java
│   ├── Calc_weigthed_sum.java
│   ├── Activations_functions.java
│   ├── Loss_Function.java
│   ├── Gradient_Decent.java
│   ├── Update_weights.java
│   └── Update_biases.java
└── examples/
    ├── Main_01_XOR.java
    ├── Main_02_Temperatur.java
    ├── Main_03_MiniMNIST.java
    └── Main_04_3DCluster.java
```

---

## Schnellstart

```java
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
```

---

## Gelöste Probleme

### 1. XOR
Das klassische nicht-linear trennbare Problem — braucht mindestens eine Hidden Layer.  



### 2. Temperaturklassifikation
Erster kontinuierlicher Input (normalisierte Temperatur).  
Output: [kalt, warm, heiß] — das Netz lernt Entscheidungsgrenzen auf einer Zahlengeraden.  
Testet Generalisierung auf unbekannte Temperaturen.

### 3. Mini-MNIST (5×5 Pixel)
25 Inputs, 10 Outputs, 110 Trainingsbeispiele (11 Varianten pro Ziffer).  
100% Trainingsgenauigkeit, 80% auf komplett unbekannten Testbildern.  
Echte Bildklassifikation ohne Bibliotheken.

### 4. 3D Punktwolken
3 kontinuierliche Inputs (x, y, z), 4 Cluster in Würfelecken.  
Testet geometrische Klassifikation im kontinuierlichen Raum.  
Der Mittelpunkt (0.5, 0.5, 0.5) hat absichtlich keine richtige Antwort — niedrige Konfidenz dort ist das korrekte Verhalten.

---


## Nächste Schritte (Projektvorschlag)

- **Tic-Tac-Toe KI** trainiert mit diesem Framework (Supervised Learning via Minimax-Trainingsdaten)
- **Weboberfläche** wo man gegen die KI spielen kann
- Optional: Reinforcement Learning — das Netz lernt durch Spielen gegen sich selbst

---

## Anforderungen

- Java 17+
- Keine externen Bibliotheken

---

*Projekt von Daniel Stein*
