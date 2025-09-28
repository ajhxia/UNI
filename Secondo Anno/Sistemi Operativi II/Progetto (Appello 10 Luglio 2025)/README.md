# Progetto Simulator Circuiti Quantistici
#### Data: 10-07-2025
#### Matricola: 2113909
#### GitHub: [github.com/ajhxia](https://github.com/ajhxia/UNI/tree/main/Secondo%20Anno/Sistemi%20Operativi%20II/Progetto%20(Appello%209%20Giugno%202025))
#### Corso: Sistemi Operativi II

## Descrizione
Questo progetto implementa un simulatore di circuiti quantistici scritto in C.  
Il programma permette di leggere file di configurazione contenenti la definizione di circuiti quantistici, di applicare operazioni su numeri complessi e di simulare il comportamento dei gate quantistici.

## Requisiti
- Compilatore GCC
- Sistema operativo Unix-like (Linux, macOS) o ambiente compatibile con `make`

### _IMPORTANTE_ 📂
#### _Formattazione dei Numeri Complessi_
È accettato qualunque formato matematicamente corretto, purché l'unità immaginaria `i` preceda sempre il coefficiente.
|Valido|	Non valido|
|-------|-----------|
|`-0.3`	|`0.4-0.5i`|
|`0.1+i`	|`0.5i-1`|
|`0.8-i0.5`	||

#### _FORMATO_

#### FILE: `init`
- Contenuto richiesto:
  - Una riga che inizia con `#qubits`, seguita da un numero intero che rappresenta la quantità di qubit.
  - Una riga che inizia con `#init`, seguita da un vettore di numeri complessi. Il vettore deve: essere racchiuso tra parentesi quadre `[ ]` contenente numeri complessi separati da virgole.

#### FILE: `circ`
- Contenuto richiesto:
  - Una o più righe che iniziano con `#define Y`, dove `Y` è un identificatore (uno o più caratteri) associato a una matrice di numeri complessi. La matrice deve: essere racchiusa tra parentesi quadre `[ ]` contenente righe (vettori) racchiuse tra parentesi tonde `()`.
Avere numeri complessi formattati correttamente, separati da virgole.
  -  Una riga che inizia con `#circ`, seguita da una sequenza di caratteri e/o stringhe che identificano le matrici da applicare nel circuito.


## Come compilare ed eseguire
Per poter eseguire correttamente il programma, **`i file che il programma dovrà leggere in input devono essere inseriti nella cartella chiamata file_input`**.
Inoltre per poter eseguire il programma è necessario aprire il terminale nella cartella dove si trova il file `Makefile`.
### Per compilare, eseguire il comando:
```bash
make
```
### Per eseguire, utilizzare:
```bash 
./main <file_input/`nome_init`> <file_input/`nome_circ`> <`numero_di_threads`>
```

### Per pulire i file compilati (file oggetto eseguibile), usare:
```bash
make clean
```

🎐
