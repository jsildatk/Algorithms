#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define MAX 60000l
#define MLD 1000000000.0

void swap(int *tab, int a, int b) {
    int temp = tab[a];
    tab[a] = tab[b];
    tab[b] = temp;
}

int partition(int *tab, int p, int size) {
    int x = tab[size];
    int i = p - 1;

    for (int j = p; j <= size; j++) {
        if (tab[j] <= x) {
            i++;
            swap(tab, i, j);
        }
    }
    if (i < size) {
        return i;
    }
    return i-1;
}

void insertionSort(int *tab, int size) {
    int j;
    int temp;

    for (int i = 1; i < size; i++) {
        temp = tab[i];
        j = i - 1;

        while (j >= 0 && tab[j] > temp) {
            tab[j+1] = tab[j];
            j--;
        }

        tab[j+1] = temp;
    }
}

void quickSort(int *tab, int p, int size, bool t) {
    int q;
    int c = 10;

    if (t) {
        if (size - p + 1 < c) {
            insertionSort(tab, size);
        } else if (p < size) {
            q = partition(tab, p, size);
            quickSort(tab, p, q, true);
            quickSort(tab, q+1, size, true);
        }
    } else {
        if (p < size) {
            q = partition(tab, p, size);
            quickSort(tab, p, q, false);
            quickSort(tab, q+1, size, false);
        }
    }
}

void fillTabRandom(int *tab, int size) {
    for (long int i = 0; i < size; i++) {
        tab[i] = rand()%1000000 + 1;
    }
}

void fillTabNegative(int *tab, int size) {
    int c = 1234;
    int max = size+c;
    int next = max-1;
    int i = 1;
    tab[0] = max;

    while (i < size) {
        tab[i] = next;
        next--;
        i++;
    }
}

void main() {
    srand(time(NULL));
    FILE *resultRandom = fopen("resultRandom.txt", "wb");
    FILE *resultNegative = fopen("resultNegative.txt", "wb");
    fprintf(resultRandom, "DANE LOSOWE\n\nRozmiar  |  Algorytm 1 |\n");
    fprintf(resultNegative, "DANE NIEKORZYSTNE\n\nRozmiar  |  Algorytm 1 |\n");
    struct timespec tp0, tp1; 
    double Tn,Tn2,Fn,x;
    long int size;
    int *tab;

    for (long int i = 2000; i <= 200000; i+=2000) {
        size = i;
        tab = (int*) malloc(sizeof(*tab)*size);
        fillTabRandom(tab, size);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        quickSort(tab, 0, size-1, false);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        fprintf(resultRandom, "%5ld | %3.10lf |\n", size, Tn);
        free(tab);
    }

    fprintf(resultRandom, "\n\nRozmiar  |  Algorytm 2 |\n");
    for (long int i = 2000; i <= 100000; i+=2000) {
        size = i;
        tab = (int*) malloc(sizeof(*tab)*size);
        fillTabRandom(tab, size);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        quickSort(tab, 0, size-1, true);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        fprintf(resultRandom, "%5ld | %3.10lf |\n", size, Tn);
        free(tab);
    }

    for (long int i = 2000; i <= 60000; i+=2000) {
        size = i;
        tab = (int*) malloc(sizeof(*tab)*size);
        fillTabNegative(tab, size);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        quickSort(tab, 0, size-1, false);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        fprintf(resultNegative, "%5ld | %3.10lf |\n", size, Tn);
        free(tab);
    }

    fprintf(resultNegative, "\n\nRozmiar  |  Algorytm 2 |\n");
    for (long int i = 2000; i <= 60000; i+=2000) {
        size = i;
        tab = (int*) malloc(sizeof(*tab)*size);
        fillTabNegative(tab, size);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        quickSort(tab, 0, size-1, true);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        fprintf(resultNegative, "%5ld | %3.10lf |\n", size, Tn);
        free(tab);
    }

    fclose(resultRandom);
    fclose(resultNegative);

}