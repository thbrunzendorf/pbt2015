package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.Ignore;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.generator.InRange;

import de.thbrunzendorf.pbt.SquareRoot;
import de.thbrunzendorf.pbt.SquareRoot.Implementation;

@RunWith(Theories.class)
public class TheoriesWithForAllTest {

	private static double TOLERANCE = 0.0000001;

	@Theory
	public void squareRootIsNeverNegative(@ForAll double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= 0);
	}

	@Theory
	public void forRadicandsGreaterEqualThan1SquareRootShouldBeLessEqualThanRadicand(
			@ForAll @InRange(minDouble = 1.0, maxDouble = 999999.999) double radicand) {
		System.out.println(radicand);
		// assumeTrue(radicand >= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix <= radicand);
	}

	@Theory
	public void forPositiveRadicandsLessEqualThan1SquareRootShouldBeGreaterEqualThanRadicand(
			@ForAll double radicand) {
		System.out.println(radicand);
		assumeTrue(radicand >= 0);
		assumeTrue(radicand <= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= radicand);
	}

	@Theory
	public void squareRootSquaredShouldBeEqualToRadicand(@ForAll double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertEquals(radicand, radix * radix, TOLERANCE);
	}

	@Theory
	@Ignore
	public void naiveImplementationShouldBeSameSquareRootAsStandard(
			@ForAll double radicand) {
		SquareRoot.setImplementation(Implementation.NAIVE);
		double naiveRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, naiveRadix, TOLERANCE);
	}

	@Theory
	@Ignore
	public void ownImplementationShouldBeSameSquareRootAsStandard(
			@ForAll double radicand) {
		SquareRoot.setImplementation(Implementation.OWN);
		double ownRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, ownRadix, TOLERANCE);
	}
}

