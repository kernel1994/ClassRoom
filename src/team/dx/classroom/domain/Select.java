package team.dx.classroom.domain;

public class Select extends Topic {

	private String a;
	private String b;
	private String c;
	private String d;
	
	public Select() {
		super();
	}

	
	public Select(String id, String chapter, String degree,
			String knowledgepoint, String score, String types, String title,
			String answer, String stuAnswer, String description,
			String a, String b, String c, String d) {
		super(id, chapter, degree, knowledgepoint, score, types, title, answer, stuAnswer, description);
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}




	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Select [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
				+ ", id=" + id + ", chapter=" + chapter + ", degree=" + degree
				+ ", knowledgepoint=" + knowledgepoint + ", score=" + score
				+ ", types=" + types + ", title=" + title + ", answer="
				+ answer + ", stuAnswer=" + stuAnswer + ", description="
				+ description + "]";
	}
}
