package team.dx.classroom.domain;

public class TrueOrFalse extends Topic {

	public TrueOrFalse() {
		super();
	}

	public TrueOrFalse(String id, String chapter, String degree,
			String knowledgepoint, String score, String types, String title,
			String answer, String stuAnswer, String description,
			String a, String b, String c, String d) {
		super(id, chapter, degree, knowledgepoint, score, types, title, answer, stuAnswer, description);
		this.id = id;
		this.title = title;
		this.answer = answer;
		this.description = description;
	}

	@Override
	public String toString() {
		return "TrueOrFalse [id=" + id + ", chapter=" + chapter + ", degree="
				+ degree + ", knowledgepoint=" + knowledgepoint + ", score="
				+ score + ", types=" + types + ", title=" + title + ", answer="
				+ answer + ", stuAnswer=" + stuAnswer + ", description="
				+ description + "]";
	}
}
