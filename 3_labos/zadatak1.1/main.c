#include "myfactory.h"

#include <stdio.h>
#include <stdlib.h>

#define NAME 0
#define GREET 1
#define MENU 2

typedef char const* (*PTRFUN)();


typedef struct Animal {
	PTRFUN* vtable;
	// vtable entries:
	// 0: char const* name(void* this);
	// 1: char const* greet();
	// 2: char const* menu();
} Animal;

// parrots and tigers defined in respective dynamic libraries

void animalPrintGreeting(Animal *animal) {
	printf("%s pozdravlja: %s\n", animal->vtable[NAME](animal), animal->vtable[GREET]());
}

void animalPrintMenu(Animal *animal) {
	printf("%s voli %s\n", animal->vtable[NAME](animal), animal->vtable[MENU]());
}


int main(void) {
	struct Animal* p1 = (struct Animal*)myfactory("parrot", "Modrobradi");
	struct Animal* p2 = (struct Animal*)myfactory("tiger", "Strasko");
	if (!p1 || !p2) {
		printf("Creation of plug-in objects failed.\n");
		exit(1);
	}
	
	animalPrintGreeting(p1);//"Sto mu gromova!"
	animalPrintGreeting(p2);//"Mijau!"

	animalPrintMenu(p1);//"brazilske orahe"
	animalPrintMenu(p2);//"mlako mlijeko"

	free(p1); free(p2);
	
}