#include <malloc.h>

typedef char const* (*PTRFUN)(void);

typedef struct Parrot {
	PTRFUN* vtable;
	const char* name;
} Parrot;

__declspec(dllexport) char const* name(void* this) {
	return ((Parrot*)this)->name;
}
__declspec(dllexport) char const* greet(void) {
	return "Sto mu gromova!";
}
__declspec(dllexport) char const* menu(void) {
	return "brazilske orahe";
}

PTRFUN parrotFunc[3] = { name, greet, menu };

void construct(Parrot * parrot, char const * name) {
	parrot->name = name;
	parrot->vtable = parrotFunc;
}

__declspec(dllexport) Parrot *create(const char *name) {
	Parrot* parrot = (Parrot*)malloc(sizeof(Parrot));
	construct(parrot, name);
	return parrot;
}