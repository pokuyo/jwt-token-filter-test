package kr.co.datarse.user.model;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
//@Entity(name = "user")
@NoArgsConstructor
public class User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
//	@Id
//	@Column(unique = true)
	@NotEmpty
	private String userid;
	@NotEmpty
	private String password;
	@NotEmpty
	private String username;
	@NotEmpty
	private String hashkey;
	@NotEmpty
	private String jsessionid;

	@Builder
	public User(String userid, String password, String username, String jsessionid, String hashkey) {
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.jsessionid = jsessionid;
		this.hashkey = hashkey;
	}
}
