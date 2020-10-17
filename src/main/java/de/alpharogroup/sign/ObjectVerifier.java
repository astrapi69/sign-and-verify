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
import de.alpharogroup.throwable.RuntimeExceptionDecorator;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Base64;
import java.util.Objects;

/**
 * The class {@link ObjectVerifier} provides an algorithm for java serializable objects with given
 * encoded signature
 */
public final class ObjectVerifier<T extends Serializable>
{

	/**
	 * The {@link Verifier} object for the verification
	 */
	Verifier verifier;

	/**
	 * Instantiates a new {@link ObjectVerifier} object
	 *
	 * @param verifyBean The {@link VerifyBean} object holds the model data for verifying
	 */
	public ObjectVerifier(VerifyBean verifyBean)
	{
		this.verifier = new Verifier(verifyBean);
	}

	/**
	 * Verify the given serializable object with the given signed encoded signature
	 *
	 * @param object the object to verify
	 * @param signedBytes   the encoded signature to verify against
	 * @return true, if successful otherwise false
	 */
	public synchronized boolean verify(T object, String signedBytes)
	{
		return this.verifier.verify(Serializer.toByteArray(object),
			Base64.getDecoder().decode(signedBytes));
	}

}
