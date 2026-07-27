// Alessia Cassetta, 2113909

#include <stdio.h>

/*
funzioni ausiliarie
=============================================================================
*/
void printArray(int arr[], int size)
{
    for (int i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

int min(int x, int y)
{
    return (x < y) ? x : y;
}

/*
ESERCIZIO 1
=============================================================================
*/
void func1()
{
    int a = 1;
    char *k = (char *)&a;
    if (*k == 1)
    {
        printf("Esercizio 1: Il sistema e' Little-Endian\n");
    }
    else
    {
        printf("Esercizio 1: Il sistema e' Big-Endian\n");
    }
}

/*
ESERCIZIO 2
=============================================================================
*/

// funzione merge che prende in input un array, un array temporaneo, e gli indici left, mid e right. Questa funzione ordina la parte dell'array compresa tra left e right
void merge(int arr[], int temp[], int left, int mid, int right)
{
    int i = left;
    int j = mid + 1;
    int k = left;

    // con questo while confrontiamo gli elementi delle due metà e li copiamo in ordine crescente in temp
    while (i <= mid && j <= right)
    {
        if (arr[i] <= arr[j])
        {
            temp[k++] = arr[i++];
        }
        else
        {
            temp[k++] = arr[j++];
        }
    }

    // se è avanzato qualcosa nella prima metà, si copia tutto in temp
    while (i <= mid)
    {
        temp[k++] = arr[i++];
    }

    // altrimenti si copia tutto quello che è avanzato nella seconda metà in temp
    while (j <= right)
    {
        temp[k++] = arr[j++];
    }

    // per concludere mettiamo tutti gli elementi che abbiamo ordinato da temp ad arr
    for (i = left; i <= right; i++)
    {
        arr[i] = temp[i];
    }
}

void func2_1(int arr[], int n)
{
    // si alloca dinamicamente un array temporaneo in modo tale da non doverlo creare e distruggere ogni volta che chiamiamo merge.
    int *temp = (int *)malloc(n * sizeof(int)); // con malloc specifichiamo la dimensione in byte, e moltiplichiamo per il numero di elementi che vogliamo (n) e per la dimensione di ogni elemento (sizeof(int))
    if (temp == NULL)
    {
        printf("Errore: memoria insufficiente!\n");
        return;
    }

    // size è la dimensione dei sottoarray che uniamo, inziando con uno e moltiplicando per 2 ad ogni iterazione finchè non è più grande di size
    for (int size = 1; size < n; size = 2 * size)
    {
        for (int left = 0; left < n - 1; left += 2 * size)
        {
            int mid = min(left + size - 1, n - 1);
            int right = min(left + 2 * size - 1, n - 1);
            merge(arr, temp, left, mid, right);
        }
    }
    printArray(arr, n);
    // si libera la memoria allocata per evitare memory leak
    free(temp);
}

void func2_2(int arr[], int n)
{
    if (n <= 1)
        return;

    int *temp = (int *)malloc(n * sizeof(int)); // array temporaneo per la fusione
    int *runs = (int *)malloc(n * sizeof(int)); // array per memorizzare gli indici di inizio delle sottosequenze già ordinate
    if (temp == NULL || runs == NULL)
    {
        printf("Errore di allocazione memoria.\n");
        if (temp)
            free(temp);
        if (runs)
            free(runs);
        return;
    }

    // ricerca delle sottosequenze gia ordinate
    int k = 0;
    runs[k++] = 0;
    for (int i = 1; i < n; i++)
    {
        if (arr[i] < arr[i - 1])
        {
            runs[k++] = i;
        }
    }

    // fusione delle sottosequenze ordinate, continuiamo a fondere finché non rimane una sola
    while (k > 1)
    {
        int next_k = 0;
        for (int i = 0; i < k; i += 2)
        {
            if (i + 1 < k)
            {
                int left = runs[i];                                // l'inizio della prima sottosequenza è dato da runs[i]
                int mid = runs[i + 1] - 1;                         // la fine della prima sottosequenza è l'elemento prima dell'inizio della seconda
                int right = (i + 2 < k) ? runs[i + 2] - 1 : n - 1; // se esiste una terza sottosequenza, altrimenti si prende fino alla fine dell'array

                merge(arr, temp, left, mid, right);
                runs[next_k++] = left;
            }
            else
            {
                runs[next_k++] = runs[i];
            }
        }
        // aggiorniamo il numero totale di run per il prossimo ciclo
        k = next_k;
    }
    printArray(arr, n);
    free(temp);
    free(runs);
}

/*
ESERCIZIO 3
=============================================================================
*/
typedef struct Node
{
    int value;
    struct Node *left;
    struct Node *right;
    struct Node *up;
} Node;

void func3(Node *root)
{
    if (root == NULL)
        return;

    Node *curr = root;
    Node *prev = NULL;

    while (curr != NULL)
    {
        Node *next = NULL;
        if (prev == curr->up)
        {
            if (curr->left != NULL)
            {
                next = curr->left; // proviamo ad andare a sinistra
            }
            else
            {
                printf("%d ", curr->value); // se non possiamo andare a sinistra, stampiamo il nodo corrente

                if (curr->right != NULL)
                {
                    next = curr->right; // se possiamo andare a destra, ci andiamo
                }
                else
                {
                    next = curr->up; // altrimenti torniamo indietro
                }
            }
        }
        else if (prev == curr->left)
        {
            printf("%d ", curr->value); // stampiamo il nodo corrente dopo essere tornati da sinistra

            if (curr->right != NULL)
            {
                next = curr->right; // se possiamo andare a destra, ci andiamo
            }
            else
            {
                next = curr->up; // nulla, torniamo indietro
            }
        }
        else if (prev == curr->right)
        {
            next = curr->up; // torniamo su
        }
        // aggiorniamo prev e curr per il prossimo ciclo
        prev = curr;
        curr = next;
    }
}

int main()
{

    func1();
    func2_1((int[]){38, 27, 43, 3, 9, 82, 10}, 7);
    func2_2((int[]){38, 27, 43, 3, 9, 82, 10}, 7);
    return 0;
}