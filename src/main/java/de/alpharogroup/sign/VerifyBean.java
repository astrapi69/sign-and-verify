package de.alpharogroup.sign;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class VerifyBean
{
	Certificate certificate;
	PublicKey publicKey;
	String signatureAlgorithm;
}
