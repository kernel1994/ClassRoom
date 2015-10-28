package team.dx.classroom.domain;

import java.util.Date;

public class User {

	private String id;
	private String nick;
	private String email;
	private String password;
	private String name;
	private String gender;
	private Date birthday;
	private String address;
	private String description;
	private Role role; /* 角色，一个账号在系统中的角色类型 */

	public User() {
	}

	public User(String id, String nick, String email, String password,
			String name, String gender, Date bitthday, String address,
			String description, Role role) {
		this.id = id;
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birthday = bitthday;
		this.address = address;
		this.description = description;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "User [id=" + id + ", nick=" + nick + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", bitthday=" + birthday + ", address=" + address
				+ ", description=" + description + ", role=" + role + "]";
	}

}
