#ifndef TRANSPORTATION_H
#define TRANSPORTATION_H

class Transportation{
	public:
		Transportation();
		virtual ~Transportation();
		void setSpeed(double s);
		virtual void mySpeed() = 0;
	protected:
		double speed;
};
#endif
