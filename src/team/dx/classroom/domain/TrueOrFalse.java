package team.dx.classroom.domain;

import java.io.Serializable;

public class TrueOrFalse extends Topic {


	public TrueOrFalse() {
		super();
	}

	public TrueOrFalse(String id, String title, String answer, String description) {
		super(id, title, answer, description);
		this.id = id;
		this.title = title;
		this.answer = answer;
		this.description = description;
	}

	@Override
	public String toString() {
		return "TrueOrFalse{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", answer='" + answer + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
