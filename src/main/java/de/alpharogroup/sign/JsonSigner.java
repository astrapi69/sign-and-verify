/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
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

import java.util.Objects;

/**
 * The class {@link JsonSigner} provide sign algorithm for all java objects. Uses internally json
 * for serialization
 */
public final class JsonSigner<T>
{

	/* the signer for sign byte arrays */
	private final ObjectSigner<String> signer;

	/* the gson object for serialization */
	private final Gson gson;

	/**
	 * Instantiates a new {@link JsonSigner} object
	 *
	 * @param signatureBean the signature bean
	 * @param gson the gson for serialization
	 */
	public JsonSigner(SignatureBean signatureBean, Gson gson)
	{
		Objects.requireNonNull(gson);
		this.signer = new ObjectSigner<>(signatureBean);
		this.gson = gson;
	}

	/**
	 * Sign the given java object
	 *
	 * @param object the object to sign
	 * @return the encoded signature of the given java object
	 */
	public synchronized String sign(final T object)
	{
		Objects.requireNonNull(object);
		String json = gson.toJson(object, object.getClass());
		return this.signer.sign(json);
	}

}
