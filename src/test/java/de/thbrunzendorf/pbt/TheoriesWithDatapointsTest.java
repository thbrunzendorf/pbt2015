package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import de.thbrunzendorf.pbt.SquareRoot;
import de.thbrunzendorf.pbt.SquareRoot.Implementation;

@RunWith(Theories.class)
public class TheoriesWithDatapointsTest {

	private static double TOLERANCE = 0.0000001;

	@DataPoints
	public static double[] doubles() {
		return new double[] { 0, 1, 4, 121, 1.21, 0.25, 2, -9 };
	}

	@Theory
	public void squareRootIsNeverNegative(double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= 0);
	}

	@Theory
	public void forRadicandsGreaterEqualThan1SquareRootShouldBeLessEqualThanRadicand(
			double radicand) {
		assumeTrue(radicand >= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix <= radicand);
	}

	@Theory
	public void forPositiveRadicandsLessEqualThan1SquareRootShouldBeGreaterEqualThanRadicand(
			double radicand) {
		assumeTrue(radicand >= 0);
		assumeTrue(radicand <= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= radicand);
	}

	@Theory
	public void squareRootSquaredShouldBeEqualToRadicand(double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertEquals(radicand, radix * radix, TOLERANCE);
	}

	@Theory
	public void ownImplementationShouldBeSameSquareRootAsStandard(
			double radicand) {
		SquareRoot.setImplementation(Implementation.OWN);
		double ownRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, ownRadix, TOLERANCE);
	}

	@Theory
	public void naiveImplementationShouldBeSameSquareRootAsStandard(
			double radicand) {
		SquareRoot.setImplementation(Implementation.NAIVE);
		double naiveRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, naiveRadix, TOLERANCE);
	}
}
