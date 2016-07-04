package com.dsinnovators.aauth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String mobileno;

	@Transient
	private String repeatPassword;

	private boolean enabled;

	private boolean enable2fa;

	private String OAuthClientId;

	@Transient
	private OAuthClientDetails oAuthClientDetails;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMobileno() {
		return mobileno;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isEnable2fa() {
		return enable2fa;
	}

	public String getOAuthClientId() {
		return OAuthClientId;
	}

	public OAuthClientDetails getoAuthClientDetails() {
		return oAuthClientDetails;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnable2fa(boolean enable2fa) {
		this.enable2fa = enable2fa;
	}

	public void setOAuthClientId(String oAuthClientId) {
		OAuthClientId = oAuthClientId;
	}

	public void setoAuthClientDetails(OAuthClientDetails oAuthClientDetails) {
		this.oAuthClientDetails = oAuthClientDetails;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
				.append(password).append(", mobileno=").append(mobileno).append(", repeatPassword=")
				.append(repeatPassword).append(", enabled=").append(enabled).append(", enable2fa=").append(enable2fa)
				.append(", OAuthClientId=").append(OAuthClientId).append(", oAuthClientDetails=")
				.append(oAuthClientDetails).append("]");
		return builder.toString();
	}

}