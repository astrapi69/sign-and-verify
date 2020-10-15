package de.alpharogroup.sign;

import de.alpharogroup.throwable.RuntimeExceptionDecorator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Objects;

/**
 * The class {@link Verifier} provides an algorithm for verifying byte arrays with given signed byte
 * arrays
 */
public final class Verifier
{

	/**
	 * The {@link Signature} object for the verification
	 */
	private final Signature signature;

	/**
	 * The {@link VerifyBean} object holds the model data for the verification
	 */
	private final VerifyBean verifyBean;

	/**
	 * Instantiates a new {@link Verifier} object
	 *
	 * @param verifyBean The {@link VerifyBean} object holds the model data for verifying
	 */
	public Verifier(VerifyBean verifyBean)
	{
		Objects.requireNonNull(verifyBean);
		Objects.requireNonNull(verifyBean.getSignatureAlgorithm());
		if (verifyBean.getPublicKey() == null && verifyBean.getCertificate() == null)
		{
			throw new IllegalArgumentException("Please provide a public key or certificate");
		}
		this.verifyBean = verifyBean;
		try
		{
			this.signature = Signature.getInstance(this.verifyBean.getSignatureAlgorithm());
			if (verifyBean.getPublicKey() != null)
			{
				signature.initVerify(this.verifyBean.getPublicKey());
			}
			else
			{
				signature.initVerify(this.verifyBean.getCertificate());
			}
		}
		catch (InvalidKeyException | NoSuchAlgorithmException exception)
		{
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Verify the given byte array with the given signed byte array
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signedBytes   the signed byte array
	 * @return true, if successful otherwise false
	 */
	public synchronized boolean verify(byte[] bytesToVerify, byte[] signedBytes)
	{
		if (verifyBean.getPublicKey() != null)
		{
			return verifyWithPublicKey(bytesToVerify, signedBytes);
		}
		return verifyWithCertificate(bytesToVerify, signedBytes);
	}

	/**
	 * Verify the given byte array with the given signed byte array with the certificate of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signedBytes   the signed byte array
	 * @return true, if successful otherwise false
	 */
	private synchronized boolean verifyWithCertificate(byte[] bytesToVerify, byte[] signedBytes)
	{
		return RuntimeExceptionDecorator.decorate(() -> {
			signature.initVerify(verifyBean.getCertificate());
			signature.update(bytesToVerify);
			return signature.verify(signedBytes);
		});
	}

	/**
	 * Verify the given byte array with the given signed byte array with the public key of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signedBytes   the signed byte array
	 * @return true, if successful otherwise false
	 */
	private synchronized boolean verifyWithPublicKey(byte[] bytesToVerify, byte[] signedBytes)
	{
		return RuntimeExceptionDecorator.decorate(() -> {
			signature.initVerify(verifyBean.getPublicKey());
			signature.update(bytesToVerify);
			return signature.verify(signedBytes);
		});
	}

}
