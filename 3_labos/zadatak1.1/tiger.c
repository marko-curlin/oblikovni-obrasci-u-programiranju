#include <malloc.h>

typedef char const* (*PTRFUN)(void);

typedef struct Tiger {
	PTRFUN* vtable;
	const char* name;
} Tiger;

__declspec(dllexport) char const* name(void* this) {
	return ((Tiger*)this)->name;
}
__declspec(dllexport) char const* greet(void) {
	return "Mijau!";
}
__declspec(dllexport) char const* menu(void) {
	return "mlako mlijeko";
}

PTRFUN tigerFunc[3] = { name, greet, menu };

void construct(Tiger * tiger, char const * name) {
	tiger->name = name;
	tiger->vtable = tigerFunc;
}

__declspec(dllexport) Tiger *create(const char *name) {
	Tiger* tiger = (Tiger*)malloc(sizeof(Tiger));
	construct(tiger, name);
	return tiger;
}