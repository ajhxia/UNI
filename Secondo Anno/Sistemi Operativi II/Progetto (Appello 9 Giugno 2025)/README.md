# Progetto Simulator Circuiti Quantistici
### Scritto da: Alessia Cassetta 
### Data: 25-05-2025 
## Descrizione

Questo progetto implementa un simulatore di circuiti quantistici scritto in C.  
Il programma permette di leggere file di configurazione contenenti la definizione di circuiti quantistici, di applicare operazioni su numeri complessi e di simulare il comportamento dei gate quantistici.

## Struttura

- `main.c` — Punto di ingresso del programma, coordina l’esecuzione generale.  
- `file_reader.c` / `.h` — Funzioni per il parsing e la lettura dei file di input.  
- `utility.c` / `.h` — Funzioni di supporto generiche (es. gestione stringhe, operazioni comuni).  
- `operation.c` / `.h` — Implementazione delle operazioni e dei gate quantistici.  
- `Makefile` — Script per la compilazione e gestione del progetto.

## Requisiti

- Compilatore GCC  
- Sistema operativo Unix-like (Linux, macOS) o ambiente compatibile con `make`  

## Come compilare ed eseguire

Per compilare e avviare il simulatore, eseguire il comando:

```bash
make
```
Il comando compila il programma e lo esegue immediatamente.

Per pulire i file compilati (file oggetto e eseguibile), usare:
```bash
make clean
```
