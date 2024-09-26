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
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String roleName;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="roles_authorities",
			joinColumns=@JoinColumn(name="roles_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="authorities_id", referencedColumnName="id")
			
		)
	private List<Authority> authorities = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
