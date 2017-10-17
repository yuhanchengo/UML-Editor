#ifndef JETCAR_H
#define JETCAR_H
#include "Car.h"
#include "Jet.h"
class JetCar: public Car, public Jet{
	public:
		JetCar();
		virtual ~JetCar();
		virtual void drive();
		virtual void fly();
		virtual void mySpeed();
};
#endif
