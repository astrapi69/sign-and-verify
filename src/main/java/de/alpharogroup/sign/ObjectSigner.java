package de.alpharogroup.sign;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Objects;

/**
 * The class {@link ObjectSigner} provide sign algorithm for java serializable objects
 */
public final class ObjectSigner<T extends Serializable>
{
	/* the signer for sign byte arrays. */
	private Signer signer;

	/**
	 * Instantiates a new {@link ObjectSigner} object
	 *
	 * @param signatureBean
	 *            the signature bean
	 * @throws InvalidKeyException
	 *             is thrown if initialization of the cipher object fails
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 */
	public ObjectSigner(SignatureBean signatureBean)
		throws NoSuchAlgorithmException, InvalidKeyException
	{
		this.signer = new Signer(signatureBean);
	}

	/**
	 * Sign the given byte array with the given private key and the appropriate algorithms.
	 *
	 * @param object
	 *            the object to sign
	 * @return the signed byte array
	 * @throws SignatureException
	 *             is thrown if the signature object is not initialized properly or if this
	 *             signature algorithm is unable to process the input data provided
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public synchronized String sign(final T object) throws SignatureException, IOException
	{
		byte[] signedBytes = this.signer.sign(Serializer.toByteArray(object));
		String encoded = Base64.getEncoder().encodeToString(signedBytes);
		return encoded;
	}

}
