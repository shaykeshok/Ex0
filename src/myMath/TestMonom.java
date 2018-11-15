package myMath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Junit to test Monom class
 * @author Shayke Shok and Itay Grinblat
 */
public class TestMonom {

	Monom m1;
	Monom m2;

	@Before
	public void setup() throws DataException {
		m1 = new Monom(4, 6);
		m2 = new Monom(-9, 6);
	}

	@Test
	public void testGetPower() {
		assertEquals("GetPower function is fail", 6, m1.get_power());
	}

	@Test
	public void testgetCoefficient() {
		assertEquals("GetCoefficient function is fail", 4, m1.get_coefficient(),0.01);
	}
	
	@Test
	public void testF() {
		double x = m1.f(1);
		assertEquals("f function is fail", 4, x,0.01);
	}
	
	@Test
	public void testAdd() throws DataException {
		m1.add(m2);
		Monom m3 = new Monom(-5,6);
		assertTrue("Add function is fail", m1.equals(m3));
	}
	
	@Test
	public void testMultiply() throws DataException {
		m1.multiply(m2);
		Monom m3 = new Monom(-36, 12);
		assertTrue("Multiply function is fail", m1.equals(m3));
	}
	
	@Test
	public void testDerivative() throws DataException {
		Monom m3 = m1.derivative();
		Monom m4 = new Monom(24, 5);
		assertTrue("Derivative function is fail", m3.equals(m4));
	}
	
	@Test
	public void testEquals() throws DataException {
		Monom m3 = new Monom(4, 6);
		assertTrue("Equals function is fail", m1.equals(m3));
	}
	
	@Test
	public void testSubstract() throws DataException {
		m1.substract(m2);
		Monom m3 = new Monom(13, 6);
		assertTrue("Substract function is fail", m1.equals(m3));
	}
	
}
