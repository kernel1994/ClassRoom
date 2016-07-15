package team.dx.classroom.domain;

import java.util.Date;

public class Resource {

	private String id;
	private String name;
	private String uri;
	private Date uploadtime;
	private String description;
	
	private User uploader;

	public Resource() {
	}

	public Resource(String id, String name, String uri, Date uploadtime, String description, User uploader) {
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.uploadtime = uploadtime;
		this.description = description;
		this.uploader = uploader;
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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", uri=" + uri + ", uploadtime=" + uploadtime + ", description=" + description + ", uploader=" + uploader + "]";
	}

}
