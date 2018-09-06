#include<iostream>
#include<vector>
#include<set>

template <typename Iterator, typename Predicate>
Iterator mymax(Iterator cur, Iterator last, Predicate pred) {
	
	Iterator max = cur;

	for (; cur != last; cur++) {
		if (pred(max, cur) < 0) {
			max = cur;
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

int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
char arr_char[] = "Suncana strana ulice";
const char* arr_str[] = {
	"Gle", "malu", "vocku", "poslije", "kise",
	"Puna", "je", "kapi", "pa", "ih", "njise"
};
int main() {
	const int* maxint = mymax(&arr_int[0],
		&arr_int[sizeof(arr_int) / sizeof(*arr_int)], gt_int);
	std::cout << *maxint << "\n";
	printf("Najveci char= %c\n", *(char*)mymax(&arr_char[0], &arr_char[sizeof(arr_char) / sizeof(char)], gt_char));
	printf("Najveci string= %s\n", *(char**)mymax(arr_str, &arr_str[sizeof(arr_str) / sizeof(char*)], gt_str));

	std::vector<int> int_vec(arr_int, arr_int + sizeof(arr_int) / sizeof(arr_int[0]));
	maxint = mymax(&int_vec[0],
		&int_vec.back(), gt_int);
	std::cout << *maxint << "\n";
	std::set<char> char_set(&arr_char[0], arr_char + sizeof(arr_char) / sizeof(arr_char[0]));
	printf("Najveci char= %c\n", *(char*)mymax(&char_set.begin(), &char_set.end(), gt_char));

	return 0;
}
