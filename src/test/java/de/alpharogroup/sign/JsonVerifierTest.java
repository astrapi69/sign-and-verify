/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.sign;

import com.google.gson.Gson;
import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.crypto.key.reader.PublicKeyReader;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.NotSerializable;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The unit test class for the class {@link JsonVerifier}
 */
public class JsonVerifierTest
{

	/**
	 * Test method for {@link JsonVerifier#verify(Object, String)}  with a certificate
	 *
	 * @throws Exception is thrown if any security exception occured
	 */
	@Test public void testVerifyWithCertificate() throws Exception
	{
		boolean actual;
		boolean expected;
		NotSerializable notSerializable;
		String signature;
		String signatureAlgorithm;
		File publickeyDerDir;
		File publickeyDerFile;
		File privatekeyDerFile;
		PrivateKey privateKey;
		PublicKey publicKey;
		Certificate certificate;
		JsonVerifier<NotSerializable> verifier;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		certificate = TestObjectFactory
			.newCertificateForTests(publicKey, privateKey, signatureAlgorithm);

		VerifyBean verifyBean = VerifyBean.builder().certificate(certificate)
			.signatureAlgorithm(signatureAlgorithm).build();

		verifier = new JsonVerifier<>(verifyBean, new Gson());

		// new scenario
		signature = "nknPMqJM/lei5ixfDsk4Rv/2/ZwPC9fd/6GWyqwUHgNQFW8Y2pXC7aG0KkM8Iv9csj4uppWZvXLHWoIE09x31SbkxKs4NLSfKa5KZcbeiq/X8mHLLnGNay9T3bbdDfKrqPuizrqrz5XXRfK3rKuHMkTmDtMlIlhMsBawNbWXiIOgBVE3w7YHfFEH3IiauZGiTk0vmMwa9/719NG+1bh1CLgYXE2eOJMvEUT5jXsJtsWqblTNt9Xzk4OSlGYdsV2JZhunSYEOan7dBIAm0m36gWyhxU+bic/LDrD6VsdUAy3C4Hub6ofjPRcdFosqSscUoOSFkP5cdGbG6h6dAotsxQ==";

		notSerializable = NotSerializable.builder().build();
		actual = verifier.verify(notSerializable, signature);
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);

		// new scenario
		signature = "eJA5uNEeQ49Wy34XCjv0p9DbGAq2yEY5kX9nAB3uqx3nktJMsh//Q1Ri+cHtLcRMCKbqHr1TWR1b0JFvVwVUSzdJcFg1diDrVI3VjJPOVVeygOjF1xXFZ0QSwGNiMoUlzLfGkD6Q7BHTC10hzRczn/uxMYDtXZdgZKGvLVDzsWT3HHneR+ZROLJ3jwQtIOntaQbGM6X3YVmRD9Pj/hDbkBhJX0nu6K55H20vYOKfO6PyePZTUfztUZ5ITiAwyjOhGM4tqkFuuQccr57KdRb7ltvRajX3BZa1GAjodVPOWzOUX+obB3ULtwJHqnjZ474cEUA6PqQ28tVkY6x+ZLVfzQ==";

		notSerializable = NotSerializable.builder().name("bla").build();
		actual = verifier.verify(notSerializable, signature);
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link JsonVerifier#verify(Object, String)} with public key
	 *
	 * @throws Exception is thrown if any security exception occured
	 */
	@Test public void testVerifyWithPublicKey() throws Exception
	{
		boolean actual;
		boolean expected;
		NotSerializable notSerializable;
		String signature;
		String signatureAlgorithm;
		File publickeyDerDir;
		File publickeyDerFile;
		PublicKey publicKey;
		JsonVerifier<NotSerializable> verifier;
		VerifyBean verifyBean;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");

		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		verifyBean = VerifyBean.builder().publicKey(publicKey)
			.signatureAlgorithm(signatureAlgorithm).build();

		verifier = new JsonVerifier<>(verifyBean, new Gson());
		// new scenario
		signature = "nknPMqJM/lei5ixfDsk4Rv/2/ZwPC9fd/6GWyqwUHgNQFW8Y2pXC7aG0KkM8Iv9csj4uppWZvXLHWoIE09x31SbkxKs4NLSfKa5KZcbeiq/X8mHLLnGNay9T3bbdDfKrqPuizrqrz5XXRfK3rKuHMkTmDtMlIlhMsBawNbWXiIOgBVE3w7YHfFEH3IiauZGiTk0vmMwa9/719NG+1bh1CLgYXE2eOJMvEUT5jXsJtsWqblTNt9Xzk4OSlGYdsV2JZhunSYEOan7dBIAm0m36gWyhxU+bic/LDrD6VsdUAy3C4Hub6ofjPRcdFosqSscUoOSFkP5cdGbG6h6dAotsxQ==";

		notSerializable = NotSerializable.builder().build();
		actual = verifier.verify(notSerializable, signature);
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);

		// new scenario
		signature = "eJA5uNEeQ49Wy34XCjv0p9DbGAq2yEY5kX9nAB3uqx3nktJMsh//Q1Ri+cHtLcRMCKbqHr1TWR1b0JFvVwVUSzdJcFg1diDrVI3VjJPOVVeygOjF1xXFZ0QSwGNiMoUlzLfGkD6Q7BHTC10hzRczn/uxMYDtXZdgZKGvLVDzsWT3HHneR+ZROLJ3jwQtIOntaQbGM6X3YVmRD9Pj/hDbkBhJX0nu6K55H20vYOKfO6PyePZTUfztUZ5ITiAwyjOhGM4tqkFuuQccr57KdRb7ltvRajX3BZa1GAjodVPOWzOUX+obB3ULtwJHqnjZ474cEUA6PqQ28tVkY6x+ZLVfzQ==";

		notSerializable = NotSerializable.builder().name("bla").build();
		actual = verifier.verify(notSerializable, signature);
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);
	}

}
