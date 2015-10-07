package team.dx.classroom.domain;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String nick;
	private String email;
	private String password;
	private Set<Role> roles = new HashSet();

	public User() {
	}

	public User(String id, String nick, String email, String password, Set<Role> roles) {
		this.id = id;
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.roles = roles;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nick=" + nick + ", password=" + password + ", roles=" + roles + "]";
	}

}
