package de.alpharogroup.sign;

import de.alpharogroup.throwable.RuntimeExceptionDecorator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Objects;

/**
 * The class {@link Signer} provide sign algorithm for byte arrays
 */
public final class Signer
{

	/**
	 * The {@link Signature} object for signing
	 */
	private final Signature signature;

	/**
	 * The {@link SignatureBean} object holds the model data for signing
	 */
	private final SignatureBean signatureBean;

	/**
	 * Instantiates a new {@link Signer} object
	 *
	 * @param signatureBean the signature bean
	 * @throws InvalidKeyException      is thrown if initialization of the cipher object fails
	 * @throws NoSuchAlgorithmException is thrown if instantiation of the SecretKeyFactory object fails.
	 */
	public Signer(SignatureBean signatureBean)
	{
		Objects.requireNonNull(signatureBean);
		Objects.requireNonNull(signatureBean.getPrivateKey());
		Objects.requireNonNull(signatureBean.getSignatureAlgorithm());
		this.signatureBean = signatureBean;
		try
		{
			this.signature = Signature.getInstance(this.signatureBean.getSignatureAlgorithm());
			this.signature.initSign(this.signatureBean.getPrivateKey());
		}
		catch (InvalidKeyException | NoSuchAlgorithmException exception)
		{
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Sign the given byte array with the given private key and the appropriate algorithms.
	 *
	 * @param bytesToSign the bytes to sign
	 * @return the signed byte array
	 */
	public synchronized byte[] sign(byte[] bytesToSign)
	{
		return RuntimeExceptionDecorator.decorate(() -> {
			signature.update(bytesToSign);
			return signature.sign();
		});
	}

}
