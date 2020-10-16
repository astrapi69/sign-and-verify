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

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import de.alpharogroup.collections.array.ArrayFactory;
import de.alpharogroup.crypto.factories.CertFactory;
import de.alpharogroup.random.number.RandomBigIntegerFactory;

/**
 * The factory class {@link TestObjectFactory} for create test objects
 */
public final class TestObjectFactory
{
	public static Certificate newCertificateForTests(final PublicKey publicKey,
		final PrivateKey privateKey, String signatureAlgorithm) throws CertificateEncodingException,
		NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		String subject;
		String issuer;
		Date start;
		Date end;
		BigInteger serialNumber;

		subject = "CN=Test subject";
		issuer = "CN=Test issue";

		start = Date.from(
			LocalDate.of(2020, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		end = Date.from(
			LocalDate.of(2030, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		serialNumber = RandomBigIntegerFactory.randomSerialNumber();
		return CertFactory.newX509Certificate(publicKey, privateKey, serialNumber, subject, issuer,
			signatureAlgorithm, start, end);
	}

	public static byte[] newTestSignByteArray() {
		return ArrayFactory.newByteArray((byte)-63, (byte)-69, (byte)77, (byte)-127, (byte)-38,
			(byte)-87, (byte)-101, (byte)-25, (byte)-54, (byte)110, (byte)-76, (byte)-39, (byte)-7,
			(byte)-4, (byte)-107, (byte)119, (byte)-121, (byte)-77, (byte)-69, (byte)-106, (byte)35,
			(byte)4, (byte)-92, (byte)-10, (byte)-72, (byte)-111, (byte)-25, (byte)123, (byte)-115,
			(byte)92, (byte)86, (byte)55, (byte)67, (byte)-31, (byte)29, (byte)-127, (byte)-27,
			(byte)-83, (byte)-84, (byte)53, (byte)83, (byte)-105, (byte)-84, (byte)-25, (byte)102,
			(byte)-103, (byte)-95, (byte)-29, (byte)8, (byte)-38, (byte)-116, (byte)-22, (byte)44,
			(byte)-126, (byte)68, (byte)111, (byte)-22, (byte)-89, (byte)49, (byte)117, (byte)13,
			(byte)29, (byte)117, (byte)52, (byte)55, (byte)8, (byte)48, (byte)64, (byte)-49,
			(byte)24, (byte)43, (byte)39, (byte)12, (byte)111, (byte)-60, (byte)-54, (byte)-94,
			(byte)-126, (byte)-33, (byte)-64, (byte)-43, (byte)72, (byte)-32, (byte)-66, (byte)-113,
			(byte)61, (byte)68, (byte)78, (byte)-85, (byte)114, (byte)-110, (byte)-16, (byte)-65,
			(byte)106, (byte)-69, (byte)-68, (byte)89, (byte)-14, (byte)-75, (byte)-25, (byte)14,
			(byte)29, (byte)-68, (byte)-96, (byte)-31, (byte)-117, (byte)-97, (byte)-62, (byte)-100,
			(byte)-120, (byte)81, (byte)-82, (byte)-17, (byte)-97, (byte)-55, (byte)14, (byte)-59,
			(byte)79, (byte)-39, (byte)-42, (byte)-77, (byte)5, (byte)-45, (byte)-10, (byte)-104,
			(byte)-53, (byte)58, (byte)-96, (byte)34, (byte)110, (byte)-21, (byte)34, (byte)-59,
			(byte)95, (byte)-122, (byte)52, (byte)31, (byte)20, (byte)-107, (byte)-48, (byte)65,
			(byte)-1, (byte)6, (byte)59, (byte)69, (byte)48, (byte)108, (byte)61, (byte)-26,
			(byte)-44, (byte)-2, (byte)9, (byte)-57, (byte)63, (byte)54, (byte)-98, (byte)-106,
			(byte)62, (byte)46, (byte)-73, (byte)24, (byte)98, (byte)-107, (byte)4, (byte)-83,
			(byte)-5, (byte)-116, (byte)125, (byte)96, (byte)-21, (byte)-16, (byte)-46, (byte)122,
			(byte)-116, (byte)92, (byte)-24, (byte)-62, (byte)92, (byte)-114, (byte)-67, (byte)71,
			(byte)-66, (byte)77, (byte)114, (byte)-15, (byte)-28, (byte)26, (byte)114, (byte)-83,
			(byte)88, (byte)17, (byte)-67, (byte)38, (byte)0, (byte)-37, (byte)84, (byte)77,
			(byte)82, (byte)55, (byte)69, (byte)-114, (byte)-118, (byte)-2, (byte)20, (byte)-14,
			(byte)-80, (byte)-18, (byte)77, (byte)-109, (byte)-84, (byte)-37, (byte)-114, (byte)83,
			(byte)24, (byte)-67, (byte)-103, (byte)-2, (byte)-105, (byte)-93, (byte)-17, (byte)-42,
			(byte)-12, (byte)-51, (byte)10, (byte)-28, (byte)21, (byte)-119, (byte)-22, (byte)40,
			(byte)34, (byte)-30, (byte)-108, (byte)-49, (byte)-65, (byte)58, (byte)98, (byte)69,
			(byte)-103, (byte)-119, (byte)17, (byte)14, (byte)112, (byte)-4, (byte)82, (byte)17,
			(byte)58, (byte)47, (byte)20, (byte)19, (byte)-97, (byte)-107, (byte)-7, (byte)97,
			(byte)-102, (byte)10, (byte)105);
	}

	private TestObjectFactory()
	{
	}
}
