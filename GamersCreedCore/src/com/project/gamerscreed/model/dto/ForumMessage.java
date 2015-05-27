package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the forum_messages database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="forum_messages")
@NamedQuery(name="ForumMessage.findAll", query="SELECT f FROM ForumMessage f")
public class ForumMessage implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The date wrote. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_wrote")
	private Date dateWrote;

	//bi-directional many-to-one association to User
	/** The user. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;

	//bi-directional many-to-one association to ForumThread
	/** The forum thread. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_thread")
	private ForumThread forumThread;

	/**
	 * Instantiates a new forum message.
	 */
	public ForumMessage() {
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
	 * Gets the date wrote.
	 *
	 * @return the date wrote
	 */
	public Date getDateWrote() {
		return this.dateWrote;
	}

	/**
	 * Sets the date wrote.
	 *
	 * @param dateWrote the new date wrote
	 */
	public void setDateWrote(Date dateWrote) {
		this.dateWrote = dateWrote;
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
	 * Gets the forum thread.
	 *
	 * @return the forum thread
	 */
	public ForumThread getForumThread() {
		return this.forumThread;
	}

	/**
	 * Sets the forum thread.
	 *
	 * @param forumThread the new forum thread
	 */
	public void setForumThread(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

}