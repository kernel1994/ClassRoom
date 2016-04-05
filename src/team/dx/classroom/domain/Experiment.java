package team.dx.classroom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;	
	private int flag;       //0代表字符串，1代替数字，其他留着扩展
	private String input;
	private String output;
	
	private List<Review> list = new ArrayList<Review>();
	
	/** 学生一个作业的分数 */
	private Integer score;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public List<Review> getList() {
		return list;
	}
	public void setList(List<Review> list) {
		this.list = list;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	
}
