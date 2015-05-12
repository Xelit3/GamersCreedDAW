package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the forum_sections database table.
 * 
 */
@Entity
@Table(name="forum_sections")
@NamedQuery(name="ForumSection.findAll", query="SELECT f FROM ForumSection f")
public class ForumSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to ForumThread
	@OneToMany(mappedBy="forumSection")
	private List<ForumThread> forumThreads;

	public ForumSection() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ForumThread> getForumThreads() {
		return this.forumThreads;
	}

	public void setForumThreads(List<ForumThread> forumThreads) {
		this.forumThreads = forumThreads;
	}

	public ForumThread addForumThread(ForumThread forumThread) {
		getForumThreads().add(forumThread);
		forumThread.setForumSection(this);

		return forumThread;
	}

	public ForumThread removeForumThread(ForumThread forumThread) {
		getForumThreads().remove(forumThread);
		forumThread.setForumSection(null);

		return forumThread;
	}

}