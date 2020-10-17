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

import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.crypto.key.reader.PublicKeyReader;
import de.alpharogroup.file.search.PathFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The unit test class for the class {@link Verifier}
 */
public class VerifierTest
{

	/**
	 * Test method for {@link Verifier#verify(byte[], byte[])}  with a certificate
	 *
	 * @throws Exception
	 *             is thrown if any security exception occured
	 */
	@Test public void testVerifyWithCertificate() throws Exception
	{
		boolean actual;
		boolean expected;
		byte[] valueBytes;
		String signatureAlgorithm;
		Charset charset;
		File publickeyDerDir;
		File publickeyDerFile;
		File privatekeyDerFile;
		PrivateKey privateKey;
		PublicKey publicKey;
		Certificate certificate;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		charset = StandardCharsets.UTF_8;
		valueBytes = "foo".getBytes(charset);

		certificate = TestObjectFactory
			.newCertificateForTests(publicKey, privateKey, signatureAlgorithm);

		VerifyBean verifyBean = VerifyBean.builder().certificate(certificate)
			.signatureAlgorithm(signatureAlgorithm).build();

		Verifier verifier = new Verifier(verifyBean);
		actual = verifier.verify(valueBytes, TestObjectFactory.newTestSignByteArray());
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link Verifier#verify(byte[], byte[])} with the public key
	 *
	 * @throws Exception
	 *             is thrown if any security exception occured
	 */
	@Test public void testVerifyWithPublicKey() throws Exception
	{
		boolean actual;
		boolean expected;
		byte[] valueBytes;
		String signatureAlgorithm;
		Charset charset;
		File publickeyDerDir;
		File publickeyDerFile;
		PublicKey publicKey;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");

		charset = StandardCharsets.UTF_8;
		valueBytes = "foo".getBytes(charset);

		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		VerifyBean verifyBean = VerifyBean.builder().publicKey(publicKey)
			.signatureAlgorithm(signatureAlgorithm).build();

		Verifier verifier = new Verifier(verifyBean);
		actual = verifier.verify(valueBytes, TestObjectFactory.newTestSignByteArray());
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link Verifier#verify(byte[], byte[])} with the public key
	 */
	@Test public void testObjectCreationWithInvalidVerifyBean()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Verifier(VerifyBean.builder()
				.signatureAlgorithm(CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm()).build());
		});
	}
}
