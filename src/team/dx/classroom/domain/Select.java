package team.dx.classroom.domain;

public class Select extends Topic {

	private String a;
	private String b;
	private String c;
	private String d;
	
	public Select() {
		super();
	}

	public Select(String id, String title, String a, String b, String c, String d, String answer, String description) {
		super(id, title, answer, description);
		this.id = id;
		this.title = title;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.answer = answer;
		this.description = description;
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
		return "Select{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", a='" + a + '\'' +
				", b='" + b + '\'' +
				", c='" + c + '\'' +
				", d='" + d + '\'' +
				", answer='" + answer + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
