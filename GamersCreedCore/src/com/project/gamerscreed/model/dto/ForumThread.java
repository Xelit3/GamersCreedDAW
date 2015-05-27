package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the forum_threads database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="forum_threads")
@NamedQuery(name="ForumThread.findAll", query="SELECT f FROM ForumThread f")
public class ForumThread implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	/** The title. */
	private String title;

	//bi-directional many-to-one association to ForumMessage
	/** The forum messages. */
	@OneToMany(mappedBy="forumThread")
	private List<ForumMessage> forumMessages;

	//bi-directional many-to-one association to User
	/** The user. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_creation")
	private User user;

	//bi-directional many-to-one association to ForumSection
	/** The forum section. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_section")
	private ForumSection forumSection;

	/**
	 * Instantiates a new forum thread.
	 */
	public ForumThread() {
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
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the forum messages.
	 *
	 * @return the forum messages
	 */
	public List<ForumMessage> getForumMessages() {
		return this.forumMessages;
	}

	/**
	 * Sets the forum messages.
	 *
	 * @param forumMessages the new forum messages
	 */
	public void setForumMessages(List<ForumMessage> forumMessages) {
		this.forumMessages = forumMessages;
	}

	/**
	 * Adds the forum message.
	 *
	 * @param forumMessage the forum message
	 * @return the forum message
	 */
	public ForumMessage addForumMessage(ForumMessage forumMessage) {
		getForumMessages().add(forumMessage);
		forumMessage.setForumThread(this);

		return forumMessage;
	}

	/**
	 * Removes the forum message.
	 *
	 * @param forumMessage the forum message
	 * @return the forum message
	 */
	public ForumMessage removeForumMessage(ForumMessage forumMessage) {
		getForumMessages().remove(forumMessage);
		forumMessage.setForumThread(null);

		return forumMessage;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the forum section.
	 *
	 * @return the forum section
	 */
	public ForumSection getForumSection() {
		return this.forumSection;
	}

	/**
	 * Sets the forum section.
	 *
	 * @param forumSection the new forum section
	 */
	public void setForumSection(ForumSection forumSection) {
		this.forumSection = forumSection;
	}

}