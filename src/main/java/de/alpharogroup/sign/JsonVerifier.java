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
import de.alpharogroup.throwable.RuntimeExceptionDecorator;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Base64;
import java.util.Objects;

/**
 * The class {@link JsonVerifier} provides an algorithm for verifying all java objects with given
 * encoded signature. Uses internally json for serialization
 */
public final class JsonVerifier<T>
{

	/**
	 * The {@link Verifier} object for the verification
	 */
	private final ObjectVerifier<String> verifier;

	/**
	 * The {@link Gson} object for serialization
	 */
	private final Gson gson;

	/**
	 * Instantiates a new {@link JsonVerifier} object
	 *
	 * @param verifyBean The {@link VerifyBean} object holds the model data for verifying
	 * @param gson The {@link Gson} object for serialization
	 */
	public JsonVerifier(VerifyBean verifyBean, Gson gson)
	{
		Objects.requireNonNull(gson);
		this.verifier = new ObjectVerifier<>(verifyBean);
		this.gson = gson;
	}

	/**
	 * Verify the given byte array with the given signed byte array
	 *
	 * @param object the object to verify
	 * @param signature   the signature to verify against
	 * @return true, if successful otherwise false
	 */
	public synchronized boolean verify(T object, String signature)
	{
		String json = gson.toJson(object, object.getClass());
		return this.verifier.verify(json, signature);
	}

}
