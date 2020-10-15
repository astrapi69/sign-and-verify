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

import com.google.gson.Gson;
import de.alpharogroup.io.Serializer;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

/**
 * The class {@link JsonSigner} provide sign algorithm for java serializable objects
 */
public final class JsonSigner<T>
{

	/* the signer for sign byte arrays. */
	private final Signer signer;

	private final Gson gson;

	/**
	 * Instantiates a new {@link JsonSigner} object
	 *
	 * @param signatureBean the signature bean
	 */
	public JsonSigner(SignatureBean signatureBean, Gson gson)
	{
		Objects.requireNonNull(gson);
		this.signer = new Signer(signatureBean);
		this.gson = gson;
	}

	/**
	 * Sign the given byte array with the given private key and the appropriate algorithms.
	 *
	 * @param object the object to sign
	 * @return the signed byte array
	 */
	public synchronized String sign(final T object)
	{
		Objects.requireNonNull(object);
		byte[] signedBytes = this.signer.sign(Serializer.toByteArray(gson.toJson(object, object.getClass())));
		String encoded = Base64.getEncoder().encodeToString(signedBytes);
		return encoded;
	}

}
