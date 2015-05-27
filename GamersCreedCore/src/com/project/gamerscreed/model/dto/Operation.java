package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import com.project.gamerscreed.view.OperationBasicData;

import java.util.Date;

/**
 * The persistent class for the operations database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="operations")
@NamedQueries({
	@NamedQuery(name="Operation.findAll", query="SELECT o FROM Operation o"),
	@NamedQuery(name="Operation.rejectOperation", query="UPDATE Operation o SET o.rejected = false WHERE o.id = :id")
})
public class Operation implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The date accepted. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_accepted")
	private Date dateAccepted;

	/** The date sended. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_sended")
	private Date dateSended;

	/** The price. */
	private float price;

	/** The rejected. */
	private boolean rejected;

	//bi-directional many-to-one association to Videogame
	/** The videogame received. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_videogame_receiver")
	private Videogame videogameReceived;

	//bi-directional many-to-one association to User
	/** The user sender. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_sender")
	private User userSender;

	//bi-directional many-to-one association to User
	/** The user received. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_receiver")
	private User userReceived;

	//bi-directional many-to-one association to Videogame
	/** The videogame sended. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_videogame_sender")
	private Videogame videogameSended;

	/**
	 * Instantiates a new operation.
	 */
	public Operation() {
	}

	/**
	 * Instantiates a new operation.
	 *
	 * @param anOperation the an operation
	 */
	public Operation(OperationBasicData anOperation) {
		this.dateSended = new Date();
		this.price = anOperation.getPrice();
		this.userReceived = new User();
		this.userReceived.setId(anOperation.getUserReceiverId());
		this.userSender = new User();
		this.userSender.setId(anOperation.getUserSenderId());
		this.videogameReceived = new Videogame();
		this.videogameReceived.setId(anOperation.getVideogameReceiverId());
		this.videogameSended = new Videogame();
		this.videogameSended.setId(anOperation.getVideogameSenderId());
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
	 * Gets the date accepted.
	 *
	 * @return the date accepted
	 */
	public Date getDateAccepted() {
		return this.dateAccepted;
	}

	/**
	 * Sets the date accepted.
	 *
	 * @param dateAccepted the new date accepted
	 */
	public void setDateAccepted(Date dateAccepted) {
		this.dateAccepted = dateAccepted;
	}

	/**
	 * Gets the date sended.
	 *
	 * @return the date sended
	 */
	public Date getDateSended() {
		return this.dateSended;
	}

	/**
	 * Sets the date sended.
	 *
	 * @param dateSended the new date sended
	 */
	public void setDateSended(Date dateSended) {
		this.dateSended = dateSended;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the rejected.
	 *
	 * @return the rejected
	 */
	public boolean getRejected() {
		return this.rejected;
	}

	/**
	 * Sets the rejected.
	 *
	 * @param rejected the new rejected
	 */
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	/**
	 * Gets the videogame received.
	 *
	 * @return the videogame received
	 */
	public Videogame getVideogameReceived() {
		return this.videogameReceived;
	}

	/**
	 * Sets the videogame received.
	 *
	 * @param videogameReceived the new videogame received
	 */
	public void setVideogameReceived(Videogame videogameReceived) {
		this.videogameReceived = videogameReceived;
	}

	/**
	 * Gets the user sender.
	 *
	 * @return the user sender
	 */
	public User getUserSender() {
		return this.userSender;
	}

	/**
	 * Sets the user sender.
	 *
	 * @param userSender the new user sender
	 */
	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	/**
	 * Gets the user received.
	 *
	 * @return the user received
	 */
	public User getUserReceived() {
		return this.userReceived;
	}

	/**
	 * Sets the user received.
	 *
	 * @param userReceived the new user received
	 */
	public void setUserReceived(User userReceived) {
		this.userReceived = userReceived;
	}

	/**
	 * Gets the videogame sended.
	 *
	 * @return the videogame sended
	 */
	public Videogame getVideogameSended() {
		return this.videogameSended;
	}

	/**
	 * Sets the videogame sended.
	 *
	 * @param videogameSended the new videogame sended
	 */
	public void setVideogameSended(Videogame videogameSended) {
		this.videogameSended = videogameSended;
	}

}