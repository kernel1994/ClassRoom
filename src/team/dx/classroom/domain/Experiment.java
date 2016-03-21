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
	
	/** 这个属性可以直接从数据库中查出并赋值，添加这个属性可以极大地方便对resource 对象的查询构造 */
	private String resource_id;
	private Resource resource;
	
	private List<Review> list = new ArrayList<Review>();
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
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public List<Review> getList() {
		return list;
	}
	public void setList(List<Review> list) {
		this.list = list;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
