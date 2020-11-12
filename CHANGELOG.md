## Change log
----------------------

Version 1.2
-------------

ADDED: 

- added new annotation class SignatureExclude for fields that have to be excluded from the signing and verifying process
- added new factory method in Signer for create the signature that decorates checked exceptions to runtime exceptions
- added new factory method in Verifier for create the signature that decorates checked exceptions to runtime exceptions

CHANGED:

- increase code coverage
- optimized of JsonSigner and JsonVerifier for debugging issues
- optimized of ObjectSigner and ObjectVerifier for debugging issues
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.36.0
- update of dependency lombok in new version 1.18.16
- update of test dependency crypt-data to new version 7.4
- update of test dependency silly-collections to new version 8.4

Version 1.1
-------------

ADDED: 

- added usage area to the README.md file

CHANGED:

- update of gradle to new version 6.7
- update of test dependency file-worker to new version 5.7
- javadoc extended and optimized

Version 1
-------------

ADDED: 

- initial version of project
- new CHANGELOG.md file created
- gradle as build system

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
