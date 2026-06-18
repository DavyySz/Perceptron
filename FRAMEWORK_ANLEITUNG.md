# Framework-Anleitung — Neural Network Java

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
- Alle Werte müssen zwischen 0.0 und 1.0 liegen
- Kontinuierliche Werte (z.B. Temperaturen) müssen normalisiert werden
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
new int[]{64, 32, 10} // größeres Netz für komplexere Probleme
```

**Faustregel für die Netzgröße:**
- Einfache binäre Probleme (AND, OR): `{4, 1}` reicht
- Nicht-linear trennbar (XOR): mindestens `{4, 1}`
- Mehrere Klassen: letzte Zahl = Anzahl Klassen
- Bildklassifikation: deutlich größer, z.B. `{64, 32, 10}`

### learningrate
Wie groß die Schritte beim Gradientenabstieg sind.

| Wert | Effekt |
|------|--------|
| 0.01 | sehr langsam, sicher, selten lokale Minima |
| 0.1  | gut für die meisten Probleme (Empfehlung) |
| 0.5  | schnell aber kann oszillieren |
| 1.0  | meist zu groß, Training divergiert |

### stop_threshold
Training stoppt wenn `avg_loss < stop_threshold`.

| Wert | Wann sinnvoll |
|------|---------------|
| 0.1  | schnelles Testen, grobe Ergebnisse |
| 0.01 | gute Genauigkeit für die meisten Probleme |
| 0.001| hohe Präzision, längeres Training |
| 0.0001| sehr hohe Präzision, kann sehr lange dauern |

---

## 4. Training

```java
net.train(
    ArrayList<ArrayList<Double>> inputs,
    ArrayList<ArrayList<Double>> expected,
    int epochs,      // maximale Epochenzahl
    int log_every    // alle N Epochen wird geloggt
);
```

**Empfohlene Werte:**

| Problem | epochs | log_every |
|---------|--------|-----------|
| Einfach (AND, OR) | 10000 | 100 |
| Mittel (XOR) | 100000 | 1000 |
| Schwer (Parität) | 100000000 | 1000 |
| Bildklassifikation | 100000000 | 1000 |

Das Training stoppt automatisch früher wenn der Loss unter `stop_threshold` fällt
(Early Stopping). Die maximale Epochenzahl ist nur ein Sicherheitsnetz.

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
Zeigt für jedes Trainingsbeispiel: Input, Erwarteter Output,
Vorhergesagter Output, Gerundeter Output, Korrekt/Falsch.

### printLossHistory
```java
net.printLossHistory();
```
Zeigt den Loss-Verlauf über alle geloggten Epochen.
Ein gleichmäßig sinkender Verlauf bedeutet das Netz lernt gut.

---

## 7. Häufige Probleme

### Loss stagniert bei ~0.69
Das Netz ist im lokalen Minimum. Lösungen:
- Training neu starten (Gewichte werden zufällig neu initialisiert)
- Lernrate erhöhen (z.B. von 0.1 auf 0.2)
- Netz größer machen

### Loss sinkt aber Vorhersagen sind falsch
Zu wenig Epochen oder stop_threshold zu hoch.
Lösung: stop_threshold kleiner setzen (z.B. von 0.01 auf 0.001).

### Training läuft sehr lange ohne Verbesserung
Bei schwierigen Problemen (z.B. 4-Bit Parität) kann das Netz
lange in lokalen Minima feststecken bevor es sich selbst befreit.
Einfach warten — oder neu starten mit anderem Seed.

### Vorhersagen auf neuen Daten schlechter als auf Trainingsdaten
Das Netz hat auswendig gelernt statt zu generalisieren.
Lösungen:
- Mehr und vielfältigere Trainingsdaten
- Netz kleiner machen
- stop_threshold erhöhen (weniger präzises Training)

### Alle Vorhersagen sind ~0.5
Netz konvergiert nicht. Lernrate zu klein oder Netz zu klein.

---

## 8. Beispiele nach Komplexität

| Datei                     | Problem | Inputs | Outputs | Besonderheit |
|---------------------------|---------|--------|---------|--------------|
| `Main_00_Quickstart.java` | AND | 2 | 1 | Einstieg, linear trennbar |
| `Main_01_XOR.java`        | XOR | 2 | 1 | Nicht-linear trennbar |
| `Main_02_Temperatur.java` | Temperatur | 1 | 3 | Kontinuierlicher Input |
| `Main_03_MiniMNIST.java`  | Ziffernerkennung | 25 | 10 | Bildklassifikation, Generalisierung |
| `Main_04_3DCluster.java`  | 3D Punkte | 3 | 4 | Geometrische Klassifikation |

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

*Framework entwickelt von Daniel Stein — TH [Name], [Kurs], [Semester]*
