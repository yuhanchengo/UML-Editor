#include "Transportation.h"
#include <iostream>
using namespace std;
// constructor
Transportation::Transportation(){cout << "transportation constructor" << endl;}
// destructor
Transportation::~Transportation(){cout << "transportation destructor" << endl;}
// method
void Transportation::setSpeed(double s){speed = s;}

