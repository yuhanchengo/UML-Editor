#include "JetCar.h"
#include <iostream>
using namespace std;

JetCar::JetCar(){cout << "JetCar constructor" << endl;}
JetCar::~JetCar(){cout << "JetCar destructor" << endl;}
void JetCar::drive(){cout << "JetCar driving" << endl;}
void JetCar::fly(){cout << "JetCar flying" << endl;}
void JetCar::mySpeed(){
	Car::mySpeed(); 
	Jet::mySpeed();
}

