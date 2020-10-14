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

Utility library that can sign and verify java objects

If you like this project put a ⭐ and donate

## License

The source code comes under the liberal MIT License, making sign-and-verify great for all types of applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~sign-and-verify~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of sign-and-verify:

For development with jdk 1.8 use following version:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- sign-and-verify version -->
		<sign-and-verify.version>1.3</sign-and-verify.version>
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

You can first define the version in the ext section and add than the following gradle dependency to your project `build.gradle` if you want to import the core functionality of file-worker:

```
ext {
			...
    sillyMathVersion = "1.3"
			...
}
dependencies {
			...
compile("de.alpharogroup:sign-and-verify:$sillyMathVersion")
			...
}
```

If you develop above jdk 11 you can use sign-and-verify version 2.x and above.

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
