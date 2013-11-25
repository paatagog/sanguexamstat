package ge.sangu.model;

import java.util.List;

public class Lecture {

	private Integer id;
	
	private String name;
	
	private Integer program;
	
	private List<Lecture> prerequisites;
	
	private Double credit;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProgram() {
		return program;
	}

	public void setProgram(Integer program) {
		this.program = program;
	}

	public List<Lecture> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Lecture> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	public boolean isEmpty() {
		return id == null && name == null;
	}
}
