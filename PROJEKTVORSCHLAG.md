# Projektvorschlag: Tic-Tac-Toe KI mit eigenem Neural Network Framework

**Student:** Daniel Stein  


---

## Ausgangslage

Im Rahmen des Selbststudiums habe ich ein vollständiges neuronales Netz in Java
von Grund auf implementiert — ohne externe Bibliotheken. Das Framework umfasst
Forward Pass, Backpropagation, Gradient Descent und Early Stopping, und wurde
erfolgreich auf sechs aufsteigend schwierige Probleme angewendet (XOR, 4-Bit
Parität, Sieben-Segment-Anzeige, Temperaturklassifikation, Ziffernerkennung,
3D Punktwolken-Klassifikation).

Den vollständigen Code und alle Beispiele finden Sie hier:  
→ github.com/[username]/neural-network-java

---

## Projektidee

Aufbauend auf diesem Framework möchte ich eine **Tic-Tac-Toe KI** trainieren
und als spielbare Webanwendung veröffentlichen.

**Technischer Ansatz:**

1. Einen Minimax-Algorithmus implementieren der alle ~5.500 möglichen
   Spielzustände optimal bewertet
2. Diese Zustände als Trainingsdaten für das neuronale Netz verwenden
3. Das Netz trainieren den optimalen Zug für jeden Spielzustand vorherzusagen
4. Eine einfache Website bauen auf der man gegen die trainierte KI spielen kann

**Input/Output des Netzes:**
```
Input:  9 Felder  [0 = leer, 0.5 = Spieler, 1.0 = KI]
Output: 9 Neuronen [Wahrscheinlichkeit für jeden möglichen Zug]
```

---


---

## Transparenz: Was selbst implementiert wurde

### Kernlogik — selbst geschrieben

| Klasse | Inhalt |
|--------|--------|
| `Input_layer.java` | Speichert den Input-Vektor |
| `Intermediate_layer.java` | Neuronen, Gewichte, Bias, Xavier-ähnliche Initialisierung |
| `Calc_weigthed_sum.java` | Forward Pass — berechnet gewichtete Summen |
| `Activations_functions.java` | Sigmoid-Aktivierungsfunktion |
| `Loss_Function.java` | Binary Cross Entropy Loss |
| `Gradient_Decent.java` | Berechnet neue Gewichte und Biases |
| `Update_weights.java` | Schreibt neue Gewichte zurück (Bug eigenständig identifiziert) |
| `Update_biases.java` | Schreibt neue Biases zurück |

KI kam bei der Kernlogik nur zum Einsatz um Konzepte zu erklären, Rechnungen zu überprüfen, Syntaxfehler und Bugs zu finden und zu fixen (in fast allen fällen selbst korrigiert).

### Mit KI-Unterstützung entstanden

| Datei                                 | Beschreibung |
|---------------------------------------|--------------|
| `NeuralNetwork.java`                  | Abstraktion des Frameworks — damit können beliebig große Netze mit einer einzigen Zeile erstellt werden, ohne hunderte Zeilen in der Main zu schreiben. Baut auf den selbst geschriebenen Klassen auf. |
| `Main_00` bis `Main_04`               | Beispiel-Mains durch KI generiert um das Framework auf verschiedenen Problemen zu demonstrieren |
| `README.md`, `FRAMEWORK_ANLEITUNG.md` | Durch KI generiert und von mir angepasst |
| Formelblätter in `docs/`              | Durch KI generiert, von mir verstanden und als Referenz beim Implementieren benutzt |

### Wie KI konkret eingesetzt wurde
- Konzepte erklären (Backpropagation, Vanishing Gradient, lokale Minima)
- Debug-Logs und eigene Rechnungen überprüfen lassen
- Syntaxfehler finden
- YouTube-Videos zum Verständnis ergänzend genutzt
- Formelblätter als Referenz generieren lassen

### Zur Code-Qualität
Da die Kernlogik fast ausschließlich selbst geschrieben wurde ist der Code nicht auf Effizienz optimiert — er spiegelt den Denkprozess wider, nicht eine fertige Bibliothek. Die `ArrayList<ArrayList<ArrayList<Double>>>` Datenstruktur ist langsamer als `double[][]` Arrays, macht aber den Aufbau des Netzes intuitiv sichtbar. Für die Lernziele dieses Projekts ist das ausreichend — PyTorch macht dieselbe Mathematik, nur als Matrizenoperationen auf GPU.

---

---

## Zeitplan (Vorschlag)

| Woche | Ziel |
|-------|------|
| 1–2 | Minimax implementieren, Trainingsdaten generieren |
| 3–4 | Netz trainieren, Genauigkeit evaluieren |
| 5–6 | Website bauen (HTML/JS Frontend, Java Backend) |
| 7   | Testen, Dokumentation, Abgabe |

---

## Frage an Sie

Darf ich dieses Projekt — aufbauend auf dem selbst entwickelten Framework —
als Prüfungsleistung für [Kursname] einreichen?

Ich bin offen für Anpassungen des Umfangs oder der Anforderungen.

---

*Daniel Stein — [18.06.2026]*
