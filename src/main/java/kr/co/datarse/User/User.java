package kr.co.datarse.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "user")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty @Email @Size(min = 5, max = 255)
	private String email;
	@NotEmpty  @Size(min = 10, max = 100)
	private String password;
	@NotEmpty @Size(min = 2, max = 255)
	private String name;
	@NotEmpty
	private String hashkey;

	@Builder
	public User(Long id, String email, String password, String name, String hashkey) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.hashkey = hashkey;
	}
}
