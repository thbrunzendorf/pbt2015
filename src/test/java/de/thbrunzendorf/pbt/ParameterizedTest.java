package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.thbrunzendorf.pbt.SquareRoot;

@RunWith(Parameterized.class)
public class ParameterizedTest {

	private static double TOLERANCE = 0.0000001;

	private double radicand;
	private double radix;

	public ParameterizedTest(double radicand, double radix) {
		super();
		this.radicand = radicand;
		this.radix = radix;
	}

	@Parameters
	public static Object[][] data() {
		return new Object[][] { { 0, 0 }, { 1, 1 }, { 4, 2 }, { 121, 11 },
				{ 1.21, 1.1 }, { 0.25, 0.5 }, { 2, 1.41421356 },
				{ -9, Double.NaN } };
	}

	@Test
	public void squareRootOfShouldBe() throws Exception {
		assertEquals(radix, SquareRoot.of(radicand), TOLERANCE);
	}
}
