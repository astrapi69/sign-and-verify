package de.alpharogroup.sign;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import de.alpharogroup.crypto.factories.CertFactory;
import de.alpharogroup.random.number.RandomBigIntegerFactory;

public final class TestObjectFactory
{
	public static Certificate newCertificateForTests(final PublicKey publicKey,
		final PrivateKey privateKey, String signatureAlgorithm) throws CertificateEncodingException,
		NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		String subject;
		String issuer;
		Date start;
		Date end;
		BigInteger serialNumber;

		subject = "CN=Test subject";
		issuer = "CN=Test issue";

		start = Date.from(
			LocalDate.of(2020, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		end = Date.from(
			LocalDate.of(2030, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		serialNumber = RandomBigIntegerFactory.randomSerialNumber();
		return CertFactory.newX509Certificate(publicKey, privateKey, serialNumber, subject, issuer,
			signatureAlgorithm, start, end);
	}

	private TestObjectFactory()
	{
	}
}
