package net.rakip.ems.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	private Long id;
	private String name;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_id")),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_role_id"))
	)
	private Set<Role> roles;
	

}
