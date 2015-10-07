package team.dx.classroom.domain;

public class Course {

	private String id;
	private String name;
	private int limitperson;
	private String description;
	private User teacher;

	public Course() {
	}

	public Course(String id, String name, int limitperson, String description, User teacher) {
		this.id = id;
		this.name = name;
		this.limitperson = limitperson;
		this.description = description;
		this.teacher = teacher;
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

	public int getLimitperson() {
		return limitperson;
	}

	public void setLimitperson(int limitperson) {
		this.limitperson = limitperson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", limitperson=" + limitperson + ", description=" + description + ", teacher=" + teacher + "]";
	}

}
