package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.ForumSection;

/**
 * The Class ForumSectionBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class ForumSectionBasicData {
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new forum section basic data.
	 *
	 * @param aSection the a section
	 */
	public ForumSectionBasicData(ForumSection aSection){
		this.name = aSection.getName();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
