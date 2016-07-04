package com.dsinnovators.aauth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userRoleId;

	@NotNull
	private String username;

	@NotNull
	private String role;

	public long getUserRoleId() {
		return userRoleId;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRole [userRoleId=").append(userRoleId).append(", username=").append(username)
				.append(", role=").append(role).append("]");
		return builder.toString();
	}

}