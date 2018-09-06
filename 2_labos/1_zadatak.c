#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const void* mymax(
	const void *base, size_t nmemb, size_t size,
	int(*compar)(const void *, const void *)) {

	const void *max = base;
	char* first_element = (char*)base;

	for (size_t i = 1; i < nmemb; i++) {

		const void *current_element = (first_element + i*size);

		if (compar(max, current_element) < 0) {
			max = current_element;
		}

	}

	return max;

}

int gt_int(const void* a, const void* b) {

	const int* A = (int*)a;
	const int* B = (int*)b;

	return (*A > *B) - (*A < *B);
}

int gt_char(const void* a, const void* b) {

	const char* A = (char*)a;
	const char* B = (char*)b;

	return (*A > *B) - (*A < *B);
}

int gt_str(const void* a, const void* b) { 

	const char** A = (const char**)a;
	const char** B = (const char**)b;

	return strcmp(*A, *B);
}

int main(void) {
	int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
	char arr_char[] = "Suncana strana ulice";
	const char* arr_str[] = {
		"Gle", "malu", "vocku", "poslije", "kise",
		"Puna", "je", "kapi", "pa", "ih", "njise"
	};

	printf("Najveci int= %d\n", *(const int*)mymax(arr_int, sizeof(arr_int) / sizeof(int), sizeof(int), gt_int));
	printf("Najveci char= %c\n", *(char*)mymax(arr_char, sizeof(arr_char) / sizeof(char), sizeof(char), gt_char));
	printf("Najveci string= %s\n", *(char**)mymax(arr_str, sizeof(arr_str) / sizeof(char*), sizeof(char*), gt_str));

	return 0;
}