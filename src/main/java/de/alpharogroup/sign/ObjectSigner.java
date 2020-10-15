package de.alpharogroup.sign;

import de.alpharogroup.io.Serializer;

import java.io.Serializable;
import java.util.Base64;

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
	 * @param signatureBean the signature bean
	 */
	public ObjectSigner(SignatureBean signatureBean)
	{
		this.signer = new Signer(signatureBean);
	}

	/**
	 * Sign the given byte array with the given private key and the appropriate algorithms.
	 *
	 * @param object the object to sign
	 * @return the signed byte array
	 */
	public synchronized String sign(final T object)
	{
		byte[] signedBytes = this.signer.sign(Serializer.toByteArray(object));
		String encoded = Base64.getEncoder().encodeToString(signedBytes);
		return encoded;
	}

}
