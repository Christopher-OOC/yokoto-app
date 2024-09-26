package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	// Where userId is the same as customerId
	@Id
	@Column(name = "customer_id")
	private String customerId;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	private boolean emailVerificationStatus;
	
	private String emailVerificationToken;
	
	private String passwordResetToken;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="customer_roles",
		joinColumns=@JoinColumn(name="customer_id", referencedColumnName="customer_id"),
		inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id")
	)
	private List<Role> roles = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(customerId, user.customerId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(customerId);
	}
}
