package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the forum_messages database table.
 * 
 */
@Entity
@Table(name="forum_messages")
@NamedQuery(name="ForumMessage.findAll", query="SELECT f FROM ForumMessage f")
public class ForumMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_wrote")
	private Date dateWrote;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;

	//bi-directional many-to-one association to ForumThread
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_thread")
	private ForumThread forumThread;

	public ForumMessage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateWrote() {
		return this.dateWrote;
	}

	public void setDateWrote(Date dateWrote) {
		this.dateWrote = dateWrote;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ForumThread getForumThread() {
		return this.forumThread;
	}

	public void setForumThread(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

}