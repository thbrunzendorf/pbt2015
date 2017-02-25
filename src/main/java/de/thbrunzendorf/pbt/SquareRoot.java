package de.thbrunzendorf.pbt;

public class SquareRoot {

	public enum Implementation {
		STANDARD, NAIVE, OWN
	}

	private static double TOLERANCE = 0.0000001;

	private static Implementation impl = Implementation.STANDARD;

	public static double of(double radicant) {
		if (impl == Implementation.STANDARD) {
			return standardImplementation(radicant);
		}
		if (impl == Implementation.NAIVE) {
			return naiveImplementation(radicant);
		}
		if (impl == Implementation.OWN) {
			return ownImplementation(radicant);
		}
		throw new RuntimeException("Unknown Implementation");
	}

	public static void setImplementation(Implementation implementation) {
		impl = implementation;
	}

	private static double standardImplementation(double radicant) {
		return Math.sqrt(radicant);
	}

	private static double naiveImplementation(double radicant) {
		if (radicant == 0) {
			return 0;
		}
		if (radicant == 1) {
			return 1;
		}
		if (radicant == 4) {
			return 2;
		}
		if (radicant == 121) {
			return 11;
		}
		if (radicant == 1.21) {
			return 1.1;
		}
		if (radicant == 0.25) {
			return 0.5;
		}
		if (radicant == 2) {
			return 1.41421356;
		}
		return Double.NaN;
	}

	private static double ownImplementation(double radicant) {
		if (radicant < 0) {
			return Double.NaN;
		}
		if (radicant == 0) {
			return 0;
		}
		if (radicant < 1) {
			return searchForSquareRootOfBetween(radicant, radicant, 1);
		}
		if (radicant == 1) {
			return 1;
		}
		return searchForSquareRootOfBetween(radicant, 1, radicant);
	}

	private static double searchForSquareRootOfBetween(double radicant, double lowerBound,
			double upperBound) {
		double radix = (lowerBound + upperBound) / 2;
		double delta = radix * radix - radicant;
		if (Math.abs(delta) < TOLERANCE) {
			return radix;
		}
		if (delta > 0) {
			return searchForSquareRootOfBetween(radicant, lowerBound, radix);
		}
		return searchForSquareRootOfBetween(radicant, radix, upperBound);
	}
}
