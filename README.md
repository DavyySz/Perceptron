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

| Klasse | Inhalt | Eigenanteil |
|--------|--------|-------------|
| `Input_layer.java` | Speichert den Input-Vektor | ✅ selbst |
| `Intermediate_layer.java` | Neuronen, Gewichte, Bias, Xavier-Init | ✅ selbst |
| `Calc_weigthed_sum.java` | Forward Pass (gewichtete Summe) | ✅ selbst |
| `Activations_functions.java` | Sigmoid | ✅ selbst |
| `Loss_Function.java` | Binary Cross Entropy | ✅ selbst |
| `Gradient_Decent.java` | Berechnet neue Gewichte und Biases | ✅ selbst |
| `Update_weights.java` | Schreibt neue Gewichte zurück | ✅ selbst (Bug eigenständig gefunden) |
| `Update_biases.java` | Schreibt neue Biases zurück | ✅ selbst |
| `NeuralNetwork.java` | Generischer Wrapper, Backprop-Schleife | 🤝 mit KI-Unterstützung |

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
├── src/
│   ├── NeuralNetwork.java          ← Hauptklasse, hier starten
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
    ├── Main_02_Paritaet.java
    ├── Main_03_SiebenSegment.java
    ├── Main_04_Temperatur.java
    ├── Main_05_MiniMNIST.java
    └── Main_06_3DCluster.java
```

---

## Schnellstart

```java
// Netz erstellen: 2 Inputs → 4 Hidden → 1 Output
NeuralNetwork net = new NeuralNetwork(2, new int[]{4, 1}, 0.1, 0.001);

// Trainieren
net.train(inputs, expected, 100000, 1000);

// Vorhersage
ArrayList<Double> result = net.predict(input);

// Ausgabe
net.printPredictions(inputs, expected);
net.printLossHistory();
```

---

## Gelöste Probleme

### 1. XOR
Das klassische nicht-linear trennbare Problem — braucht mindestens eine Hidden Layer.  
Konvergiert bei ~3000–5000 Epochen.

### 2. 4-Bit Parität
Output = 1 wenn die Anzahl der Einsen gerade ist. Alle 16 Kombinationen.  
Historisch bekannt als schwieriges Problem (Minsky & Papert, 1969).  
Spektakulärer Loss-Verlauf: zwei lokale Minima, zwei Ausbrüche, Konvergenz bei Epoche ~217.000.

```
Epoch   1000: Loss 0.7058  ← lokales Minimum
Epoch 136000: Loss 0.6627  ← erster Ausbruch
Epoch 141000: Loss 0.1829  ← zweites Minimum
Epoch 212000: Loss 0.0104  ← zweiter Ausbruch
Epoch 217000: Loss 0.0010  ← Early Stopping ✓
```

### 3. Sieben-Segment-Anzeige
4-Bit Input → 7 simultane Outputs (Segmente a–g für Ziffern 0–9).  
10/10 korrekt in nur 1000 Epochen — weil 7 unabhängige einfache Funktionen gleichzeitig gelernt werden.

### 4. Temperaturklassifikation
Erster kontinuierlicher Input (normalisierte Temperatur).  
Output: [kalt, warm, heiß] — das Netz lernt Entscheidungsgrenzen auf einer Zahlengeraden.  
Testet Generalisierung auf unbekannte Temperaturen.

### 5. Mini-MNIST (5×5 Pixel)
25 Inputs, 10 Outputs, 110 Trainingsbeispiele (11 Varianten pro Ziffer).  
100% Trainingsgenauigkeit, 80% auf komplett unbekannten Testbildern.  
Echte Bildklassifikation ohne Bibliotheken.

### 6. 3D Punktwolken
3 kontinuierliche Inputs (x, y, z), 4 Cluster in Würfelecken.  
Testet geometrische Klassifikation im kontinuierlichen Raum.  
Der Mittelpunkt (0.5, 0.5, 0.5) hat absichtlich keine richtige Antwort — niedrige Konfidenz dort ist das korrekte Verhalten.

---

## Interessante Beobachtungen

**Lokale Minima:** Das 4-Bit Paritätsproblem zeigt eindrücklich wie Gradient Descent in lokalen Minima feststecken kann und sich durch akkumulierte kleine Schritte selbst befreit — ohne dass von außen eingegriffen wird.

**Warum Sieben-Segment schnell ist:** 7 unabhängige Binärfunktionen. Warum Parität langsam ist: globale Eigenschaft aller Bits gleichzeitig — strukturell viel schwerer.

**Xavier-ähnliche Initialisierung:** `(Math.random() * 2.0 - 1.0) * 0.5` wurde intuitiv entdeckt — symmetrisch um 0, klein genug um Vanishing Gradients zu vermeiden.

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

*Projekt von Daniel Stein — TH [Name einfügen], [Kursname einfügen], [Semester]*
