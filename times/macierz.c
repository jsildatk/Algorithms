#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <stdbool.h>
#define MAX 60000l
#define MLD 1000000000.0
/////////////////////////////////////////////
//   PROCEDURY POMOCNICZE                  //
/////////////////////////////////////////////
void utworz_MACIERZ(int n, int ***M){
// alokujê pamiêæ na tablicê rozmiaru nxn
// i wpisuje losowe wartoci 0/1 w macierzy
int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++){
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            (*M)[i][j]=rand()% 2;
            }
    }
}
/////////////////////////////////////////////
void utworz_MACIERZ_x(int n, int ***M, int x){
// alokujê pamiêæ na tablicê rozmiaru nxn
// i wpisuje do macierzy wszêdzie wartoci x
int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++){
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            (*M)[i][j]=x;
            }
    }
}
/////////////////////////////////////////////
void wypisz_MACIERZ(int n, int **M){
// wypisuje wartoci macierzy
int i,j;

for(i=0;i<n;i++){
    for(j=0;j<n;j++)
        printf("%d",M[i][j]);
    printf("\n");
    }
}
/////////////////////////////////////////////
void zwolnij_MACIERZ(int n, int **M){
// zwalania pamiêæ zarezerwowanṗ dla macierzy
int i;
    for(i=0;i<n;i++)
    {
    free(M[i]);
    }
    free(M);
}
/////////////////////////////////////////////
//   ALGORYTM PIERWSZY                     //
/////////////////////////////////////////////
int ALGO_NAIWNY(int n, int **M){
int x1,y1,x2,y2,x,y;
int max=0;
int local_max=0;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++)
        for(x2=n-1;x2>x1-1;x2--)
            for(y2=n-1;y2>y1-1;y2--){
                local_max=0;
                for(x=x1;x<x2+1;x++)
                    for(y=y1;y<y2+1;y++)
                        local_max+=M[x][y];
                if ((local_max==(x2-x1+1)*(y2-y1+1)) && (local_max>max)) max=local_max;
                }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM DRUGI                        //
/////////////////////////////////////////////
int REKURENCJA(int **M,int x1, int y1, int x2, int y2){
if ((x2==x1)&&(y2==y1)) return M[x1][y1];
    else if ((x2-x1)>(y2-y1))
        return REKURENCJA(M,x1,y1,(int)(x1+x2)/2,y2)*REKURENCJA(M,(int)(x1+x2+1)/2,y1,x2,y2);
            else return REKURENCJA(M,x1,y1,x2,(int)(y1+y2)/2)*REKURENCJA(M,x1,(int)(y1+y2+1)/2,x2,y2);
}
/////////////////////////////////////////////
int ALGO_REKURENCYJNY(int n, int **M){
int x1,y1,x2,y2;
int max=0;
int local_max;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++)
        for(x2=x1;x2<n;x2++)
            for(y2=y1;y2<n;y2++){
                local_max=REKURENCJA(M,x1,y1,x2,y2)*(x2-x1+1)*(y2-y1+1);
                if (local_max>max) max=local_max;
            }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM TRZECI                       //
/////////////////////////////////////////////
int ALGO_DYNAMICZNY(int n, int **M){
int x1,x2,y;
int max=0;
int iloczyn;
int MM[n][n];

for(y=0;y<n;y++)
    for(x1=0;x1<n;x1++){
        iloczyn=1;
        for(x2=x1;x2<n;x2++){
            iloczyn*=M[x2][y];
            MM[x1][x2]=iloczyn*(x2-x1+1+MM[x1][x2]);
            if (MM[x1][x2]>max) max=MM[x1][x2];
        }
    }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM CZWARTY                      //
/////////////////////////////////////////////
int ALGO_CZULY(int n, int **M){
int x1,y1,x2,y2,ymax;
int max=0;
int local_max=0;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++){
        local_max=0;
        x2=x1;
        ymax=n-1;
        while ((x2<n)&&(M[x2][y1]==1)){
            y2=y1;
            while((y2<ymax+1)&&(M[x2][y2]==1)){
                y2++;
            }
            ymax=y2-1;
            local_max=(x2-x1+1)*(ymax-y1+1);
            if (local_max>max) max=local_max;
            x2++;
        }
    }
return max;
}
/////////////////////////////////////////////
/////////////////////////////////////////////
// FUNKCJE //
struct timespec tp0, tp1; 
double Tn,Fn,x;
int **Macierz;

void callNaiwny(int a, bool m, int x) {
    for (long int n = 1; n <= a; n+=1) {
        if (!m)
            utworz_MACIERZ(n,&Macierz); // macierz 0-1
        else 
            utworz_MACIERZ_x(n, &Macierz, x); // macierz 0 lub 1

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_NAIWNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        Fn = n*n*n*n*n * log(n) * sqrt(n); // 0-1, 0, 1

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }
}

void callRekurencyjny(int a, bool m, int x) {
    for (long int n = 1; n <= a; n+=1) {
        if (!m)
            utworz_MACIERZ(n,&Macierz); // macierz 0-1
        else 
            utworz_MACIERZ_x(n, &Macierz, x); // macierz 0 lub 1

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_REKURENCYJNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        Fn = n*n*n*n * sqrt(n*n*n*n) - n; // 0-1, 0, 1

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }
}

void callDynamiczny(int a, bool m, int x) {
    for (long int n = 5; n <= a; n+=5) {
        if (!m)
            utworz_MACIERZ(n,&Macierz); // macierz 0-1
        else 
            utworz_MACIERZ_x(n, &Macierz, x); // macierz 0 lub 1

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_DYNAMICZNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        if (!m)
            Fn = n*n*n + 5; // 0-1
        else  
            Fn = n*n*n * log(n) + 5; // 0, 1

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }
}

void callCzuly(int a, bool m, int x) {
    for (long int n = 5; n <= a; n+=5) {
        if (!m)
            utworz_MACIERZ(n,&Macierz); // macierz 0-1
        else 
            utworz_MACIERZ_x(n, &Macierz, x); // macierz 0 lub 1

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_CZULY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        if (x == 0)
            Fn = n*n + 9; // dla 0-1, 0
        else 
            Fn = n*n*n*n + 9; // dla 1

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }
}
/////////////////////////////////////////////
/////////////////////////////////////////////
int main(){
    srand(time(NULL));

    // (liczbaTestow, czyMajaBycStaleWartosci, stalaWartosc)

    // callNaiwny(50, false, 0); // naiwny z 0-1 // Fn = n*n*n*n*n * log(n) * sqrt(n)
    // callNaiwny(50, true, 0); // naiwny z 0 // Fn = n*n*n*n*n * log(n) * sqrt(n)
    // callNaiwny(50, true, 1); // naiwny z 1 // Fn = n*n*n*n*n * log(n) * sqrt(n)

    // callRekurencyjny(50, false, 0); // rekurencyjny z 0-1 // Fn = n*n*n*n * sqrt(n*n*n*n)
    // callRekurencyjny(50, true, 0); // rekurencyjny z 0 // Fn = n*n*n*n * sqrt(n*n*n*n)
    // callRekurencyjny(50, true, 1); // rekurencyjny z 1 // Fn = n*n*n*n * sqrt(n*n*n*n)

    // callDynamiczny(500, false, 0); // dynamiczny z 0-1 // Fn = n*n*n + 5
    // callDynamiczny(500, true, 0); // dynamiczny z 0 // Fn = n*n*n * log(n) + 5
    // callDynamiczny(500, true, 1); // dynamiczny z 1 // Fn = n*n*n * log(n) + 5

    // callCzuly(1000, false, 0); // czuly z 0-1 // Fn = n*n + 9
    // callCzuly(1000, true, 0); // czuly z 0 // Fn = n*n + 9
    // callCzuly(200, true, 1); // czuly z 1 // Fn = n*n*n*n + 9

    return 1;
}
