package myMath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Junit to test Polynom class
 * @author Shayke Shok and Itay Grinblat
 */
public class TestPolynom {

	Polynom_able p1;
	Polynom_able p2;

	@Before
	public void setup() throws DataException {
		p1 = new Polynom("5x^8-9x^4+3x^2");
		p2 = new Polynom("-4x^3+10x^0");
	}

	@Test
	public void testF() {
		double x = p1.f(2);
		assertEquals("f function is fail",1148.0, x,0.01);
	}

	@Test
	public void testAddPolynom_able() throws DataException {
		p1.add(p2);
		System.out.println(p1);
		Polynom_able p3 = new Polynom("5x^8-9x^4-4x^3+3x^2+10x^0");
		assertTrue("AddPolynom function is fail", p1.equals(p3));

	}

	@Test
	public void testAddMonom() throws DataException {
		Monom m = new Monom(6, 4);
		p1.add(m);
		Polynom_able p3 = new Polynom("5x^8-3x^4+3x^2");
		assertTrue("AddMonom function is fail", p1.equals(p3));
	}

	@Test
	public void testSubstract() throws DataException {
		p1.substract(p2);
		Polynom_able p3 = new Polynom("5x^8-9x^4+4x^3+3x^2-10x^0");
		assertEquals("Substract function is fail", true, p1.equals(p3));
	}

	@Test
	public void testMultiply() throws DataException {
		p1.multiply(p2);
		Polynom_able p3 = new Polynom("-20x^11+50x^8+36x^7-12x^5-90x^4+30x^2");
		assertTrue("Multiply function is fail", p1.equals(p3));
	}

	@Test
	public void testEqualsPolynom_able() {
		assertEquals("Polynoms aren't equals", false, p2.equals(p1));
	}
	
	@Test
	public void testIsZero() {
		assertEquals("IsZero function is fail", false, p1.isZero());
	}
	
	@Test
	public void testRoot() throws DataException, RootException {
		double x = p1.root(-100, 1, 0.01);
		assertEquals("Root function is fail", 0.59930419921875, x,0.01);
	}
	
	@Test
	public void testDerivative() throws DataException {
		p1.derivative();
		Polynom_able p3 = new Polynom("5x^8-9x^4+3x^2");
		assertEquals("Derivative function is fail", true, p1.equals(p3));
	}
	
	@Test
	public void testArea() {
		double x = p1.area(0, 3, 0.6);
		assertEquals("Area function is fail", 22677.0, x,0.01);
	}
	
	@Test
	public void testAreaUnderX() throws DataException {
		Polynom p4 = new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5x^0");
		double x = p4.areaUnderX(-2, 6, 0.01);
		assertEquals("testArea is wrong", 25.183633821940276, x, 0.1);
	}

}
