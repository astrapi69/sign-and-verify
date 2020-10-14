package de.alpharogroup.sign;

import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.security.PrivateKey;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test class for the class {@link ObjectSigner}
 */
public class ObjectSignerTest
{

	/**
	 * Test method for {@link Signer#sign(byte[])}
	 *
	 * @throws Exception
	 *             is thrown if any security exception occured
	 */
	@Test
	public void testSign() throws Exception
	{
		String actual;
		String expected;
		String signatureAlgorithm;
		File publickeyDerDir;
		File privatekeyDerFile;
		PrivateKey privateKey;
		Person person;
		ObjectSigner<Person> signer;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		SignatureBean bean = SignatureBean.builder().privateKey(privateKey)
			.signatureAlgorithm(signatureAlgorithm).build();
		signer = new ObjectSigner<Person>(bean);

		// new scenario
		person = Person.builder().build();
		actual = signer.sign(person);
		expected = "5SjzRihJy8yqQvOnY+S8in381lfy8pyTYXj/5PqMC6Wpg29/4gTEZeZzMvhVwUCjo/y9Fj842AEQqSmi3qoc742V5JOY5KVSxIovM7BlWVE0Mbzs2dv5ulYL40gCmoREWt7AozxfRxv8yMbosF5jvHstfbeNGIg8DFDvq0SSmN5aPr2IC3q491J/y0y7TG474G1inUyE/o5UzYcHuZWSFIoY2tz4rsfOnlA60HUp1DxwLEdn8YN1qhCCpuqeCjoIFzmeijxLAeKF9Nhxl54klQqMfodwI03zGdy4X4Yilb+wHq7rLMEcv/yYaFPjBbyn1PWd9sKa3/6rNBfmokumPQ==";
		assertEquals(actual, expected);

		// new scenario
		person = Person.builder().about("foo").name("Arnold").build();
		actual = signer.sign(person);
		expected = "PzCM82sh2sWAapzvNOB3fNJdCIkSMS2wqTYcIayMB/J8pewXQCko8FznSrDg0yNut4wNiPQD1bxuva3qNCbEX3CQ//P57I012Fy/92hv9K+ALQeVqftww6gv9RJWyD6BLK9CoEPQ0MFR9ISD2WVzJGxazMwiruFm0Gky0zZEX1M5ycJw0cpj7PVH2fyJ4Z/ogY6kQVMfRqVhiKMfH/UzDxjVn2NUyubqKYUg3ylCk8MKv3f/OEavTaVAW12Vi9C/dcOdF75ahzLrT4rWGSDMgZkqtWtGE4In/vFRGACG40M/QT0WpuubcWrN5lHh+o8nDWBb6FJuaHEKjwiWQ6PYuw==";
		assertEquals(actual, expected);

	}
}
