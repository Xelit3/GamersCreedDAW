package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the forum_threads database table.
 * 
 */
@Entity
@Table(name="forum_threads")
@NamedQuery(name="ForumThread.findAll", query="SELECT f FROM ForumThread f")
public class ForumThread implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String title;

	//bi-directional many-to-one association to ForumMessage
	@OneToMany(mappedBy="forumThread")
	private List<ForumMessage> forumMessages;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_creation")
	private User user;

	//bi-directional many-to-one association to ForumSection
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_section")
	private ForumSection forumSection;

	public ForumThread() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ForumMessage> getForumMessages() {
		return this.forumMessages;
	}

	public void setForumMessages(List<ForumMessage> forumMessages) {
		this.forumMessages = forumMessages;
	}

	public ForumMessage addForumMessage(ForumMessage forumMessage) {
		getForumMessages().add(forumMessage);
		forumMessage.setForumThread(this);

		return forumMessage;
	}

	public ForumMessage removeForumMessage(ForumMessage forumMessage) {
		getForumMessages().remove(forumMessage);
		forumMessage.setForumThread(null);

		return forumMessage;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ForumSection getForumSection() {
		return this.forumSection;
	}

	public void setForumSection(ForumSection forumSection) {
		this.forumSection = forumSection;
	}

}