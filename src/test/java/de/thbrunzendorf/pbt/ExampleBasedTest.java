package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;

import org.junit.Test;

import de.thbrunzendorf.pbt.SquareRoot;

public class ExampleBasedTest {

	private static double TOLERANCE = 0.0000001;

	@Test
	public void squareRootOf0ShouldBe0() throws Exception {
		assertEquals(0, SquareRoot.of(0), TOLERANCE);
	}

	@Test
	public void squareRootOf1ShouldBe1() throws Exception {
		assertEquals(1, SquareRoot.of(1), TOLERANCE);
	}

	@Test
	public void squareRootOf4ShouldBe2() throws Exception {
		double input = 4;
		double output = SquareRoot.of(input);
		assertEquals(2, output, TOLERANCE);
	}

	@Test
	public void squareRootOf121ShouldBe11() throws Exception {
		assertEquals(11, SquareRoot.of(121), TOLERANCE);
	}

	@Test
	public void squareRootOf1Point21ShouldBe1Point1() throws Exception {
		assertEquals(1.1, SquareRoot.of(1.21), TOLERANCE);
	}

	@Test
	public void squareRootOf0Point25ShouldBe0Point5() throws Exception {
		assertEquals(0.5, SquareRoot.of(0.25), TOLERANCE);
	}

	@Test
	public void squareRootOf2ShouldBe1Point41421356() throws Exception {
		assertEquals(1.41421356, SquareRoot.of(2), TOLERANCE);
	}

	@Test
	public void squareRootOfMinus9ShouldBeUndefined() throws Exception {
		assertEquals(Double.NaN, SquareRoot.of(-9), TOLERANCE);
	}
}
