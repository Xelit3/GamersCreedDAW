package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the forum_sections database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="forum_sections")
@NamedQuery(name="ForumSection.findAll", query="SELECT f FROM ForumSection f")
public class ForumSection implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to ForumThread
	/** The forum threads. */
	@OneToMany(mappedBy="forumSection")
	private List<ForumThread> forumThreads;

	/**
	 * Instantiates a new forum section.
	 */
	public ForumSection() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the forum threads.
	 *
	 * @return the forum threads
	 */
	public List<ForumThread> getForumThreads() {
		return this.forumThreads;
	}

	/**
	 * Sets the forum threads.
	 *
	 * @param forumThreads the new forum threads
	 */
	public void setForumThreads(List<ForumThread> forumThreads) {
		this.forumThreads = forumThreads;
	}

	/**
	 * Adds the forum thread.
	 *
	 * @param forumThread the forum thread
	 * @return the forum thread
	 */
	public ForumThread addForumThread(ForumThread forumThread) {
		getForumThreads().add(forumThread);
		forumThread.setForumSection(this);

		return forumThread;
	}

	/**
	 * Removes the forum thread.
	 *
	 * @param forumThread the forum thread
	 * @return the forum thread
	 */
	public ForumThread removeForumThread(ForumThread forumThread) {
		getForumThreads().remove(forumThread);
		forumThread.setForumSection(null);

		return forumThread;
	}

}