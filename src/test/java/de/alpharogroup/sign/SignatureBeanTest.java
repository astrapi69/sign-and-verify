/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
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
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.throwable.RuntimeExceptionDecorator;
import org.junit.jupiter.api.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import static org.testng.Assert.assertNotNull;

/**
 * The unit test class for the class {@link SignatureBean}
 */
class SignatureBeanTest
{

	PrivateKey privateKey;

	/**
	 * Test method for creation of object {@link SignatureBean}
	 */
	@Test public void testObjectCreation()
		throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException,
		IOException
	{
		String signatureAlgorithm;
		File publickeyDerDir;
		File privatekeyDerFile;
		PrivateKey privateKey;
		SignatureBean object;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		object = SignatureBean.builder().build();
		assertNotNull(object);
		object = new SignatureBean(privateKey, signatureAlgorithm);
		assertNotNull(object);
		object = new SignatureBean();
		assertNotNull(object);
	}

	/**
	 * Test method for {@link SignatureBean} with {@link BeanTester}
	 */
	@Test public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("privateKey", new Factory<PrivateKey>()
			{
				@Override public PrivateKey create()
				{
					if (privateKey == null)
					{

						File privatekeyDerDir = new File(PathFinder.getSrcTestResourcesDir(),
							"/der");
						File privatekeyDerFile = new File(privatekeyDerDir, "private.der");
						privateKey = RuntimeExceptionDecorator
							.decorate(() -> PrivateKeyReader.readPrivateKey(privatekeyDerFile));
					}

					return privateKey;
				}
			}).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(SignatureBean.class, configuration);
		beanTester.testBean(SignatureBean.class);
	}
}
