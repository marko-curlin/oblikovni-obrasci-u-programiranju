#include<list>
#include<iostream>

struct Point {
	int x; int y;
};
class Shape {
private:
	Point center;
public:
	virtual void draw() = 0;
	void move(int x, int y) {
		center.x += x;
		center.y += y;
	}
};
class Circle : public Shape {
private:
	double radius;
public:
	virtual void draw() {
		std::cerr << "in drawCircle\n";
	}
};
class Square : public Shape {
private:
	double side;
public:
	virtual void draw() {
		std::cerr << "in drawSquare\n";
	}
};
class Rhomb : public Shape {
private:
	double side;
	double angle;
public:
	virtual void draw() {
		std::cerr << "in drawRhomb\n";
	}
};

void drawShapes(const std::list<Shape*>& fig) {
	std::list<Shape*>::const_iterator it;
	for (it = fig.begin(); it != fig.end(); it++) {
		(*it)-> draw();
	}
}

void moveShapes(const std::list<Shape*>& fig, int x, int y) {
	std::list<Shape*>::const_iterator it;
	for (it = fig.begin(); it != fig.end(); it++) {
		(*it)->move(x, y);
	}
}

int main() {
	std::list<Shape*> shapes;
	shapes.push_front((Shape*)new Circle);
	shapes.push_front((Shape*)new Square);
	shapes.push_front((Shape*)new Circle);
	shapes.push_front((Shape*)new Square);
	shapes.push_front((Shape*)new Rhomb);

	drawShapes(shapes);
	moveShapes(shapes, 1, 1);

	return 0;
}