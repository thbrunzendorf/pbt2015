package de.thbrunzendorf.pbt;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.java.lang.IntegerGenerator;
import com.pholser.junit.quickcheck.generator.java.lang.StringGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class CustomDataGenerator extends Generator<CustomData> {

	public CustomDataGenerator(Class<CustomData> type) {
		super(type);
	}

	private final StringGenerator stringGenerator = new StringGenerator();
	private final IntegerGenerator integerGenerator = new IntegerGenerator();

	@Override
	public CustomData generate(SourceOfRandomness random,
			GenerationStatus status) {
		String item = stringGenerator.generate(random, status);
		int count = integerGenerator.generate(random, status);
		return new CustomData(item, count);
	}
}
