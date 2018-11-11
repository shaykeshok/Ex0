package myMath;

import java.text.DecimalFormat;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and b is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Shayke Shok and Itay Grinblat
 *
 */
public class Monom implements function {
	private double _coefficient;
	private int _power;

	/**
	 * This is the constractor of the Monom
	 * @param a is the coefficient. The user input
	 * @param b is the power. The user input
	 * @throws DataException when the data input is wrong
	 */
	public Monom(double a, int b) throws DataException{
		if (b<0)
		{
			throw new DataException("Negitive number for power is unpossible = " + b);
		}
		if (a != 0) {
			this.set_coefficient(a);
			this.set_power(b);
		}
		else
			throw new DataException("Coefficient number cannot be equal to zero = " +a);

	}
	
	/**
	 * Copy constructor, get Monom and create a copy of it
	 * @param ot is the Monom that we copy
	 * @throws DataException when the data given is wrong 
	 */
	public Monom(Monom ot) throws DataException {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	/**
	 * Power getter
	 * @return _power
	 */
	public int get_power() {
		return _power;
	}
	
	/**
	 * Coefficient getter
	 * @return _coefficient
	 */
	public double get_coefficient() {
		return _coefficient;
	}


	/**
	 * The function calculates the value of f(x) in x  point
	 * @param x the point to calculate of y
	 * @return f(x) value
	 */
	public double f(double x) {
		return _coefficient * (Math.pow(x, _power));
	}
	
	/**
	 * Add monom to other monom only if the power of both equal.
	 * What we do is to add the coefficient of one monom to other monom.
	 * @param monom that we need to add
	 * @throws DataException when the powers not equals
	 */
	public void add(Monom m) throws DataException {
		DecimalFormat df = new DecimalFormat("#.##");
		if (_power == m._power)
			this.set_coefficient((Double.parseDouble(df.format(this.get_coefficient()+ m.get_coefficient()))));
		else
			throw new DataException("You cannot add monom with diffrent power");
	}
	
	/**
	 * The punction get monom and multiply with another monom
	 * @param monom to multiply with another monom
	 */
	public void multiply(Monom m) {
		double c=this.get_coefficient();
		double v = m.get_coefficient();
		DecimalFormat df = new DecimalFormat("#.##");
		this.set_coefficient((Double.parseDouble(df.format(c*v))));
		this.set_power(this.get_power()+m.get_power());
	}
	
	/**
	 * This function calculates the derivative of the monom 
	 * @return the monom after derivative 
	 */
	public Monom derivative() throws DataException {
		int p = this.get_power();
		double c = this.get_coefficient();
		if(p==0)
			return null;
		return new Monom(p * c, p - 1);
	}
	
	/**
	 * Check if two monoms are equals such the power of both and the coefficient of both equals
	 * @param monom that we need to compare to this.monom
	 * @return true if equals and false otherwise
	 */
	public boolean equals(Monom m) {
		return (_coefficient == m._coefficient && _power == m._power);
	}

	/**
	 * Substract monom from other monom only if the power of both equal.
	 * What we do is to sub the coefficient of one monom from other monom.
	 * @param monom that we need to sub
	 * @throws DataException when the powers not equals
	 */
	public void substract(Monom m) throws DataException {
		if (_power == m._power){
			DecimalFormat df = new DecimalFormat("#.##");
			this.set_coefficient(Double.parseDouble(df.format(this.get_coefficient()-m.get_coefficient())));
		}
		else
			throw new DataException("Wrong! powers no equal");
	}
	
	/**
	 * Function for printing
	 * @return the string of the Polynom
	 */
	public String toString() {
		return this._coefficient + "x^" + this._power;
	}
	
	//private methods
	
	private void set_coefficient(double a) {
		this._coefficient = a;
	}
	
	private void set_power(int p) {
		this._power = p;
	}

}
