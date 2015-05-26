package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the operations database table.
 * 
 */
@Entity
@Table(name="operations")
@NamedQueries({
	@NamedQuery(name="Operation.findAll", query="SELECT o FROM Operation o"),
	@NamedQuery(name="Operation.rejectOperation", query="UPDATE Operation o SET o.rejected = false WHERE o.id = :id")
})
public class Operation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_accepted")
	private Date dateAccepted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_sended")
	private Date dateSended;

	private float price;

	private boolean rejected;

	//bi-directional many-to-one association to Videogame
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_videogame_receiver")
	private Videogame videogameReceived;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_sender")
	private User userSender;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_receiver")
	private User userReceived;

	//bi-directional many-to-one association to Videogame
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_videogame_sender")
	private Videogame videogameSended;

	public Operation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateAccepted() {
		return this.dateAccepted;
	}

	public void setDateAccepted(Date dateAccepted) {
		this.dateAccepted = dateAccepted;
	}

	public Date getDateSended() {
		return this.dateSended;
	}

	public void setDateSended(Date dateSended) {
		this.dateSended = dateSended;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean getRejected() {
		return this.rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public Videogame getVideogameReceived() {
		return this.videogameReceived;
	}

	public void setVideogameReceived(Videogame videogameReceived) {
		this.videogameReceived = videogameReceived;
	}

	public User getUserSender() {
		return this.userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public User getUserReceived() {
		return this.userReceived;
	}

	public void setUserReceived(User userReceived) {
		this.userReceived = userReceived;
	}

	public Videogame getVideogameSended() {
		return this.videogameSended;
	}

	public void setVideogameSended(Videogame videogameSended) {
		this.videogameSended = videogameSended;
	}

}