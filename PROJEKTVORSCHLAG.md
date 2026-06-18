# Projektvorschlag: Tic-Tac-Toe KI mit eigenem Neural Network Framework

**Student:** Daniel Stein  
**Kurs:** [Kursname]  
**Betreuer:** [Name des Profs]

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

## Transparenz zum bisherigen Prozess

KI-Tools wurden als Lernwerkzeug eingesetzt:
- Konzepte erklären und Formelblätter generieren
- Eigene Rechnungen und Debug-Logs überprüfen lassen
- Syntaxfehler finden

Den Code habe ich fast vollständig selbst geschrieben.
Das mathematische Verständnis (Backpropagation, Gradient Descent,
Loss-Funktionen) habe ich mir durch YouTube, generierte Formelblätter
und eigenes Ausprobieren erarbeitet.

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

*Daniel Stein — [Datum]*
