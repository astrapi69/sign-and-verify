package de.alpharogroup.sign;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Objects;

/**
 * The class {@link Signer} provide sign algorithm for byte arrays
 */
public final class Signer
{

	/** The {@link Signature} object for signing */
	private final Signature signature;

	/** The {@link SignatureBean} object holds the model data for signing */
	private final SignatureBean signatureBean;

	/**
	 * Instantiates a new {@link Signer} object
	 *
	 * @param signatureBean
	 *            the signature bean
	 * @throws InvalidKeyException
	 *             is thrown if initialization of the cipher object fails
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 */
	public Signer(SignatureBean signatureBean) throws NoSuchAlgorithmException, InvalidKeyException
	{
		Objects.requireNonNull(signatureBean);
		Objects.requireNonNull(signatureBean.getPrivateKey());
		Objects.requireNonNull(signatureBean.getSignatureAlgorithm());
		this.signatureBean = signatureBean;
		this.signature = Signature.getInstance(this.signatureBean.getSignatureAlgorithm());
		this.signature.initSign(this.signatureBean.getPrivateKey());
	}

	/**
	 * Sign the given byte array with the given private key and the appropriate algorithms.
	 *
	 * @param bytesToSign
	 *            the bytes to sign
	 * @return the signed byte array
	 * @throws SignatureException
	 *             is thrown if the signature object is not initialized properly or if this
	 *             signature algorithm is unable to process the input data provided
	 */
	public synchronized byte[] sign(byte[] bytesToSign)
		throws SignatureException
	{
		signature.update(bytesToSign);
		return signature.sign();
	}

}
