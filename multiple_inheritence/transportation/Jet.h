#ifndef JET_H
#define JET_H
#include "Transportation.h"
class Jet: public virtual Transportation{
	public: 
		Jet();
		virtual ~Jet();
		virtual void mySpeed();
		virtual void fly();
};
#endif
