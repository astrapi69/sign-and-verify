package de.alpharogroup.sign;

import com.google.gson.Gson;
import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.NotSerializable;
import de.alpharogroup.test.objects.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.security.PrivateKey;

import static org.junit.jupiter.api.Assertions.*;

class JsonSignerTest
{

	/**
	 * Test method for {@link JsonSigner#sign(Object)}
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
		NotSerializable notSerializable;
		JsonSigner<NotSerializable> signer;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		SignatureBean bean = SignatureBean.builder().privateKey(privateKey)
			.signatureAlgorithm(signatureAlgorithm).build();
		signer = new JsonSigner<NotSerializable>(bean, new Gson());

		// new scenario
		notSerializable = NotSerializable.builder().build();
		actual = signer.sign(notSerializable);
		expected = "nknPMqJM/lei5ixfDsk4Rv/2/ZwPC9fd/6GWyqwUHgNQFW8Y2pXC7aG0KkM8Iv9csj4uppWZvXLHWoIE09x31SbkxKs4NLSfKa5KZcbeiq/X8mHLLnGNay9T3bbdDfKrqPuizrqrz5XXRfK3rKuHMkTmDtMlIlhMsBawNbWXiIOgBVE3w7YHfFEH3IiauZGiTk0vmMwa9/719NG+1bh1CLgYXE2eOJMvEUT5jXsJtsWqblTNt9Xzk4OSlGYdsV2JZhunSYEOan7dBIAm0m36gWyhxU+bic/LDrD6VsdUAy3C4Hub6ofjPRcdFosqSscUoOSFkP5cdGbG6h6dAotsxQ==";
		assertEquals(actual, expected);

		// new scenario
		notSerializable = NotSerializable.builder().name("bla").build();
		actual = signer.sign(notSerializable);
		expected = "eJA5uNEeQ49Wy34XCjv0p9DbGAq2yEY5kX9nAB3uqx3nktJMsh//Q1Ri+cHtLcRMCKbqHr1TWR1b0JFvVwVUSzdJcFg1diDrVI3VjJPOVVeygOjF1xXFZ0QSwGNiMoUlzLfGkD6Q7BHTC10hzRczn/uxMYDtXZdgZKGvLVDzsWT3HHneR+ZROLJ3jwQtIOntaQbGM6X3YVmRD9Pj/hDbkBhJX0nu6K55H20vYOKfO6PyePZTUfztUZ5ITiAwyjOhGM4tqkFuuQccr57KdRb7ltvRajX3BZa1GAjodVPOWzOUX+obB3ULtwJHqnjZ474cEUA6PqQ28tVkY6x+ZLVfzQ==";
		assertEquals(actual, expected);
	}
}
