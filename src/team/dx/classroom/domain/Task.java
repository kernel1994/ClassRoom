package team.dx.classroom.domain;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String id;
	private String name;
	private String description;	
	
	/** ������Կ���ֱ�Ӵ����ݿ��в������ֵ�����������Կ��Լ���ط����resource ����Ĳ�ѯ���� */
	private String resource_id;
	private Resource resource;
	private List<Review> list = new ArrayList<Review>();
	public Task() {
	}

	public Task(String id, String name, String description, Resource resource, List<Review> list) {
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

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public List<Review> getList() {
		return list;
	}

	public void setList(List<Review> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description="
				+ description + ", resource_id=" + resource_id + ", resource="
				+ resource + ", list=" + list + "]";
	}

}
