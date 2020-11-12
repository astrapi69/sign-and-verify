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

import de.alpharogroup.throwable.RuntimeExceptionDecorator;

import java.security.Signature;
import java.util.Objects;

/**
 * The class {@link Verifier} provides an algorithm for verifying byte arrays with given signed byte
 * arrays
 */
public final class Verifier
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
	 * Instantiates a new {@link Verifier} object
	 *
	 * @param verifyBean The {@link VerifyBean} object holds the model data for verifying
	 */
	public Verifier(VerifyBean verifyBean)
	{
		Objects.requireNonNull(verifyBean);
		Objects.requireNonNull(verifyBean.getSignatureAlgorithm());
		if (verifyBean.getPublicKey() == null && verifyBean.getCertificate() == null)
		{
			throw new IllegalArgumentException("Please provide a public key or certificate");
		}
		this.verifyBean = verifyBean;
		this.signature = newSignature(this.verifyBean);
	}

	protected Signature newSignature(final VerifyBean verifyBean) {
		return RuntimeExceptionDecorator.decorate(() -> {
			Signature signature = Signature.getInstance(verifyBean.getSignatureAlgorithm());
			if (verifyBean.getPublicKey() != null)
			{
				signature.initVerify(verifyBean.getPublicKey());
			}
			else
			{
				signature.initVerify(verifyBean.getCertificate());
			}
			return signature;
		});
	}

	/**
	 * Verify the given byte array with the given signature
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signature   the signature as byte array to verify against
	 * @return true, if successful otherwise false
	 */
	public synchronized boolean verify(byte[] bytesToVerify, byte[] signature)
	{
		if (verifyBean.getPublicKey() != null)
		{
			return verifyWithPublicKey(bytesToVerify, signature);
		}
		return verifyWithCertificate(bytesToVerify, signature);
	}

	/**
	 * Verify the given byte array with the given signed byte array with the certificate of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signature   the signature as byte array to verify against
	 * @return true, if successful otherwise false
	 */
	private synchronized boolean verifyWithCertificate(byte[] bytesToVerify, byte[] signature)
	{
		return RuntimeExceptionDecorator.decorate(() -> {
			this.signature.initVerify(verifyBean.getCertificate());
			this.signature.update(bytesToVerify);
			return this.signature.verify(signature);
		});
	}

	/**
	 * Verify the given byte array with the given signed byte array with the public key of the
	 * verifyBean and the appropriate algorithms.
	 *
	 * @param bytesToVerify the bytes to verify
	 * @param signature   the signature as byte array to verify against
	 * @return true, if successful otherwise false
	 */
	private synchronized boolean verifyWithPublicKey(byte[] bytesToVerify, byte[] signature)
	{
		return RuntimeExceptionDecorator.decorate(() -> {
			this.signature.initVerify(verifyBean.getPublicKey());
			this.signature.update(bytesToVerify);
			return this.signature.verify(signature);
		});
	}

}
