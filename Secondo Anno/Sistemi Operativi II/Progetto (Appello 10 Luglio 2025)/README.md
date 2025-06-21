# Progetto Simulator Circuiti Quantistici
#### Data: 10-07-2025
#### Matricola: 2113909

## Descrizione
Questo progetto implementa un simulatore di circuiti quantistici scritto in C.  
Il programma permette di leggere file di configurazione contenenti la definizione di circuiti quantistici, di applicare operazioni su numeri complessi e di simulare il comportamento dei gate quantistici.

## Requisiti
- Compilatore GCC
- Sistema operativo Unix-like (Linux, macOS) o ambiente compatibile con `make`

### _IMPORTANTE_ 📂
Per poter eseguire correttamente il programma, **`i file che il programma dovrà leggere in input devono essere inseriti nella cartella chiamata file_input`**.
Inoltre per poter eseguire il programma è necessario aprire il terminale nella cartella dove si trova il file `Makefile`.

## Come compilare ed eseguire
Per compilare e avviare il simulatore, eseguire il comando:
```bash
make
```
Il comando compila il programma e lo esegue immediatamente. Appena il programma inizia viene **`richiesto di inserire il nome dei due file (compresi di estensione)`** di configurazione del circuito quantistico da simulare.

Per pulire i file compilati (file oggetto eseguibile), usare:
```bash
make clean
```

🎐