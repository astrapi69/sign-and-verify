package de.alpharogroup.sign;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * The utility class {@link Serializer} can convert java objects to byte arrays
 */
public final class Serializer
{

	private Serializer()
	{
	}

	/**
	 * Copies the given object to a byte array
	 *
	 * @param object
	 *            The object to copy
	 * @return the byte array
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static<T extends Serializable> byte[] toByteArray(final T object) throws IOException
	{
		Objects.requireNonNull(object);
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream))
		{
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		}
	}
}
