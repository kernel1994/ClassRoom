package team.dx.classroom.domain;

public class User {

	private String id;
	private String nick;
	private String email;
	private String password;
	private Role role; /* 角色，一个账号在系统中的角色类型 */

	public User() {
	}

	public User(String id, String nick, String email, String password, Role role) {
		this.id = id;
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nick=" + nick + ", password=" + password + ", role=" + role + "]";
	}

}
