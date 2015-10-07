package team.dx.classroom.domain;

import java.util.Date;

public class Person {

	private String id;
	private String name;
	private String gender;
	private Date bitthday;
	private String address;
	private String description;

	public Person() {
	}

	public Person(String id, String name, String gender, Date bitthday, String address, String description) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bitthday = bitthday;
		this.address = address;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getBitthday() {
		return bitthday;
	}

	public void setBitthday(Date bitthday) {
		this.bitthday = bitthday;
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender + ", bitthday=" + bitthday + ", address=" + address + ", description=" + description + "]";
	}

}
