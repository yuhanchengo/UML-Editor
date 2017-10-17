#include "Jet.h"
#include "Car.h"
#include "JetCar.h"
#include <iostream>
using namespace std;

void showSpeed(Transportation *testTransportation);
void checkCarPerformance(Car * testTransportation);
void checkFlyingPerformance(Jet *testTransportation);
int main(){
	Jet myJet;
	Car myCar;
	JetCar myJetCar;
	
	myJet.setSpeed(8);
	myCar.setSpeed(6);
	myJetCar.Jet::setSpeed(10);
	myJetCar.Car::setSpeed(6);
	
	cout << endl <<endl;	
	// polymorphism usage
	cout << "transportation testing" << endl;
	checkCarPerformance(&myCar);
	checkCarPerformance(&myJetCar);
	checkFlyingPerformance(&myJet);
	checkFlyingPerformance(&myJetCar);

	cout << endl <<endl;
	showSpeed(&myJet);
	showSpeed(&myCar);
	showSpeed(&myJetCar);
	cout << endl <<endl;

	return 0;
}
void showSpeed(Transportation *testTransportation){
	testTransportation->mySpeed();
}
void checkCarPerformance(Car *testTransportation){
	testTransportation->drive();
}
void checkFlyingPerformance(Jet *testTransportation){
	testTransportation->fly();
}
