/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
	private final Signer signer;

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
	 * Sign the given serializable object
	 *
	 * @param object the object to sign
	 * @return the encoded signature
	 */
	public synchronized String sign(final T object)
	{
		byte[] signedBytes = this.signer.sign(Serializer.toByteArray(object));
		String encoded = Base64.getEncoder().encodeToString(signedBytes);
		return encoded;
	}

}
