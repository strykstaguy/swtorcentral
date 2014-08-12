package com.stryksta.swtorcentral.data;

public class CharacterItem {

	private int id;
	private String advanced_class;
	private String race;
	private String gender;
	private String alignment;
	private String level;
	private String name;
	private String crew_skill_1;
	private String crew_skill_2;
	private String crew_skill_3;
	private String description;
		
	public CharacterItem(){}

	public CharacterItem(int id, String advanced_class, String race, String gender, String alignment, String level, String name,  String crew_skill_1,  String crew_skill_2,  String crew_skill_3, String description) {
		super();
		this.id = id;
		this.advanced_class = advanced_class;
		this.race = race;
		this.gender = gender;
		this.alignment = alignment;
		this.level = level;
		this.name = name;
		this.crew_skill_1 = crew_skill_1;
		this.crew_skill_2 = crew_skill_2;
		this.crew_skill_3 = crew_skill_3;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAdvancedClass() {
		return advanced_class;
	}

	public void setAdvancedClass(String advanced_class) {
		this.advanced_class = advanced_class;
	}
	
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCrewSkill_1() {
		return crew_skill_1;
	}

	public void setCrewSkill_1(String crew_skill_1) {
		this.crew_skill_1 = crew_skill_1;
	}
	
	public String getCrewSkill_2() {
		return crew_skill_2;
	}

	public void setCrewSkill_2(String crew_skill_2) {
		this.crew_skill_2 = crew_skill_2;
	}
	
	public String getCrewSkill_3() {
		return crew_skill_3;
	}

	public void setCrewSkill_3(String crew_skill_3) {
		this.crew_skill_3 = crew_skill_3;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", description=" + description
				+ "]";
	}
	
}
