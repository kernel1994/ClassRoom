package team.dx.classroom.domain;

import java.util.Date;

public class Review {

	private String id;
	private String name;
	private Date time;

	public Review() {
	}

	public Review(String id, String name, Date time) {
		this.id = id;
		this.name = name;
		this.time = time;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", name=" + name + ", time=" + time + "]";
	}

}
