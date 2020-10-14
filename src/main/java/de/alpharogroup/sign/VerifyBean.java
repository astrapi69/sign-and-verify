package de.alpharogroup.sign;

import java.security.PublicKey;
import java.security.cert.Certificate;

public class VerifyBean
{
	private Certificate certificate;
	private PublicKey publicKey;
	private String signatureAlgorithm;

	public VerifyBean(Certificate certificate, PublicKey publicKey, String signatureAlgorithm)
	{
		this.certificate = certificate;
		this.publicKey = publicKey;
		this.signatureAlgorithm = signatureAlgorithm;
	}

	public VerifyBean()
	{
	}

	protected VerifyBean(VerifyBeanBuilder<?, ?> b)
	{
		this.certificate = b.certificate;
		this.publicKey = b.publicKey;
		this.signatureAlgorithm = b.signatureAlgorithm;
	}

	public static VerifyBeanBuilder<?, ?> builder()
	{
		return new VerifyBeanBuilderImpl();
	}

	public Certificate getCertificate()
	{
		return this.certificate;
	}

	public PublicKey getPublicKey()
	{
		return this.publicKey;
	}

	public String getSignatureAlgorithm()
	{
		return this.signatureAlgorithm;
	}

	public void setCertificate(Certificate certificate)
	{
		this.certificate = certificate;
	}

	public void setPublicKey(PublicKey publicKey)
	{
		this.publicKey = publicKey;
	}

	public void setSignatureAlgorithm(String signatureAlgorithm)
	{
		this.signatureAlgorithm = signatureAlgorithm;
	}

	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof VerifyBean))
			return false;
		final VerifyBean other = (VerifyBean)o;
		if (!other.canEqual((Object)this))
			return false;
		final Object this$certificate = this.getCertificate();
		final Object other$certificate = other.getCertificate();
		if (this$certificate == null ?
			other$certificate != null :
			!this$certificate.equals(other$certificate))
			return false;
		final Object this$publicKey = this.getPublicKey();
		final Object other$publicKey = other.getPublicKey();
		if (this$publicKey == null ?
			other$publicKey != null :
			!this$publicKey.equals(other$publicKey))
			return false;
		final Object this$signatureAlgorithm = this.getSignatureAlgorithm();
		final Object other$signatureAlgorithm = other.getSignatureAlgorithm();
		if (this$signatureAlgorithm == null ?
			other$signatureAlgorithm != null :
			!this$signatureAlgorithm.equals(other$signatureAlgorithm))
			return false;
		return true;
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof VerifyBean;
	}

	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $certificate = this.getCertificate();
		result = result * PRIME + ($certificate == null ? 43 : $certificate.hashCode());
		final Object $publicKey = this.getPublicKey();
		result = result * PRIME + ($publicKey == null ? 43 : $publicKey.hashCode());
		final Object $signatureAlgorithm = this.getSignatureAlgorithm();
		result = result * PRIME + ($signatureAlgorithm == null ?
			43 :
			$signatureAlgorithm.hashCode());
		return result;
	}

	public String toString()
	{
		return "VerifyBean(certificate=" + this.getCertificate() + ", publicKey=" + this
			.getPublicKey() + ", signatureAlgorithm=" + this.getSignatureAlgorithm() + ")";
	}

	public static abstract class VerifyBeanBuilder<C extends VerifyBean, B extends VerifyBean.VerifyBeanBuilder<C, B>>
	{
		private Certificate certificate;
		private PublicKey publicKey;
		private String signatureAlgorithm;

		public B certificate(Certificate certificate)
		{
			this.certificate = certificate;
			return self();
		}

		public B publicKey(PublicKey publicKey)
		{
			this.publicKey = publicKey;
			return self();
		}

		public B signatureAlgorithm(String signatureAlgorithm)
		{
			this.signatureAlgorithm = signatureAlgorithm;
			return self();
		}

		protected abstract B self();

		public abstract C build();

		public String toString()
		{
			return "VerifyBean.VerifyBeanBuilder(certificate=" + this.certificate + ", publicKey=" + this.publicKey + ", signatureAlgorithm=" + this.signatureAlgorithm + ")";
		}
	}

	private static final class VerifyBeanBuilderImpl
		extends VerifyBeanBuilder<VerifyBean, VerifyBeanBuilderImpl>
	{
		private VerifyBeanBuilderImpl()
		{
		}

		protected VerifyBean.VerifyBeanBuilderImpl self()
		{
			return this;
		}

		public VerifyBean build()
		{
			return new VerifyBean(this);
		}
	}
}
