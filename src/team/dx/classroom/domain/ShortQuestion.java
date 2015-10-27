package team.dx.classroom.domain;

public class ShortQuestion extends Topic {
	
	public ShortQuestion() {
		super();
	}

	public ShortQuestion(String id, String title, String description) {
		super(id, title, null, description);
		this.id = id;
		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ShortQuestion{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
