package com.karasuno.spring.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karasuno.spring.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
@JsonIgnoreProperties(ignoreUnknown = false)
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	@NotBlank(message = "Username is required")
	@Size(min = 6, message = "Username must be at least 6 characters long")
	private String username;

	@Column(name = "password")
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@Column(name = "first_name")
	@NotBlank(message = "First name is required")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "Last name is required")
	private String lastName;

	@Column(name = "gender")
	@NotNull
	private Boolean gender;

	@Column(name = "email")
	@ValidEmail
	@NotBlank(message = "Email is required")
	private String email;

	@Column(name = "phone_number")
	@Pattern(regexp = "(^$|[0-9]{9,10})", message = "Invalid phone number")
	private String phoneNum;

	@Column(name = "image")
	private String image;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "Users_Roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cart cart;
	
	public void addRole(Role role) {
		if (roles == null) roles = new LinkedHashSet<Role>();
		roles.add(role);
	}

	public boolean isCustomer() {
		return roles.stream().anyMatch(role -> role.getRole().equals("ROLE_CUSTOMER"));
	}

	public boolean isManager() {
		return roles.stream().anyMatch(role -> role.getRole().equals("ROLE_MANAGER"));
	}

	public boolean isAdmin() {
		return roles.stream().anyMatch(role -> role.getRole().equals("ROLE_ADMIN"));
	}

	@Override
	public String toString() {
		return String.format("\nId: %d | Username: %s | Password: %s | First Name: %s | Last Name: %s | gender: %d | email: %s | phoneNumber: %s | image: %s"
		, id, username, password, firstName, lastName, gender, email, phoneNum, image);
	}
}
