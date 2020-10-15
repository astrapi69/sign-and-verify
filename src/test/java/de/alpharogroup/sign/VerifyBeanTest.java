package de.alpharogroup.sign;

import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.crypto.key.reader.PublicKeyReader;
import de.alpharogroup.evaluate.object.verifier.ContractVerifier;
import de.alpharogroup.file.search.PathFinder;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class VerifyBeanTest
{

	/**
	 * Test method for creation of object {@link VerifyBean}
	 */
	@Test
	public void testObjectCreation()
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

}
