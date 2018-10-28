#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void swap(int *tab, int a, int b) {
    int temp = tab[a];
    tab[a] = tab[b];
    tab[b] = temp;
}

int countRows() {
    FILE *file = fopen("test.txt", "r");
	char c;
	int a = 0;

	while ((c = getc(file)) != EOF){
	    if (c == '\n')
            a++;
	}

    fclose(file);

    return a;
}

void getResult(int *tab, int size) {
    FILE *file = fopen("result.txt", "wb");
    for (int i = 0; i < size; i++)
        fprintf(file, "%d\n", tab[i]);

    fclose(file);
}

void heapify(int *tab, int n, int k) {

    // RECURSIVE //

    // int max = k;
    // int l = (k*2) + 1;
    // int p = (k*2) + 2;

    // if (l < n) {
    //     if (tab[max] < tab[l])
    //     max = l;
    // }

    // if(p < n && tab[p] > tab[max])
    //     max = p;

    // if (max != k) {
    //     swap(tab, k, max);
    //     heapify(tab, n, max);
    // }

    // ITERATIVE //

    int max;
    int l;
    int p;

    while (true) {
        max = k;
        l = (k*2) + 1;
        p = (k*2) + 2;

        if (l < n) {
            if (tab[max] < tab[l])
            max = l;
        }

        if(p < n && tab[p] > tab[max])
            max = p;

        if (max != k) {
            swap(tab, k, max);
            k = max;
        }

        else 
            break;
    }
}

void buildHeap(int *tab, int n) {
    for (int i = (n/2); i >= 0; i--)
        heapify(tab, n, i);
}

void heapSort(int *tab, int n) {
    for (int i = n-1; i >= 1; i--) {
        swap(tab, 0, i);
        heapify(tab, i, 0);
    }
}

void main() {
    FILE *file = fopen("test.txt", "r");
    int size = countRows();
    int *tab = (int*) malloc(sizeof(*tab)*size);

    for (int i = 0; i < size; i++)    
        fscanf(file, "%d", &tab[i]);

    buildHeap(tab, size);
    heapSort(tab, size);
    getResult(tab, size);

    free(tab);
    fclose(file);
}