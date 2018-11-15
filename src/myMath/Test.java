package myMath;

import java.util.Iterator;
import java.util.Set;
/**
 * test class
 * @author Shayke Shok and Itay Grinblat
 */

public class Test {

	public static void main(String[] args) throws DataException, RootException {

		System.out.println("\n\n######test######\n");
		test();
	}
		
	public static void test() throws DataException, RootException {

		Polynom_able p1 = new Polynom("2x^1+-3x^2+1x^3");
		Polynom_able p2 = new Polynom("-0.2x^0+2.1x^1+-1x^3");
		System.out.println("first p1: " + p1);
		System.out.println("first p2: " + p2);

		// add
		p1.add(p2);
		// System.out.println("get \"-0.2+4.1x-3x^2\":");
		System.out.println("p1.add(p2): " + p1);
		System.out.println("first p2: " + p2);
		// substract
		p1.substract(p2);

		// System.out.println("get \"2x^3+-3x^2-1x^1\":");
		System.out.println("p1.substract(p2): " + p1);
		System.out.println("first p2555: " + p2);

		// multiply
		Polynom_able mul = p1.copy();
		System.out.println("copy of p1: " + mul);
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		mul.multiply(p2);
		// System.out.println("get \"-0.4x+4.8x^2-6.5x^3+0.1x^4+3x^5-x^6\":");
		System.out.println("mul.multiply(p2): " + mul);

		// derivative
		System.out.println("p1: " + p1);
		// System.out.println("get \"2-6x+3x^2\":");
		System.out.println("p1.derivative: " + p1.derivative());

		System.out.println("p2: " + p2);
		// System.out.println("get \"2.1-3x^2\":");
		System.out.println("p2.derivative: " + p2.derivative());

		// f(x)
		System.out.println("p1.f: " + p1.f(3));
		System.out.println("p2.f: " + p2.f(3));

		// root and area
		double eps = Double.MIN_VALUE;
		System.out.println("\nroot\n");

		// root p1
		System.out.println("get something close to 0:" + p1.root(-0.5, 0.5, eps));
		System.out.println("get something close to 1:" + p1.root(0.5, 1.5, eps));
		System.out.println("get something close to 2:" + p1.root(1.5, 2.5, eps));

		// root p3
		Polynom_able p3 = new Polynom("2x^3-7x^2+5x^0");
		System.out.println("get something close to 1:" + p3.root(-0.5, 3, eps));

		eps = 0.00001;
		System.out.println("\narea\n");
		// area

		System.out.println("get something close to -0.25:" + p1.area(0, 1, eps));
		System.out.println("get something close to 0:" + p1.area(0.5, 1.5, eps));
		System.out.println("get something close to -0.140625:" + p1.area(0.5, 2, eps));

		System.out.println("get something close to 0.6:" + p2.area(0, 1, eps));

		// zero test
		Polynom p4 = new Polynom();
		p4.add(new Monom(0.2, 0));
		p4.add(new Monom(-0.1, 0));
		p4.add(new Monom(-0.1, 0));
		System.out.println(p4);

		// equals 2 test
		System.out.println("get true:" + p1.toString().equals(p2.toString()));

		// Iterator test
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()) {
			System.out.print(iterator.next().get_power() + ", ");

		}
		
		//plot test
		Polynom p=new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5x^0");
		p.funcPlot();
		
		//area under x line test
		System.out.println("area under x line: "+p.areaUnderX(-2, 6,0.01));
		
		//extrem points test
		Set<Double> set=p.extremPoints(-2, 6, 0.01);
		System.out.println(set);
	}
}
