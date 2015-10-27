package team.dx.classroom.domain;

import java.io.Serializable;

/**
 * 描述一道题。是选择题、判断题、问答题的公共父类
 */
public class Topic implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected String id;
    protected String title;
    protected String answer;
    protected String description;

    public Topic() {
    }

    public Topic(String id, String title, String answer, String description) {
        this.id = id;
        this.title = title;
        this.answer = answer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
