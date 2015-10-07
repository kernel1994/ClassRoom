package team.dx.classroom.domain;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private String id;
	private String name;
	private String description;
	private Person person;
	private Set<Privilege> privilleges = new HashSet();

	public Role() {
	}

	public Role(String id, String name, String description, Person person, Set<Privilege> privilleges) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.person = person;
		this.privilleges = privilleges;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Privilege> getPrivilleges() {
		return privilleges;
	}

	public void setPrivilleges(Set<Privilege> privilleges) {
		this.privilleges = privilleges;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", person=" + person + ", privilleges=" + privilleges + "]";
	}

}
