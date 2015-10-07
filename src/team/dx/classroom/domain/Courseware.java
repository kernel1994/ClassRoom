package team.dx.classroom.domain;

import java.util.ArrayList;
import java.util.List;

public class Courseware {

	private String id;
	private String name;
	private String description;
	private Resource resource;
	private List<Review> list = new ArrayList();

	public Courseware() {
	}

	public Courseware(String id, String name, String description, Resource resource, List<Review> list) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.resource = resource;
		this.list = list;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Review> getList() {
		return list;
	}

	public void setList(List<Review> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Courseware [id=" + id + ", name=" + name + ", description=" + description + ", resource=" + resource + ", list=" + list + "]";
	}

}
