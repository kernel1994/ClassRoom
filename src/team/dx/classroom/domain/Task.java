package team.dx.classroom.domain;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String id;
	private String name;
	private String description;	
	
	/** 这个属性可以直接从数据库中查出并赋值，添加这个属性可以极大地方便对resource 对象的查询构造 */
	private String resource_id;
	private Resource resource;
	private List<Review> list = new ArrayList<Review>();

	/** 学生一个作业的分数 */
	private Integer score;

	public Task() {
	}

	public Task(String id, String name, String description, Resource resource, List<Review> list, Integer score) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.resource = resource;
		this.list = list;
		this.score = score;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Task{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", resource_id='" + resource_id + '\'' +
				", resource=" + resource +
				", list=" + list +
				", score=" + score +
				'}';
	}
}
