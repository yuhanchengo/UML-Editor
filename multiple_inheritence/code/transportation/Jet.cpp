#include "Jet.h"
#include <iostream>
using namespace std;

Jet::Jet(){cout << "Jet constructor" << endl;}
Jet::~Jet(){cout << "Jet destructor" << endl;}
void Jet::mySpeed(){cout << "It is flying at a speed of: " << speed << endl;}
void Jet::fly(){cout << "Flying a Jet" << endl;}

