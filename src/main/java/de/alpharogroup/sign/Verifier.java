package de.alpharogroup.sign;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Objects;

/**
 * The class {@link Verifier} provides an algorithm for verifying byte arrays with given signed byte
 * arrays
 */
public final class Verifier
{

	/** The {@link Signature} object for the verification */
	private final Signature signature;

	/** The {@link VerifyBean} object holds the model data for the verification */
	private final VerifyBean verifyBean;

	/**
	 * Instantiates a new {@link Verifier} object
	 *
	 * @param verifyBean
	 *            The {@link VerifyBean} object holds the model data for verifying
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the cipher object fails
	 */
	public Verifier(VerifyBean verifyBean) throws NoSuchAlgorithmException, InvalidKeyException
	{

		Objects.requireNonNull(verifyBean);
		Objects.requireNonNull(verifyBean.getSignatureAlgorithm());
		if (verifyBean.getPublicKey() == null && verifyBean.getCertificate() == null)
		{
			throw new IllegalArgumentException("Please provide a public key or certificate");
		}
		this.verifyBean = verifyBean;
		this.signature = Signature.getInstance(this.verifyBean.getSignatureAlgorithm());
		if (verifyBean.getPublicKey() != null)
		{
			signature.initVerify(this.verifyBean.getPublicKey());
		} else {
			signature.initVerify(this.verifyBean.getCertificate());
		}
	}

	/**
	 * Verify the given byte array with the given signed byte array
	 *
	 * @param bytesToVerify
	 *            the bytes to verify
	 * @param signedBytes
	 *            the signed byte array
	 * @return true, if successful otherwise false
	 * @throws InvalidKeyException
	 *             is thrown if initialization of the cipher object fails
	 * @throws SignatureException
	 *             if the signature object is not initialized properly, the passed-in signature is
	 *             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *             process the input data provided, etc.
	 */
	public synchronized boolean verify(byte[] bytesToVerify, byte[] signedBytes)
		throws InvalidKeyException, SignatureException
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
	 * @param bytesToVerify
	 *            the bytes to verify
	 * @param signedBytes
	 *            the signed byte array
	 * @return true, if successful otherwise false
	 * @throws SignatureException
	 *             if the signature object is not initialized properly, the passed-in signature is
	 *             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *             process the input data provided, etc.
	 * @throws InvalidKeyException
	 *             is thrown if initialization of the cipher object fails
	 */
	private synchronized boolean verifyWithCertificate(byte[] bytesToVerify, byte[] signedBytes)
		throws SignatureException, InvalidKeyException
	{
		signature.initVerify(verifyBean.getCertificate());
		signature.update(bytesToVerify);
		return signature.verify(signedBytes);
	}

	/**
	 * Verify the given byte array with the given signed byte array with the public key of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify
	 *            the bytes to verify
	 * @param signedBytes
	 *            the signed byte array
	 * @return true, if successful otherwise false
	 * @throws InvalidKeyException
	 *             is thrown if initialization of the cipher object fails
	 * @throws SignatureException
	 *             if the signature object is not initialized properly, the passed-in signature is
	 *             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *             process the input data provided, etc.
	 */
	private synchronized boolean verifyWithPublicKey(byte[] bytesToVerify, byte[] signedBytes)
		throws InvalidKeyException, SignatureException
	{
		signature.initVerify(verifyBean.getPublicKey());
		signature.update(bytesToVerify);
		return signature.verify(signedBytes);
	}

}
