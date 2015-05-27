package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the posts database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="posts")
@NamedQueries({
	@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p"),
	@NamedQuery(name="Post.findAllByUser", query="SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.id=:id")
})
public class Post implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The content. */
	private String content;

	/** The post date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date postDate;

	//bi-directional many-to-one association to User
	/** The user. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;

	/**
	 * Instantiates a new post.
	 */
	public Post() {
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
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the post date.
	 *
	 * @return the post date
	 */
	public Date getPostDate() {
		return this.postDate;
	}

	/**
	 * Sets the post date.
	 *
	 * @param postDate the new post date
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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

}