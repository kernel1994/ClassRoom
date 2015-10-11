package team.dx.classroom.domain;

import java.util.Date;

public class Review {

	private String id;
	private String content;
	private Date time;
	/** ÆÀÂÛµÄÈË */
	private User user;

	public Review() {
	}

	public Review(String id, User user, String content, Date time) {
		this.id = id;
		this.user = user;
		this.content = content;
		this.time = time;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", user=" + user + ", content=" + content + ", time=" + time + "]";
	}

}
