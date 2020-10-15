package de.alpharogroup.sign;

import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.crypto.compound.CompoundAlgorithm;
import de.alpharogroup.crypto.key.reader.PrivateKeyReader;
import de.alpharogroup.crypto.key.reader.PublicKeyReader;
import de.alpharogroup.file.search.PathFinder;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertNotNull;

class SignatureBeanTest
{

	/**
	 * Test method for creation of object {@link SignatureBean}
	 */
	@Test
	public void testObjectCreation()
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
}
