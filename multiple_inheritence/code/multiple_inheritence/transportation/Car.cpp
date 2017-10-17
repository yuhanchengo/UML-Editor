#include "Car.h"
#include <iostream>
using namespace std;

Car::Car(){cout << "Car constructor" << endl;}
Car::~Car(){cout << "Car destructor" << endl;}
void Car::drive(){cout << "Driving a Car" << endl;}
void Car::mySpeed(){cout << "It is running at a speed of : " << speed << endl;}

