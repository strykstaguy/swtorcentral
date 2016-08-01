package com.stryksta.swtorcentral.models;

public class AddCharacterItem {

	private int id;
	private int class_id;
	private int advanced_class_id;
	private int race_id;
	private int gender_id;
	private int alignment;
	private int skill_tree_build_id;
	private int level;
	private String name;
	private String legacy;
	private int crew_skill_id_1;
	private int crew_skill_id_2;
	private int crew_skill_id_3;
	private String description;
		
	public AddCharacterItem(){}

	public AddCharacterItem(int class_id, int advanced_class_id, int race_id, int gender_id, int alignment, int skill_tree_build_id, int level, String name, String legacy, int crew_skill_id_1,  int crew_skill_id_2,  int crew_skill_id_3, String description) {
		super();
		
		this.class_id = class_id;
		this.advanced_class_id = advanced_class_id;
		this.race_id = race_id;
		this.gender_id = gender_id;
		this.alignment = alignment;
		this.skill_tree_build_id = skill_tree_build_id;
		this.level = level;
		this.name = name;
		this.legacy = legacy;
		this.crew_skill_id_1 = crew_skill_id_1;
		this.crew_skill_id_2 = crew_skill_id_2;
		this.crew_skill_id_3 = crew_skill_id_3;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getClassId() {
		return class_id;
	}

	public void setClassId(int class_id) {
		this.class_id = class_id;
	}
	
	public int getAdvancedClassId() {
		return advanced_class_id;
	}

	public void setAdvancedClassId(int advanced_class_id) {
		this.advanced_class_id = advanced_class_id;
	}
	
	public int getRace() {
		return race_id;
	}

	public void setRace(int race_id) {
		this.race_id = race_id;
	}
	
	public int getGender() {
		return gender_id;
	}

	public void setGender(int gender_id) {
		this.gender_id = gender_id;
	}
	
	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}
	
	public int getSkillTreeBuildId() {
		return skill_tree_build_id;
	}

	public void setSkillTreeBuildId(int skill_tree_build_id) {
		this.skill_tree_build_id = skill_tree_build_id;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String legacy) {
		this.legacy = legacy;
	}
	
	public String getLegacy() {
		return legacy;
	}

	public void setLegacy(String name) {
		this.name = name;
	}
	
	public int getCrewSkillId_1() {
		return crew_skill_id_1;
	}

	public void setCrewSkillId_1(int crew_skill_id_1) {
		this.crew_skill_id_1 = crew_skill_id_1;
	}
	
	public int getCrewSkillId_2() {
		return crew_skill_id_2;
	}

	public void setCrewSkillId_2(int crew_skill_id_2) {
		this.crew_skill_id_2 = crew_skill_id_2;
	}
	
	public int getCrewSkillId_3() {
		return crew_skill_id_3;
	}

	public void setCrewSkillId_3(int crew_skill_id_3) {
		this.crew_skill_id_3 = crew_skill_id_3;
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
