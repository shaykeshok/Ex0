package myMath;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Shayke Shok and Itay Grinblat
 */
public class Polynom implements Polynom_able {

	private List<Monom> monomList;

	/**
	 * Empty constructor
	 */
	public Polynom() {
		monomList = new ArrayList<Monom>();
	}

	/**
	 * Constructor that get a polynom and copy him to this Polynom
	 * 
	 * @param polynom
	 */
	public Polynom(Polynom p) {
		Iterator<Monom> it = p.iteretor();
		monomList = new ArrayList<Monom>();
		Monom m = null;
		while (it.hasNext()) {
			m = it.next();
			monomList.add(m);
		}
	}

	/**
	 * Constructor that get a string and make from it a Polynom We get only
	 * format like it: a1x^b1+a2x^b2+a3x^b3...
	 * 
	 * @param string
	 *            of the polynom
	 * @throws DataException
	 *             when the data is incorrect
	 */
	public Polynom(String s) throws DataException {
		monomList = new ArrayList<Monom>();
		int len = s.length();
		if (len == 0)
			throw new DataException("The length of the String must be big then zero");
		double a = 0;
		int b = 0;
		char c = ' ';
		String str = "";
		boolean needPluse = false;

		for (int i = 0; i < len; i++) {
			c = s.charAt(i);

			// if the first monom is negetive
			if (i == 0 && c == '-')
				str += c;
			else {
				if (c != 'x' && c != 'X' && c != '+' && c != '-' && c != '^') {
					if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.')
						str += c;
					else
						throw new DataException("invalid char at " + i + " position of the string");
				} else {
					if (c == 'x' || c == 'X') {
						a = Double.parseDouble(str);
						str = "";
					}

					// check if there is '+' and '+' after him or '-' and '+'
					// after him. if yes we
					// throws him
					else if (((c == '+') && s.charAt(i + 1) == '+') || ((c == '-') && s.charAt(i + 1) == '+'))
						throw new DataException("invalid chars. cannot get '+' and '+' immidately after it. format like this is not good: a1x^b1++a2x^b");

					// check if there is '+' or '-' and number after them
					else if ((c == '+') && s.charAt(i + 1) != '-' || ((c == '-') && s.charAt(i + 1) != '-')) {
						if (a == Double.MIN_VALUE)
							throw new DataException("invalid string format");
						b = Integer.parseInt(str);
						if (needPluse) {
							c = '+';
							needPluse = false;
						}
						str = c + "";
						monomList.add(new Monom(a, b));
						a = Double.MIN_VALUE;
						b = Integer.MIN_VALUE;

						// check if there is '-' and after him another '-'. we
						// replace them in '+'
					} else if ((c == '-') && s.charAt(i + 1) == '-')
						needPluse = true;

				}
			}
		}

		b = Integer.parseInt(str);
		monomList.add(new Monom(a, b));
		Comparator<Monom> cmpByPower = new Monom_Comperator();
		monomList.sort(cmpByPower);

		// check if there is monom with coefficient equal to zero and remove him
		Polynom p = new Polynom(this);
		int iRemove = 0;
		for (Monom monom : monomList) {
			if (monom.get_coefficient() == 0)
				p.monomList.remove(iRemove);
			iRemove++;

		}

	}

	/**
	 * The function returns a iterator
	 * 
	 * @return an iterator
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> mIter = monomList.iterator();
		return mIter;
	}

	/**
	 * The function calculates the value of f(x) of the this polynom in x point
	 * 
	 * @param x
	 *            is the point value to be calculated for get y
	 * @return the y of the polynom in the x point
	 */
	@Override
	public double f(double x) {
		double ans = 0;
		for (Monom monom : monomList) {
			ans += monom.f(x);
		}
		return ans;
	}

	/**
	 * The function gets a polynom add him to another polynom
	 * 
	 * @param p1
	 *            the polynom that we need to add
	 * @throws DataException
	 */
	@Override
	public void add(Polynom_able p1) throws DataException {
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Monom m = new Monom(it.next());
			this.add(m);
		}
	}

	/**
	 * The function get a monom and add it to the polynom if the monom already
	 * exists we add the coefficient to the existing monom if the sum of the
	 * coefficient of the two monoms is equal to zero we remove it from the
	 * polynom
	 * 
	 * @param m
	 *            is the monom to add
	 * @throws DataException
	 */
	@Override
	public void add(Monom m) throws DataException {

		Comparator<Monom> cmpByPower = new Monom_Comperator();
		boolean needSort = true;
		int i = 0;
		int index = -1;
		for (Monom monom : monomList) {

			if (monom.get_power() - m.get_power() == 0) {
				if (monom.get_coefficient() + m.get_coefficient() == 0)
					index = i;
				monom.add(m);
				needSort = false;
			}
			i++;
		}
		if (needSort) {
			monomList.add(m);
			monomList.sort(cmpByPower);
		}
		if (index != (-1))
			monomList.remove(index);
	}

	/**
	 * The function get polynom and sub him from the exist polynom
	 * 
	 * @param p1
	 *            the polynom we get to subtract from the exist polynom
	 * @throws DataException
	 */
	@Override
	public void substract(Polynom_able p1) throws DataException {
		Polynom p1New = new Polynom((Polynom) p1);
		for (Monom monom : p1New.monomList) {
			substract(monom);
		}
	}

	/**
	 * The function get a monom and sub it from the polynom if there is monom
	 * already exists with power equal to this power monom we sub the
	 * coefficient to the existing monom if the sum of the coefficient of the
	 * two monoms is equal to zero we remove it from the polynom
	 * 
	 * @param m
	 *            is the monom to sub
	 * @throws DataException
	 */
	public void substract(Monom m) throws DataException {
		Comparator<Monom> cmpByPower = new Monom_Comperator();
		boolean needSort = true;
		int index = -1;
		int i = 0;
		for (Monom monom : this.monomList) {
			if (monom.get_power() - m.get_power() == 0) {
				if (monom.get_coefficient() - m.get_coefficient() == 0)
					index = i;
				monom.substract(m);
				needSort = false;
			}
			i++;
		}
		if (needSort) {
			Monom mNew = new Monom(m.get_coefficient() * (-1), m.get_power());
			this.monomList.add(mNew);
			this.monomList.sort(cmpByPower);
		}
		if (index != (-1))
			this.monomList.remove(index);
	}

	/**
	 * The function get a polynom and multiply it with the exist polynom
	 * 
	 * @param p1
	 *            is the polynom to multiply with the exist polynom
	 * @throws DataException
	 */
	@Override
	public void multiply(Polynom_able p1) throws DataException {
		Polynom pNew = new Polynom();
		Iterator<Monom> it = null;

		for (Monom monom : this.monomList) {
			it = p1.iteretor();
			while (it.hasNext()) {
				Monom m1 = new Monom(it.next());
				Monom m2 = new Monom(monom);
				m2.multiply(m1);
				pNew.add(m2);
			}
		}
		monomList.clear();
		for (Monom monom : pNew.monomList) {
			this.add(monom);
		}
	}

	/**
	 * The function get a polynom and get over any monoms of him and check if
	 * the monoms are equals to the monoms in the existing polynom we iterate
	 * through both and check every monom
	 * 
	 * @param p1
	 *            is the polynom to check if it is equal to the existing polynom
	 * @return true if the polynoms are equal
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		for (Monom monom : monomList) {
			if (it.hasNext()) {
				Monom m = it.next();
				if (!monom.equals(m))
					return false;
			} else
				return false;
		}
		if (it.hasNext())
			return false;
		return true;
	}

	/**
	 * The function checks if all of the coefficient in the polynom are equal to
	 * zero
	 * 
	 * @return true if all of the coefficient are equal to zero
	 */
	@Override
	public boolean isZero() {
		return monomList.isEmpty();
	}

	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps assuming
	 * (f(x0)*f(x1)<=0, returns f(x2) such that: * (i) x0<=x2<=x2 && (ii)
	 * f(x2)<eps
	 * 
	 * @param x0
	 *            starting point
	 * @param x1
	 *            end point
	 * @param eps
	 *            step (positive) value
	 * @return the root
	 * @throws DataException
	 * @throws RootException
	 */
	@Override
	public double root(double Xleft, double Xright, double eps) throws DataException, RootException {

		double fXleft = this.f(Xleft);
		double fXright = this.f(Xright);
		double interval = Xright - Xleft;
		double Xmid, fXmid;

		// check the input
		if (fXright * fXleft > 0 || eps < 0) {
			throw new DataException("The input is invalid. The values of your x's isnt opposite");
		}

		while (Math.abs(fXleft) >= eps && Math.abs(fXright) >= eps) {
			interval = interval / 2;
			if (interval == 0) {
				System.out.println("problem with f(x)");
				throw new RootException("There isnt root");
			}

			Xmid = Xleft + interval;
			fXmid = this.f(Xmid);

			if (fXmid * fXleft > 0) {
				Xleft = Xmid;
				fXleft = fXmid;
			} else {
				Xright = Xmid;
				fXright = fXmid;
			}
		}

		if (Math.abs(fXleft) < eps)
			return Xleft;
		else
			return Xright;

	}

	/**
	 * create a deep copy of this Polynum
	 * 
	 * @return new Polynom
	 * @throws DataException
	 */
	@Override
	public Polynom_able copy() throws DataException {
		Polynom pNew = new Polynom();
		for (Monom monom : monomList) {
			Monom m = new Monom(monom);
			pNew.add(m);
		}
		return pNew;
	}

	/**
	 * The function compute the derivative of this polynom
	 * 
	 * @return new polynom that he is the derivative of this polynom
	 * @throws DataException
	 */
	@Override
	public Polynom_able derivative() throws DataException {
		Polynom pNew = new Polynom();
		Monom m = null;
		for (Monom monom : monomList) {
			m = monom.derivative();
			if (m != null)
				pNew.add(m);
		}
		return pNew;
	}

	/**
	 * The function uses Riemann's Integral to compute the area above the x line
	 * 
	 * @param x0
	 *            is one point on x line
	 * @param x1
	 *            is the second point on x line
	 * @param eps
	 *            is the step value to calculate the area
	 * @return sum of the area
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double val = 0;
		if (x0 > x1) {
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}
		val = this.f(x0);

		int sum = 0;
		while (val > 0) {
			sum += val * eps;
			x0 = x0 + eps;
			val = this.f(x0);
		}
		val = this.f(x1);
		while (val > 0) {
			sum += val * eps;
			x1 = x1 - eps;
			val = this.f(x1);
		}
		return sum;
	}

	/**
	 * The function returns the polynom as a string
	 * 
	 * @return the string of the polynom
	 */
	@Override
	public String toString() {
		String s = "";
		for (Monom monom : monomList) {
			if (monom.toString().charAt(0) != '-' && !s.isEmpty())
				s += "+";
			s += monom.toString();
		}
		return s;
	}

	/**
	 * Finds the extream points of the function
	 * @param xL - start point     
	 * @param xR - end point    
	 * @return -Set of the points that suspected to be extream points in the
	 *         function
	 */
	public Set<Double> extremPoints(double xL, double xR, double eps) throws DataException, RootException {
		Polynom_able pNigzeret = this.derivative();
		Set<Double> set = new HashSet<>();
		for (double x = xL; x <= xR; x += 0.01) {

			DecimalFormat df = new DecimalFormat("#.##");
			double xAfter = x + 0.01;
			double fx = pNigzeret.f(x);
			double fXAfter = pNigzeret.f(xAfter);
			if (fx * fXAfter <= 0) {
				double pointX = 0;
				pointX = pNigzeret.root(x, xAfter, 0.01);
				double pointY = this.f(pointX);
				set.add((Double.parseDouble(df.format(pointX))));
				System.out.println("(" + (Double.parseDouble(df.format(pointX))) + ", " + (Double.parseDouble(df.format(pointY))) + ")");
				x = pointX;
			}
		}
		return set;
	}

	/**
	 * Calculate the area under X line with Riemann's Integral
	 * @param x0 starting point         
	 * @param x1 end point    
	 * @return sum - area of the function under x line
	 */
	public double areaUnderX(double x0, double x1, double eps) {

		double sum = 0;
		double val = 0;
		
		if (x0 > x1) {
			double temp = x0;
			x0 = x1;
			x1 = x0;
		}
		if (x0 == x1)
			return 0;
		while (x0 < x1) {
			x0 += eps;
			val = this.f(x0);
			if (val < 0)
				sum += val * eps;

		}
		return Math.abs(sum);

	}
/**
 * function for draw the polynom
 * @throws DataException
 * @throws RootException
 */
	public void funcPlot() throws DataException, RootException {
		LinePlotTest frame = new LinePlotTest(-2,6,this);
		frame.setVisible(true);
	}

}
