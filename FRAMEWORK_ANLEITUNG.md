# Framework-Anleitung — Neural Network Java

> **KI-Kennzeichnung:** Diese Anleitung wurde von Claude generiert und von mir
> angepasst. Am 30.09.2026 wurden alle Aussagen entfernt, die sich nicht aus dem
> Code oder den Beispielen belegen lassen. Siehe README.md → "Transparenz".

Diese Anleitung erklärt Schritt für Schritt wie das Framework benutzt wird,
welche Parameter es gibt, und was bei häufigen Problemen zu tun ist.

---

## Inhaltsverzeichnis

1. [Grundprinzip](#1-grundprinzip)
2. [Trainingsdaten vorbereiten](#2-trainingsdaten-vorbereiten)
3. [Netz erstellen](#3-netz-erstellen)
4. [Training](#4-training)
5. [Vorhersagen](#5-vorhersagen)
6. [Ausgabe](#6-ausgabe)
7. [Häufige Probleme](#7-häufige-probleme)
8. [Beispiele nach Komplexität](#8-beispiele-nach-komplexität)

---

## 1. Grundprinzip

Das Framework implementiert ein vollverbundenes neuronales Netz (Feedforward)
mit Sigmoid-Aktivierung und Binary Cross Entropy Loss.

**Ablauf:**
```
Trainingsdaten → Forward Pass → Loss berechnen → Backpropagation → Gewichte updaten
```

Dieser Ablauf wiederholt sich für jede Epoche bis entweder die maximale
Epochenzahl erreicht ist oder der Loss unter das Abbruchkriterium fällt.

---

## 2. Trainingsdaten vorbereiten

Trainingsdaten bestehen immer aus zwei parallelen Listen:

```java
ArrayList<ArrayList<Double>> inputs   = new ArrayList<>();
ArrayList<ArrayList<Double>> expected = new ArrayList<>();
```

Jeder Eintrag in `inputs` entspricht einem Trainingsbeispiel.
Der Eintrag an derselben Position in `expected` ist die erwartete Ausgabe.

**Wichtige Regeln:**
- Erwartete Ausgaben müssen 0.0 oder 1.0 sein (Binary Cross Entropy Loss, Sigmoid-Output)
- Kontinuierliche Inputs (z.B. Temperaturen) werden in den Beispielen auf 0.0 bis 1.0 normalisiert
- `inputs.size()` muss gleich `expected.size()` sein

**Beispiel — 1 Input, 1 Output (binär):**
```java
inputs.add(new ArrayList<>(Arrays.asList(0.0)));
expected.add(new ArrayList<>(Arrays.asList(1.0)));
```

**Beispiel — 4 Inputs, 3 Outputs (One-Hot):**
```java
// Klasse 0 = [1,0,0], Klasse 1 = [0,1,0], Klasse 2 = [0,0,1]
inputs.add(new ArrayList<>(Arrays.asList(0.2, 0.8, 0.1, 0.5)));
expected.add(new ArrayList<>(Arrays.asList(0.0, 1.0, 0.0)));
```

**Normalisierung kontinuierlicher Werte:**
```java
// Beispiel: Temperatur von -20°C bis 50°C → 0.0 bis 1.0
double norm = (temperatur + 20.0) / 70.0;
inputs.add(new ArrayList<>(Arrays.asList(norm)));
```

---

## 3. Netz erstellen

```java
NeuralNetwork net = new NeuralNetwork(
    int    num_inputs,       // Anzahl Input-Neuronen
    int[]  layer_sizes,      // Größe jeder Schicht inkl. Output
    double learningrate,     // Lernrate
    double stop_threshold    // Abbruchkriterium
);
```

**Parameter im Detail:**

### num_inputs
Muss gleich der Länge eines einzelnen Trainingsbeispiels sein.
```java
// Wenn inputs.get(0).size() == 4, dann:
new NeuralNetwork(4, ...)
```

### layer_sizes
Letzter Wert = Anzahl Output-Neuronen.
Alle anderen Werte = Hidden Layer Größen.
```java
new int[]{4, 1}       // 1 Hidden Layer (4 Neuronen), 1 Output
new int[]{8, 4, 1}    // 2 Hidden Layer, 1 Output
new int[]{16, 8, 3}   // 2 Hidden Layer, 3 Outputs (z.B. 3 Klassen)
```

Bei Mehrklassen-Problemen ist der letzte Wert die Anzahl der Klassen (One-Hot).
Welche Größen in den Beispielen verwendet werden, steht in Abschnitt 8.

### learningrate
Wie groß die Schritte beim Gradientenabstieg sind.
In den Beispielen werden 0.1 bis 0.2 verwendet.

### stop_threshold
Training stoppt, wenn `avg_loss < stop_threshold`.
In den Beispielen werden Werte zwischen 0.001 und 0.05 verwendet.

---

## 4. Training

```java
net.train(
    ArrayList<ArrayList<Double>> inputs,
    ArrayList<ArrayList<Double>> expected,
    int epochs,      // maximale Epochenzahl
    int log_every    // alle N Epochen wird geloggt und das Abbruchkriterium geprüft
);
```

Das Training stoppt automatisch früher, wenn der Loss unter `stop_threshold` fällt
(Early Stopping). Die maximale Epochenzahl ist nur ein Sicherheitsnetz.

**Hinweis:** Das Abbruchkriterium wird nur alle `log_every` Epochen geprüft.
Ein größeres `log_every` kann das Training daher etwas verlängern.

---

## 5. Vorhersagen

```java
ArrayList<Double> result = net.predict(ArrayList<Double> input);
```

`predict()` gibt eine Liste mit allen Output-Neuronen zurück.

**1 Output-Neuron (binäre Klassifikation):**
```java
ArrayList<Double> input = new ArrayList<>(Arrays.asList(1.0, 0.0));
ArrayList<Double> result = net.predict(input);

double wert    = result.get(0);        // z.B. 0.847
int    gerundet = wert > 0.5 ? 1 : 0; // → 1
```

**Mehrere Output-Neuronen (Mehrklassen):**
```java
ArrayList<Double> result = net.predict(input);

// Klasse mit höchstem Wert finden
int bestIdx = 0;
for (int j = 1; j < result.size(); j++) {
    if (result.get(j) > result.get(bestIdx)) bestIdx = j;
}
System.out.println("Erkannte Klasse: " + bestIdx);
System.out.println("Konfidenz: " + (result.get(bestIdx) * 100) + "%");
```

---

## 6. Ausgabe

### printPredictions
```java
net.printPredictions(inputs, expected);
```
Zeigt für jedes Trainingsbeispiel: Input, erwarteter Output,
vorhergesagter Output, gerundeter Output (Schwelle 0.5).

### printLossHistory
```java
net.printLossHistory();
```
Zeigt den durchschnittlichen Loss aller geloggten Epochen.

---

## 7. Häufige Probleme

### Loss bleibt bei ~0.69 stehen
0.69 ≈ ln(2) ist der Binary-Cross-Entropy-Loss, wenn das Netz für alle
Beispiele etwa 0.5 ausgibt, also nichts gelernt hat.
Die Gewichte werden bei jedem Start zufällig initialisiert (`Math.random()`),
ein Neustart liefert daher einen anderen Ausgangspunkt.
Einen festen Seed gibt es im Framework nicht.

### Vorhersagen auf neuen Daten schlechter als auf Trainingsdaten
Im Beispiel `Main_03_MiniMNIST` erreicht das Netz 100 % auf den Trainingsdaten,
aber nur 80 % auf unbekannten Testbildern. Das Netz generalisiert also nur teilweise.

### Ergebnisse unterscheiden sich von Lauf zu Lauf
Wegen der zufälligen Initialisierung sind Epochenzahl bis zum Early Stopping
und die Ausgaben bei uneindeutigen Eingaben (z.B. der Mittelpunkt in
`Main_04_3DCluster`) bei jedem Lauf anders.

---

## 8. Beispiele nach Komplexität

| Datei                     | Problem | Inputs | Outputs | layer_sizes | learningrate | stop_threshold | Besonderheit |
|---------------------------|---------|--------|---------|-------------|--------------|----------------|--------------|
| `Main_00_Quickstart.java` | AND | 2 | 1 | `{4, 1}` | 0.1 | 0.001 | Einstieg, linear trennbar |
| `Main_01_XOR.java`        | XOR | 2 | 1 | `{4, 1}` | 0.1 | 0.001 | Nicht-linear trennbar |
| `Main_02_Temperatur.java` | Temperatur | 1 | 3 | `{8, 4, 3}` | 0.1 | 0.01 | Kontinuierlicher Input |
| `Main_03_MiniMNIST.java`  | Ziffernerkennung | 25 | 10 | `{64, 32, 10}` | 0.1 | 0.05 | Bildklassifikation, Test auf unbekannten Bildern |
| `Main_04_3DCluster.java`  | 3D Punkte | 3 | 4 | `{16, 8, 4}` | 0.1 | 0.01 | Geometrische Klassifikation |

Lernrate 0.2 wird in `src/Main.java` (XOR) verwendet.

---

## Komplettes Minimalbeispiel

```java
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Trainingsdaten
        ArrayList<ArrayList<Double>> inputs   = new ArrayList<>();
        ArrayList<ArrayList<Double>> expected = new ArrayList<>();
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 0.0))); expected.add(new ArrayList<>(Arrays.asList(0.0)));
        inputs.add(new ArrayList<>(Arrays.asList(0.0, 1.0))); expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 0.0))); expected.add(new ArrayList<>(Arrays.asList(1.0)));
        inputs.add(new ArrayList<>(Arrays.asList(1.0, 1.0))); expected.add(new ArrayList<>(Arrays.asList(0.0)));

        // Netz erstellen
        NeuralNetwork net = new NeuralNetwork(2, new int[]{4, 1}, 0.1, 0.001);

        // Trainieren
        net.train(inputs, expected, 100000, 1000);

        // Ausgabe
        net.printPredictions(inputs, expected);
        net.printLossHistory();
    }
}
```

---

*Framework entwickelt von Daniel Stein*
