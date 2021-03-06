package team.dx.classroom.domain;

import java.io.Serializable;

/**
 * 描述一道题。是选择题、判断题、问答题的公共父类
 */
public class Topic implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected String id;
    
    protected String chapter;
    protected String degree;
    protected String knowledgepoint;
    protected String score;
    protected String types;
    
    protected String title;
    protected String answer;
    protected String stuAnswer;
    protected String description;

    public Topic() {
    }

	public Topic(String id, String chapter, String degree,
			String knowledgepoint, String score, String types, String title,
			String answer, String stuAnswer, String description) {
		super();
		this.id = id;
		this.chapter = chapter;
		this.degree = degree;
		this.knowledgepoint = knowledgepoint;
		this.score = score;
		this.types = types;
		this.title = title;
		this.answer = answer;
		this.stuAnswer = stuAnswer;
		this.description = description;
	}




	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


	public String getChapter() {
		return chapter;
	}


	public void setChapter(String chapter) {
		this.chapter = chapter;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getKnowledgepoint() {
		return knowledgepoint;
	}


	public void setKnowledgepoint(String knowledgepoint) {
		this.knowledgepoint = knowledgepoint;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}
	
	

	public String getTypes() {
		return types;
	}



	public void setTypes(String types) {
		this.types = types;
	}



	@Override
	public String toString() {
		return "Topic [id=" + id + ", chapter=" + chapter + ", degree="
				+ degree + ", knowledgepoint=" + knowledgepoint + ", score="
				+ score + ", types=" + types + ", title=" + title + ", answer="
				+ answer + ", stuAnswer=" + stuAnswer + ", description="
				+ description + "]";
	}


    
}
