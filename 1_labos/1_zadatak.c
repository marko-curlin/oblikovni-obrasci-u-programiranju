#include<malloc.h>
#include<stdio.h>
#define GREET 0
#define MENU 1

typedef char const* (*PTRFUN)();
typedef struct Animal {
	char const* name;
	PTRFUN *funcTable;
} Animal;

void testAnimals(void);
char const* dogGreet(void);
char const* dogMenu(void);
char const* catGreet(void);
char const* catMenu(void);
void constructDog(void* addr, char const* name);
void constructCat(void* addr, char const* name);
Animal *createDog(const char *name);
Animal *createCat(const char *name);
void animalPrintGreeting(Animal* animal);
void animalPrintMenu(Animal* animal);
void testHeapAndStack(void);
Animal * createNDogs(int n);
void freeAnimalName(Animal*, int);
void testNDogs(int n);

PTRFUN dogFunc[2] = { dogGreet, dogMenu };
PTRFUN catFunc[2] = { catGreet, catMenu };

int main(void) {
	testAnimals();
	testHeapAndStack();
	printf("\n");
	testNDogs(3);
	return 0;
}

void testAnimals(void) {
	struct Animal* p1 = createDog("Hamlet");
	struct Animal* p2 = createCat("Ofelija");
	struct Animal* p3 = createDog("Polonije");

	animalPrintGreeting(p1);
	animalPrintGreeting(p2);
	animalPrintGreeting(p3);

	animalPrintMenu(p1);
	animalPrintMenu(p2);
	animalPrintMenu(p3);

	free(p1); free(p2); free(p3);
}

void testHeapAndStack(void) {
	//created on stack
	Animal dog;
	constructDog(&dog, "dog");
	//created on heap
	Animal *cat = createCat("cat");

	printf("\nStack-dog: %p\nHeap-cat: %p\n", &dog, cat);
	free(cat);
}

char const* dogGreet(void) {
	return "vau!";
}
char const* dogMenu(void) {
	return "kuhanu govedinu";
}
char const* catGreet(void) {
	return "mijau!";
}
char const* catMenu(void) {
	return "konzerviranu tunjevinu";
}

void animalPrintGreeting(Animal * animal) {
	printf("%s pozdravlja: %s\n", animal->name, animal->funcTable[GREET]());
}

void animalPrintMenu(Animal * animal) {
	printf("%s voli %s\n", animal->name, animal->funcTable[MENU]());
}

void constructDog(Animal * dog, char const * name){
	dog->name = name;
	dog->funcTable = dogFunc;
}

void constructCat(Animal * cat, char const * name){
	cat->name = name;
	cat->funcTable = catFunc;
}

Animal *createDog(const char *name) {
	Animal* dog = (Animal*) malloc(sizeof(Animal));
	constructDog(dog, name);
	return dog;
}

Animal *createCat(const char *name) {
	Animal* cat = (Animal*) malloc(sizeof(Animal));
	constructCat(cat, name);
	return cat;
}

Animal * createNDogs(int n) {
	Animal* dogs = (Animal*) malloc(sizeof(Animal) * n);
	Animal* returnValue = dogs;

	for (int i = 1; i <= n; i++) {
		char *name = (char*)malloc(sizeof(char)*3);
		sprintf(name, "%d", i);
		constructDog(dogs++, name);
	}
	return returnValue;
}

void testNDogs(int n) {
	Animal* dogs = createNDogs(n);
	Animal* dogsIterator = dogs;
	for (int i = 0; i < n; i++) {
		animalPrintGreeting(dogsIterator);
		dogsIterator++;
	}
	freeAnimalName(dogs, n);
	free(dogs); //who let the dogs out
}

void freeAnimalName(Animal* animals, int n) {
	for (int i = 0; i < n; i++) {
		free(animals->name);
		animals++;
	}
}