package de.thbrunzendorf.pbt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.contrib.theories.ParameterSignature;
import org.junit.contrib.theories.ParameterSupplier;
import org.junit.contrib.theories.ParametersSuppliedBy;
import org.junit.contrib.theories.PotentialAssignment;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import de.thbrunzendorf.pbt.SquareRoot.Implementation;

@RunWith(Theories.class)
public class TheoriesWithRandomParameterSupplierTest {

	private static double TOLERANCE = 0.0000001;
	private static double MAXIMUM = 1000000;
//	private static double MAXIMUM = Double.MAX_VALUE;


	public static class RandomDoubleSupplier extends ParameterSupplier {
		@Override
		public List<PotentialAssignment> getValueSources(
				ParameterSignature signature) throws Throwable {
			List<PotentialAssignment> list = new ArrayList<PotentialAssignment>();
			for (int i = 0; i < 10; i++) {
				double random = Math.random();
				double value = random * MAXIMUM;
				list.add(PotentialAssignment.forValue("", value));
			}
			return list;
		}
	}

	@Theory
	@Ignore
	public void naiveImplementationFailsWithRandomValues(
			@ParametersSuppliedBy(RandomDoubleSupplier.class) double radicand) {
		SquareRoot.setImplementation(Implementation.NAIVE);
		double naiveRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, naiveRadix, TOLERANCE);
	}

	@Theory
	@Ignore
	public void ownImplementationSucceedsWithRandomValues(
			@ParametersSuppliedBy(RandomDoubleSupplier.class) double radicand) {
		SquareRoot.setImplementation(Implementation.OWN);
		double ownRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, ownRadix, TOLERANCE);
	}

}
