#include <iostream>
#include <assert.h>

struct Point {
	int x; int y;
};
struct Shape {
	enum EType { circle, square, rhomb };
	EType type_;
};
struct Circle {
	Shape::EType type_;
	double radius_;
	Point center_;
};
struct Square {
	Shape::EType type_;
	double side_;
	Point center_;
};
struct Rhomb {
	Shape::EType type_;
	double side_;
	Point center;
	double angle;
};
void drawSquare(struct Square*) {
	std::cerr << "in drawSquare\n";
}
void drawCircle(struct Circle*) {
	std::cerr << "in drawCircle\n";
}
void drawRhomb(struct Rhomb*) {
	std::cerr << "in drawRomb\n";
}
void drawShapes(Shape** shapes, int n) {
	for (int i = 0; i<n; ++i) {
		struct Shape* s = shapes[i];
		switch (s->type_) {
		case Shape::square:
			drawSquare((struct Square*)s);
			break;
		case Shape::circle:
			drawCircle((struct Circle*)s);
			break;
		case Shape::rhomb:
			drawRhomb((struct Rhomb*)s);
			break;
		default:
			assert(0);
			exit(0);
		}
	}
}
void moveSquare(struct Square* square, int x, int y) {
	square->center_.x += x;
	square->center_.y += y;
}
void moveCircle(struct Circle* circle, int x, int y) {
	circle->center_.x += x;
	circle->center_.y += y;
}
void printLocationOfSquare(Square* square) {
	std::cout << "x= " << square->center_.x << ", y= " << square->center_.y << std::endl;
}
void printLocationOfCircle(Circle* circle) {
	std::cout << "x= " << circle->center_.x << ", y= " << circle->center_.y << std::endl;
}
void moveShapes(Shape** shapes, int n, int x, int y) {
	for (int i = 0; i < n; i++) {
		struct Shape* s = shapes[i];
		switch (s->type_) {
		case Shape::square:
			moveSquare((struct Square*)s, x, y);
			//printLocationOfSquare((struct Square*)s);
			break;
		case Shape::circle:
			moveCircle((struct Circle*)s, x, y);
			//printLocationOfCircle((struct Circle*)s);
			break;
		default:
			assert(0);
			exit(0);
		}
	}
}
int main1() {
	Shape* shapes[4];
	shapes[0] = (Shape*)new Circle;
	shapes[0]->type_ = Shape::circle;
	shapes[1] = (Shape*)new Square;
	shapes[1]->type_ = Shape::square;
	shapes[2] = (Shape*)new Square;
	shapes[2]->type_ = Shape::square;
	shapes[3] = (Shape*)new Rhomb;
	shapes[3]->type_ = Shape::rhomb;

	drawShapes(shapes, 4);
	moveShapes(shapes, 4, 1, 1);

	return 0;
}