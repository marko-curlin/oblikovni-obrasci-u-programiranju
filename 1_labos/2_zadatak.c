#include<stdbool.h>
#include<stdlib.h>
#include<stdio.h>

typedef void (*PTRFUN)();
typedef double(*DOUBLE_FUN)();

typedef struct Unary_Function {
	PTRFUN* funcTable;
	int lower_bound;
	int upper_bound;
}Unary_Function;

typedef struct Square {
	PTRFUN* funcTable;
	int lower_bound;
	int upper_bound;
}Square;

typedef struct Linear {
	PTRFUN* funcTable;
	int lower_bound;
	int upper_bound;
	double a;
	double b;
}Linear;


double value_at_square(Square*, double);
double value_at_linear(Linear*, double);
double negative_value_at(Unary_Function*, double);
void tabulate(Unary_Function*);
bool same_functions_for_ints(Unary_Function*, Unary_Function*, double);
void constructSquare(Square*, int, int);
Square* createSquare(int, int);
void constructUnaryFunction(Unary_Function*, int, int);
Unary_Function* createUnaryFunction(int, int);


PTRFUN unary_func[] = { NULL, negative_value_at };
PTRFUN square_func[] = { value_at_square, negative_value_at };
PTRFUN linear_func[] = { value_at_linear, negative_value_at };

void constructUnaryFunction(Unary_Function * unary_function, int lb, int ub) {
	unary_function->lower_bound = lb;
	unary_function->upper_bound = ub;
	unary_function->funcTable = unary_func;
}

Unary_Function *createUnaryFunction(int lb, int ub) {
	Unary_Function *unaryFunction = (Unary_Function*)malloc(sizeof(Unary_Function));
	constructUnaryFunction(unaryFunction, lb, ub);
	return unaryFunction;
}

void constructSquare(Square * square, int lb, int ub) {
	constructUnaryFunction((Unary_Function*)square, lb, ub);
	square->funcTable = square_func;
}

Square* createSquare(int lb, int ub) {
	Square *square = (Square*)malloc(sizeof(Square));
	constructSquare(square, lb, ub);
	return square;
}

void constructLiner(Linear * linear, int lb, int ub, double a_coef, double b_coef) {
	constructUnaryFunction((Unary_Function*)linear, lb, ub);
	linear->a = a_coef;
	linear->b = b_coef;
	linear->funcTable = linear_func;
}

Linear* createLinear(int lb, int ub, double a_coef, double b_coef) {
	Linear *linear = (Linear*)malloc(sizeof(Linear));
	constructLiner(linear, lb, ub, a_coef, b_coef);
	return linear;
}

double value_at_square(Square* self, double x) {
	return x*x;
}

double value_at_linear(Linear* self, double x) {
	return self->a*x + self->b;
}

double negative_value_at(Unary_Function *self, double x) {
	return -((DOUBLE_FUN)self->funcTable[0])(self, x);
}

void tabulate(Unary_Function *self) {
	for (int x = self->lower_bound; x <= self->upper_bound; x++) {
		printf("f(%d)=%lf\n", x, ((DOUBLE_FUN)self->funcTable[0])(self, (double)x));
	}
}

bool same_functions_for_ints(Unary_Function *f1, Unary_Function *f2, double tolerance) {
	if (f1->lower_bound != f2->lower_bound) return false;
	if (f1->upper_bound != f2->upper_bound) return false;
	for (int x = f1->lower_bound; x <= f1->upper_bound; x++) {
		double delta = ((DOUBLE_FUN)f1->funcTable[0])(f1, (double)x) - ((DOUBLE_FUN)f2->funcTable[0])(f2, (double)x);	//zasto ne radi bez casta
		if (delta < 0) delta = -delta;
		if (delta > tolerance) return false;
	}
	return true;
}

int main() {
	Unary_Function *f1 = (Unary_Function*)createSquare(-2, 2);
	tabulate(f1);
	Unary_Function *f2 = (Unary_Function*)createLinear(-2, 2, 5, -2);
	tabulate(f2);
	printf("f1==f2: %s\n", same_functions_for_ints(f1, f2, 1E-6) ? "DA" : "NE");
	printf("neg_val f2(1) = %lf\n", ((DOUBLE_FUN)f2->funcTable[1])(f2, 1.0));
	free(f1);
	free(f2);
	return 0;
}
/* -- tocan rezultat
f(-2)=4.000000
f(-1)=1.000000
f(0)=0.000000
f(1)=1.000000
f(2)=4.000000
f(-2)=-12.000000
f(-1)=-7.000000
f(0)=-2.000000
f(1)=3.000000
f(2)=8.000000
f1==f2: NE
neg_val f2(1) = -3.000000
*/
