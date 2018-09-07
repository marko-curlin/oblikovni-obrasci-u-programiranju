#include <Windows.h>
#include <stdio.h>
#include <tchar.h>

#define BUFSIZE 4096

typedef int(__cdecl *MYPROC)(char const*);

char* stradd(const char* a, const char* b) {
	size_t len = strlen(a) + strlen(b);
	char *ret = (char*)malloc(len * sizeof(char) + 1);
	*ret = '\0';
	return strcat(strcat(ret, a), b);
}

void* myfactory(char const* libname, char const* ctorarg) {
	HINSTANCE hinstLib;
	MYPROC ProcAdd;

	DWORD  retval = 0;
	TCHAR  buffer[BUFSIZE] = TEXT("");
	TCHAR  buf[BUFSIZE] = TEXT("");
	TCHAR** lppPart = { NULL };
	
	char *new_libname;// = libname;

	new_libname = stradd(".\\", libname);
	new_libname = stradd(new_libname, ".dll");

	hinstLib = LoadLibrary(new_libname);

	if (hinstLib == NULL) {
		printf("Fail");
		exit(1);
	}

	ProcAdd = (MYPROC)GetProcAddress(hinstLib, "create");

	if (ProcAdd == NULL) {
		return NULL;
	}

	return (ProcAdd)(ctorarg);
}