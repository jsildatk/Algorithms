#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct list {
    struct list *next;
    char string[50];
} list;

list* insert(list *l, char *string) {
    list *new = malloc(sizeof(list));
    strcpy(new -> string, string);
    new -> next = l;
    return new;
}

void print(list *l) {
    if (l == NULL) {
        printf("Lista jest pusta\n");
    } else {
        do {
            printf("%s\n", l -> string);
            l = l -> next;
        } while (l != NULL);
    }
}

list* search(list *l, char *string) {
    if (l == NULL) {
        printf("Lista jest pusta\n");
    } else {
        do {
            if (strcmp(l -> string, string) == 0) {
                return l;
            } else {
                l = l -> next;
            }
        } while (l != NULL);
    }
    return NULL;
}

void deleteItem(list *l, char *string) {
    list *temp = NULL;
    list *c = l;
    while (c != NULL) {
        if (strcmp(c -> string, string) == 0) {
            break;
        }
        temp = c;
        c = c -> next;
    }

    if (c != NULL) {
        if (c == l) {
            l = c -> next;
        } else {
            temp -> next = c -> next;
        }
        free(c);
    }
}

void deleteList(list **l) {
    list* temp;
    while(*l != NULL) {
        temp = *l;
        *l = (*l) -> next;
        free(temp);
    }
}

list* deleteDuplicates(list *l) {
    list *new = NULL;
    list *temp2 = new;
    list *temp = l;
    bool n;
    while (temp != NULL) {
        n = true;
        while (temp2 != NULL) {
            if (strcmp(temp -> string, temp2 -> string) == 0) {
                n = false;
            }
            temp2 = temp2 -> next;
        }
        if (n) {
            new = insert(new, temp -> string);
            temp2 = new;
        }
        temp = temp -> next;
    }
    return new;
}

list* merge(list *l, list *l2) {
    list *new = l;
    list *temp = l;
    while (temp -> next != NULL) {
        temp = temp -> next;
    }
    temp -> next = l2;
    return new;
}

void main() {
    list *l = NULL;
    list *l2 = NULL;
    list *l3 = NULL;
    // Fill list 
    l = insert(l, "asdf");
    l = insert(l, "asdf");
    l = insert(l, "zxcv");
    l = insert(l, "qqwer");
    l = insert(l, "hfgjhg");
    l = insert(l, "xcv");
    l = insert(l, "rty");
    l = insert(l, "xddasdxz");
    print(l);
    printf("--------------\n");
    // Find in list
    printf("%p\n", search(l, "xcv"));
    printf("%p\n", search(l, "xcASv"));
    printf("%p\n", search(l, "asdf"));
    printf("--------------\n");
    // Delete from list 
    deleteItem(l, "qqwer");
    print(l);
    printf("--------------\n");
    // Create list without duplicates
    l2 = deleteDuplicates(l);
    print(l2);
    printf("--------------\n");
    // Merge lists 
    l3 = merge(l, l2);
    print(l3);
    printf("--------------\n");
    // // Delete enitre list 
    deleteList(&l);
    print(l);
    printf("--------------\n");
}