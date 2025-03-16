//
// Created by alebox on 16/03/25.
//
#include<stdio.h>
int main(){
  int i;
  printf("Hello World\n");
  printf("Inserisci un numero: \n");
  scanf("%i",&i);
  printf("il numero che hai inserito è: %i\n",i);
  return 0;
}

/* Alcune informazioni UTILI:
* Per compilare un file .c viene eseguito un comando: gcc -Wall nomeFile.c -lm
* Per eseguire il file eseguibile, che ha rilasciato il comando precedente, potete usare: ./nomefile.out
* Per rinominare l'eseguibile: gcc -Wall nomeFile.c -o nomeEseguibile.o

 * */
