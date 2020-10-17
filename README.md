# Overview

<div style="text-align: center;">

[![Build Status](https://travis-ci.org/astrapi69/sign-and-verify.svg?branch=develop)](https://travis-ci.org/astrapi69/sign-and-verify) 
[![Coverage Status](https://codecov.io/gh/astrapi69/sign-and-verify/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/sign-and-verify)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/sign-and-verify.svg?style=flat)](https://github.com/astrapi69/sign-and-verify/issues) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/sign-and-verify/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/sign-and-verify)
[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/sign-and-verify.svg)](http://www.javadoc.io/doc/de.alpharogroup/sign-and-verify)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Utility library that can sign and verify java objects.

If you like this project put a ⭐ and donate

## License

The source code comes under the liberal MIT License, making sign-and-verify great for all types 
of applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~sign-and-verify~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core 
functionality of sign-and-verify:

For development with jdk 1.8 use following version:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- sign-and-verify version -->
		<sign-and-verify.version>1.1</sign-and-verify.version>
			...
	</properties>
			...
		<dependencies>
			...
			<!-- sign-and-verify DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>sign-and-verify</artifactId>
				<version>${sign-and-verify.version}</version>
			</dependency>
			...
		</dependencies>
	
			
## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency 
to your project `build.gradle` if you want to import the core functionality:

```
define version in file gradle.properties

signAndVerifyVersion=1.1

or in build.gradle ext area
ext {
			...
    signAndVerifyVersion = "1.1"
			...
}

and add to  

dependencies {
			...
implementation("de.alpharogroup:sign-and-verify:$signAndVerifyVersion")
			...
}
```

# Usage

In some situation we have to create a digitale signature of objects, for check against unauthorized 
manipulation by third-party.
In this case the first step is to sign the specific object. For simple sign you can use the Signer
class that provides one method for sign a byte array. But before you start signing and verifying 
objects you need a KeyPair object and get the private key from it. As second, we need a signature 
algorithm. As an alternative you can use a private key file and extract the private key from it.

For that this library provides model classes that can encapsulate the needed objects. For the Signature
we need to create a SignatureBean that can be given as argument to the contructor of the Signer 
object that can then sign the given byte array as you can see in the following unit test.

```
public class SignerTest
{

	@Test
	public void testSign() throws Exception
	{
		byte[] actual;
		byte[] expected;
		byte[] valueBytes;
		String signatureAlgorithm;
		Charset charset;
		File publickeyDerDir;
		File privatekeyDerFile;
		PrivateKey privateKey;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		charset = StandardCharsets.UTF_8;
		valueBytes = "foo".getBytes(charset);

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		SignatureBean bean = SignatureBean.builder().privateKey(privateKey)
			.signatureAlgorithm(signatureAlgorithm).build();
		Signer signer = new Signer(bean);
		actual = signer.sign(valueBytes);
		expected = TestObjectFactory.newTestSignByteArray();
		assertArrayEquals(actual, expected);
    }
}
```

When the object have a digitale signature, and the appropriate field have been set you can then 
afterwards verify the object this object. To do the verification process you can use the Verifier
class that have one verify method with the bytes to verify and the signature as byte array to 
verify against.

```
public class VerifierTest
{

	@Test
	public void testVerifyWithCertificate() throws Exception
	{
		boolean actual;
		boolean expected;
		byte[] valueBytes;
		String signatureAlgorithm;
		Charset charset;
		File publickeyDerDir;
		File publickeyDerFile;
		File privatekeyDerFile;
		PrivateKey privateKey;
		PublicKey publicKey;
		Certificate certificate;

		publickeyDerDir = new File(PathFinder.getSrcTestResourcesDir(), "/der");
		publickeyDerFile = new File(publickeyDerDir, "public.der");
		privatekeyDerFile = new File(publickeyDerDir, "private.der");

		privateKey = PrivateKeyReader.readPrivateKey(privatekeyDerFile);
		publicKey = PublicKeyReader.readPublicKey(publickeyDerFile);
		signatureAlgorithm = CompoundAlgorithm.SHA256_WITH_RSA.getAlgorithm(); // SHA256withRSA

		charset = StandardCharsets.UTF_8;
		valueBytes = "foo".getBytes(charset);

		certificate = TestObjectFactory.newCertificateForTests(publicKey, privateKey,
			signatureAlgorithm);

		VerifyBean verifyBean = VerifyBean.builder().certificate(certificate)
			.signatureAlgorithm(signatureAlgorithm).build();

		Verifier verifier = new Verifier(verifyBean);
		actual = verifier.verify(valueBytes, TestObjectFactory.newTestSignByteArray());
		expected = true;
		assertTrue(actual);
		assertEquals(actual, expected);
	}
	
}
```

For more examples you can have a look at the unit test classes for JsonSigner, JsonVerifier,
ObjectSigner and ObjectVerifier.

## Semantic Versioning

The versions of sign-and-verify are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning).

## Want to Help and improve it? ###

The source code for sign-and-verify are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/sign-and-verify/fork](https://github.com/astrapi69/sign-and-verify/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/sign-and-verify/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the sign-and-verify developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/sign-and-verify/issues).

## Note

No animals were harmed in the making of this library.


# Donations

If you like this library, please consider a donation through bitcoin or over bitcoin-cash with:

36JxRRDfRazLNqUV6NsywCw1q7TK38ukpC

or over ether with:

0x588Aa02De98B1Ef70afeDC3ec5290130a3E5e273

or over flattr:

<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Fastrapi69%2Fsign-and-verify" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>

## Similar projects

Here is a list of awesome similar projects:

Open Source:

 * [commons-math](http://commons.apache.org/proper/commons-math/) The Apache Commons Mathematics Library
 * [Colt](https://dst.lbl.gov/ACSSoftware/colt/) Colt provides a set of Open Source Libraries for High Performance Scientific and Technical Computing in Java.

## Credits

|**Travis CI**|
|     :---:      |
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png) <img width=500/>|
|Special thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects|
|     <img width=1000/>     |

|**Nexus Sonatype repositories**|
|     :---:      |
|![sonatype repository](https://avatars1.githubusercontent.com/u/33330803?s=200&v=4)|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**codecov.io**|
|     :---:      |
|[![Coverage Status](https://codecov.io/gh/astrapi69/sign-and-verify/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/sign-and-verify)|
|Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/sign-and-verify.svg)](http://www.javadoc.io/doc/de.alpharogroup/sign-and-verify)|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |
