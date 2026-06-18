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
