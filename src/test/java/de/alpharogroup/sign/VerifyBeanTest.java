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
import de.alpharogroup.throwable.RuntimeExceptionDecorator;
import org.junit.jupiter.api.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The unit test class for the class {@link VerifyBean}
 */
class VerifyBeanTest
{
	Certificate certificate;
	PublicKey publicKey;

	/**
	 * Test method for creation of object {@link VerifyBean}
	 */
	@Test public void testObjectCreation()
		throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException,
		IOException, CertificateEncodingException, InvalidKeyException, SignatureException
	{
		String signatureAlgorithm;
		File publickeyDerDir;
		File publickeyDerFile;
		File privatekeyDerFile;
		PrivateKey privateKey;
		PublicKey publicKey;
		Certificate certificate;
		VerifyBean object;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		certificate = TestObjectFactory
			.newCertificateForTests(publicKey, privateKey, signatureAlgorithm);

		object = VerifyBean.builder().build();
		assertNotNull(object);
		object = new VerifyBean(certificate, publicKey, signatureAlgorithm);
		assertNotNull(object);
		object = new VerifyBean();
		assertNotNull(object);
	}

	/**
	 * Test method for {@link VerifyBean} with {@link BeanTester}
	 */
	@Test public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("certificate", new Factory<Certificate>()
			{
				@Override public Certificate create()
				{
					if (certificate == null)
					{
						String signatureAlgorithm;
						File publickeyDerDir;
						File publickeyDerFile;
						File privatekeyDerFile;
						PrivateKey privateKey;
						PublicKey publicKey;

						publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
						publickeyDerFile = new File(publickeyDerDir, "public.der");
						privatekeyDerFile = new File(publickeyDerDir, "private.der");
						privateKey = RuntimeExceptionDecorator
							.decorate(() -> PrivateKeyReader.readPrivateKey(privatekeyDerFile));
						publicKey = RuntimeExceptionDecorator
							.decorate(() -> PublicKeyReader.readPublicKey(publickeyDerFile));
						signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA
							.getAlgorithm(); // SHA256withRSA
						certificate = RuntimeExceptionDecorator.decorate(() -> TestObjectFactory
							.newCertificateForTests(publicKey, privateKey, signatureAlgorithm));
					}
					return certificate;
				}
			}).overrideFactory("publicKey", new Factory<PublicKey>()
			{
				@Override public PublicKey create()
				{
					if (publicKey == null)
					{
						File publickeyDerDir;
						File publickeyDerFile;

						publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
						publickeyDerFile = new File(publickeyDerDir, "public.der");
						publicKey = RuntimeExceptionDecorator
							.decorate(() -> PublicKeyReader.readPublicKey(publickeyDerFile));
					}
					return publicKey;
				}
			}).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(VerifyBean.class, configuration);
		beanTester.testBean(VerifyBean.class);
	}
}
