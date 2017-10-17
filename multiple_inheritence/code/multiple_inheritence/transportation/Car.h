#ifndef CAR_H
#define CAR_H

#include "Transportation.h"
class Car: public virtual Transportation{
	public:
		Car();
		virtual ~Car();
		virtual void drive();
		virtual void mySpeed();
};
#endif
