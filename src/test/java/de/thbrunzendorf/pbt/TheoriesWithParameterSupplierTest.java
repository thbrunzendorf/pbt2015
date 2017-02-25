package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.contrib.theories.ParameterSignature;
import org.junit.contrib.theories.ParameterSupplier;
import org.junit.contrib.theories.ParametersSuppliedBy;
import org.junit.contrib.theories.PotentialAssignment;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import de.thbrunzendorf.pbt.SquareRoot;
import de.thbrunzendorf.pbt.SquareRoot.Implementation;

@RunWith(Theories.class)
public class TheoriesWithParameterSupplierTest {

	private static double TOLERANCE = 0.0000001;

	public static class DoubleSupplier extends ParameterSupplier {
		@Override
		public List<PotentialAssignment> getValueSources(
				ParameterSignature signature) throws Throwable {
			List<PotentialAssignment> list = new ArrayList<PotentialAssignment>();
			list.add(PotentialAssignment.forValue("", 0));
			list.add(PotentialAssignment.forValue("", 1));
			list.add(PotentialAssignment.forValue("", 4));
			list.add(PotentialAssignment.forValue("", 121));
			list.add(PotentialAssignment.forValue("", 1.21));
			list.add(PotentialAssignment.forValue("", 0.25));
			list.add(PotentialAssignment.forValue("", 2));
			list.add(PotentialAssignment.forValue("", -9));
			return list;
		}
	}

	@Theory
	public void squareRootIsNeverNegative(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= 0);
	}

	@Theory
	public void forRadicandsGreaterEqualThan1SquareRootShouldBeLessEqualThanRadicand(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		assumeTrue(radicand >= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix <= radicand);
	}

	@Theory
	public void forPositiveRadicandsLessEqualThan1SquareRootShouldBeGreaterEqualThanRadicand(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		assumeTrue(radicand >= 0);
		assumeTrue(radicand <= 1);
		double radix = SquareRoot.of(radicand);
		assertTrue(radix >= radicand);
	}

	@Theory
	public void squareRootSquaredShouldBeEqualToRadicand(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertEquals(radicand, radix * radix, TOLERANCE);
	}

	@Theory
	public void ownImplementationShouldBeSameSquareRootAsStandard(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		SquareRoot.setImplementation(Implementation.OWN);
		double ownRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, ownRadix, TOLERANCE);
	}

	@Theory
	public void naiveImplementationShouldBeSameSquareRootAsStandard(
			@ParametersSuppliedBy(DoubleSupplier.class) double radicand) {
		SquareRoot.setImplementation(Implementation.NAIVE);
		double naiveRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, naiveRadix, TOLERANCE);
	}

}
