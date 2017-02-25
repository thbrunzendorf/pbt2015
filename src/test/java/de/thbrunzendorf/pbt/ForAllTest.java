package de.thbrunzendorf.pbt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.ValuesOf;

import de.thbrunzendorf.pbt.SquareRoot.Implementation;

@RunWith(Theories.class)
public class ForAllTest {

	private static double TOLERANCE = 0.0000001;

	@Theory
	public void constrainingIntValues(
			@ForAll @InRange(minInt = 0, maxInt = 9) int number) {
		System.out.println(number);
	}

	@Theory
	public void constrainingIntValuesInLists(
			@ForAll @InRange(minInt = 0, maxInt = 9) List<Integer> list) {
		String listAsString = "";
		for (Integer integer : list) {
			listAsString += integer + ";";
		}
		System.out.println(list.size() + " / " + listAsString);
	}

	@Theory
	public void constrainingDateValues(
			@ForAll @InRange(min = "01.01.2015", max = "31.12.2019", format = "dd.MM.yyyy") Date date) {
		DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
		System.out.println(sdf.format(date));
	}

	enum Ternary {
		YES, NO, MAYBE
	}

	@Theory
	public void coveringAllValuesOfBooleanAndEnums(@ForAll @ValuesOf boolean b,
			@ForAll @ValuesOf Ternary t) {
		System.out.println(b + "/" + t);
	}

	@Theory
	public void limitingSampleSize(@ForAll(sampleSize = 10) int number) {
		System.out.println(number);
	}

	@Theory
	public void moreThanOneArgument(@ForAll(sampleSize = 10) String s1,
			@ForAll(sampleSize = 10) String s2) {
		System.out.println(s1 + "/" + s2);
		assertEquals(s1.length() + s2.length(), (s1 + s2).length());
	}

	@Theory
	public void squareRootSquaredShouldBeEqualToRadicand(
			@ForAll(sampleSize=100) @InRange(minDouble = 0.0, maxDouble = 1000000) double radicand) {
		System.out.println(radicand);
		assumeTrue(radicand >= 0);
		double radix = SquareRoot.of(radicand);
		assertEquals(radicand, radix * radix, TOLERANCE);
	}

	@Theory
	public void ownImplementationSucceedsWithRandomValues(
			@ForAll(sampleSize=100) @InRange(minDouble = 0.0, maxDouble = 1000000) double radicand) {
		System.out.println(radicand);
		SquareRoot.setImplementation(Implementation.OWN);
		double ownRadix = SquareRoot.of(radicand);
		SquareRoot.setImplementation(Implementation.STANDARD);
		double standardRadix = SquareRoot.of(radicand);
		assertEquals(standardRadix, ownRadix, TOLERANCE);
	}
}
