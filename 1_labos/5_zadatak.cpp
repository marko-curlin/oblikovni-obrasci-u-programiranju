#include<iostream>

class B {
public:
	virtual int prva() = 0;
	virtual int druga() = 0;
};

class D : public B {
public:
	virtual int prva() { return 0; }
	virtual int druga() { return 42; }
};

using namespace std;

typedef int (*FUN)();

void function(B *pb) {
	cout << "prva()= ";
	FUN **fun = (FUN**)pb;
	cout << (**fun)() << endl;
	cout << "druga()= ";
	cout << (*(*fun+1))() << endl;
}

int main(void) {
	B *pb = new D;
	function(pb);
	return 0;
}