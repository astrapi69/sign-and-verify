package de.alpharogroup.sign;

import de.alpharogroup.io.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Objects;

/**
 * The class {@link ObjectVerifier} provides an algorithm  for java serializable objects with given
 * signed string arrays
 */
public final class ObjectVerifier<T extends Serializable>
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
	 * Instantiates a new {@link ObjectVerifier} object
	 *
	 * @param verifyBean The {@link VerifyBean} object holds the model data for verifying
	 * @throws NoSuchAlgorithmException is thrown if instantiation of the cipher object fails
	 */
	public ObjectVerifier(VerifyBean verifyBean)
		throws NoSuchAlgorithmException, InvalidKeyException
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
		}
		else
		{
			signature.initVerify(this.verifyBean.getCertificate());
		}
	}

	/**
	 * Verify the given byte array with the given signed byte array
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signedBytes   the signed byte array
	 * @return true, if successful otherwise false
	 * @throws InvalidKeyException is thrown if initialization of the cipher object fails
	 * @throws SignatureException  if the signature object is not initialized properly, the passed-in signature is
	 *                             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *                             process the input data provided, etc.
	 */
	public synchronized boolean verify(T bytesToVerify, String signedBytes)
		throws InvalidKeyException, SignatureException, IOException
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
	 * @throws SignatureException  if the signature object is not initialized properly, the passed-in signature is
	 *                             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *                             process the input data provided, etc.
	 * @throws InvalidKeyException is thrown if initialization of the cipher object fails
	 */
	private synchronized boolean verifyWithCertificate(T bytesToVerify, String signedBytes)
		throws SignatureException, InvalidKeyException, IOException
	{
		signature.initVerify(verifyBean.getCertificate());
		signature.update(Serializer.toByteArray(bytesToVerify));
		byte[] signedBytesBytes = Base64.getDecoder().decode(signedBytes);
		return signature.verify(signedBytesBytes);
	}

	/**
	 * Verify the given byte array with the given signed byte array with the public key of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signedBytes   the signed byte array
	 * @return true, if successful otherwise false
	 * @throws InvalidKeyException is thrown if initialization of the cipher object fails
	 * @throws SignatureException  if the signature object is not initialized properly, the passed-in signature is
	 *                             improperly encoded or of the wrong type, if this signature algorithm is unable to
	 *                             process the input data provided, etc.
	 */
	private synchronized boolean verifyWithPublicKey(T bytesToVerify, String signedBytes)
		throws InvalidKeyException, SignatureException, IOException
	{
		signature.initVerify(verifyBean.getPublicKey());
		signature.update(Serializer.toByteArray(bytesToVerify));
		byte[] signedBytesBytes = Base64.getDecoder().decode(signedBytes);
		return signature.verify(signedBytesBytes);
	}

}
