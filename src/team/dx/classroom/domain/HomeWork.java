package team.dx.classroom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeWork implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Select> selects = new ArrayList<Select>();
	private List<TrueOrFalse> trueOrFalses = new ArrayList<TrueOrFalse>();
	private List<ShortQuestion> shortQuestions = new ArrayList<ShortQuestion>();
	
	public HomeWork() {
		super();
	}
	public HomeWork(List<Select> selects, List<TrueOrFalse> trueOrFalses,
			List<ShortQuestion> shortQuestions) {
		super();
		this.selects = selects;
		this.trueOrFalses = trueOrFalses;
		this.shortQuestions = shortQuestions;
	}
	
	public List<Select> getSelects() {
		return selects;
	}
	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}
	public List<TrueOrFalse> getTrueOrFalses() {
		return trueOrFalses;
	}
	public void setTrueOrFalses(List<TrueOrFalse> trueOrFalses) {
		this.trueOrFalses = trueOrFalses;
	}
	public List<ShortQuestion> getShortQuestions() {
		return shortQuestions;
	}
	public void setShortQuestions(List<ShortQuestion> shortQuestions) {
		this.shortQuestions = shortQuestions;
	}
	
}
