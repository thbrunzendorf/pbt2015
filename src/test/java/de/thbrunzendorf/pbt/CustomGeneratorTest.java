package de.thbrunzendorf.pbt;

import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.From;

@RunWith(Theories.class)
public class CustomGeneratorTest {

	@Theory
	public void generateCustomData(
			@ForAll @From(CustomDataGenerator.class) CustomData data) {
		System.out.println(data.getItem() + "/" + data.getCount());
	}

}
