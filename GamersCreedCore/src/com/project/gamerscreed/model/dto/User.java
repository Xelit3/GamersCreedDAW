package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.gamerscreed.view.UserBasicData;

/**
 * The persistent class for the users database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findOthers", query="SELECT u FROM User u WHERE u.id <> :id"),
	@NamedQuery(name="User.searchUser", query="SELECT u FROM User u WHERE u.username LIKE :username"),
	@NamedQuery(name="User.getById", query="SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name="User.loginUser", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="User.getAllFollowers", query="SELECT u FROM User u JOIN FETCH u.followers WHERE u.id = :id")
})
public class User implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The mail. */
	private String mail;

	/** The name. */
	private String name;
	
	/** The password. */
	private String password;

	/** The username. */
	private String username;

	//bi-directional many-to-one association to ForumMessage
	/** The forum messages. */
	@OneToMany(mappedBy="user") 
	private List<ForumMessage> forumMessages;

	//bi-directional many-to-one association to ForumThread
	/** The forum threads. */
	@OneToMany(mappedBy="user")
	private List<ForumThread> forumThreads;

	//bi-directional many-to-one association to Operation
	/** The operations sended. */
	@OneToMany(mappedBy="userSender")
	private List<Operation> operationsSended;

	//bi-directional many-to-one association to Operation
	/** The operations received. */
	@OneToMany(mappedBy="userReceived")
	private List<Operation> operationsReceived;

	//bi-directional many-to-one association to Post
	/** The posts. */
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	//bi-directional many-to-one association to Address
	/** The address. */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_address")
	private Address address;

	//bi-directional many-to-one association to Role
	/** The role. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_role")
	private Role role;

	//uni-directional many-to-many association to User
	/** The followers. */
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="users_followers"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_follower")
			}
		)
	private List<User> followers;

	//uni-directional many-to-many association to User
	/** The followings. */
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="users_followings"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_following")
			}
		)
	private List<User> followings;

	//bi-directional many-to-many association to Videogame
	/** The videogames. */
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="users_videogames"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_videogame")
			}
		)
	private List<Videogame> videogames;

	/**
	 * Instantiates a new user.
	 */
	public User() {	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param aRole the a role
	 */
	public User(Role aRole){
		this.role = aRole;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param aUsername the a username
	 * @param aPassword the a password
	 */
	public User(String aUsername, String aPassword) {
		super();
		this.username = aUsername;
		this.password = aPassword;		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param aUsername the a username
	 * @param aRole the a role
	 * @param aName the a name
	 * @param aMail the a mail
	 * @param anAddress the an address
	 */
	public User(String aUsername, Role aRole, String aName, String aMail, Address anAddress){
		this.username = aUsername;
		this.name = aName;
		this.mail = aMail;
		this.address = anAddress;
		this.role = aRole;
	}
	
	public User(UserBasicData anUser){
		this.id = anUser.getId();
		this.username = anUser.getUsername();
		this.name = anUser.getName();
		this.mail = anUser.getMail();
		this.address = new Address();
		this.address.setCity(new City(anUser.getAddressCity(), new Country(anUser.getAddressCountryId())));
		this.address.setCp(anUser.getAddressCp());
		this.address.setStreet(anUser.getAddressStreet());
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
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
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
		forumMessage.setUser(this);

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
		forumMessage.setUser(null);

		return forumMessage;
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
		forumThread.setUser(this);

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
		forumThread.setUser(null);

		return forumThread;
	}

	/**
	 * Gets the operations sended.
	 *
	 * @return the operations sended
	 */
	public List<Operation> getOperationsSended() {
		return this.operationsSended;
	}

	/**
	 * Sets the operations sended.
	 *
	 * @param operationsSended the new operations sended
	 */
	public void setOperationsSended(List<Operation> operationsSended) {
		this.operationsSended = operationsSended;
	}

	/**
	 * Adds the operations sended.
	 *
	 * @param operationsSended the operations sended
	 * @return the operation
	 */
	public Operation addOperationsSended(Operation operationsSended) {
		getOperationsSended().add(operationsSended);
		operationsSended.setUserSender(this);

		return operationsSended;
	}

	/**
	 * Removes the operations sended.
	 *
	 * @param operationsSended the operations sended
	 * @return the operation
	 */
	public Operation removeOperationsSended(Operation operationsSended) {
		getOperationsSended().remove(operationsSended);
		operationsSended.setUserSender(null);

		return operationsSended;
	}

	/**
	 * Gets the operations received.
	 *
	 * @return the operations received
	 */
	public List<Operation> getOperationsReceived() {
		return this.operationsReceived;
	}

	/**
	 * Sets the operations received.
	 *
	 * @param operationsReceived the new operations received
	 */
	public void setOperationsReceived(List<Operation> operationsReceived) {
		this.operationsReceived = operationsReceived;
	}

	/**
	 * Adds the operations received.
	 *
	 * @param operationsReceived the operations received
	 * @return the operation
	 */
	public Operation addOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().add(operationsReceived);
		operationsReceived.setUserReceived(this);

		return operationsReceived;
	}

	/**
	 * Removes the operations received.
	 *
	 * @param operationsReceived the operations received
	 * @return the operation
	 */
	public Operation removeOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().remove(operationsReceived);
		operationsReceived.setUserReceived(null);

		return operationsReceived;
	}

	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public List<Post> getPosts() {
		return this.posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param posts the new posts
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/**
	 * Adds the post.
	 *
	 * @param post the post
	 * @return the post
	 */
	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	/**
	 * Removes the post.
	 *
	 * @param post the post
	 * @return the post
	 */
	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Gets the followers.
	 *
	 * @return the followers
	 */
	public List<User> getFollowers() {
		return this.followers;
	}

	/**
	 * Sets the followers.
	 *
	 * @param followers the new followers
	 */
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	/**
	 * Gets the followings.
	 *
	 * @return the followings
	 */
	public List<User> getFollowings() {
		return this.followings;
	}

	/**
	 * Sets the followings.
	 *
	 * @param followings the new followings
	 */
	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}

	/**
	 * Gets the videogames.
	 *
	 * @return the videogames
	 */
	public List<Videogame> getVideogames() {
		return this.videogames;
	}

	/**
	 * Sets the videogames.
	 *
	 * @param videogames the new videogames
	 */
	public void setVideogames(List<Videogame> videogames) {
		this.videogames = videogames;
	}
	
	/**
	 * Adds the videogame.
	 *
	 * @param aVideogame the a videogame
	 */
	public void addVideogame(Videogame aVideogame){
		if(this.getVideogames() == null)
			this.videogames = new ArrayList<Videogame>();
		
		this.videogames.add(aVideogame);
	}
	
	/**
	 * Adds the follower.
	 *
	 * @param anUser the an user
	 */
	public void addFollower(User anUser){
		if(this.getFollowers() == null)
			this.followers = new ArrayList<User>();
		
		this.followers.add(anUser);
	}
	
	/**
	 * Adds the following.
	 *
	 * @param anUser the an user
	 */
	public void addFollowing(User anUser){
		if(this.getFollowings() == null)
			this.followings = new ArrayList<User>();
		
		this.followings.add(anUser);
	}
	
	/**
	 * Removes the videogame.
	 *
	 * @param aVideogame the a videogame
	 */
	public void removeVideogame(Videogame aVideogame){
		if(this.getVideogames() != null)
			this.videogames.remove(aVideogame);			
	}
	
	/**
	 * Removes the follower.
	 *
	 * @param anUser the an user
	 */
	public void removeFollower(User anUser){
		if(this.getFollowers() != null)
			this.followers.remove(anUser);
	}
	
	/**
	 * Removes the following.
	 *
	 * @param anUser the an user
	 */
	public void removeFollowing(User anUser){
		if(this.getFollowings() != null)
			this.followings.remove(anUser);
	}
	
	/**
	 * Gets the basic data.
	 *
	 * @return the basic data
	 */
	public User getBasicData(){
		return 	new User(this.username, this.role, this.name, this.mail, this.address);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", name=" + name + ", password=" + password + ", username=" + username	+ ", address=" + address + ", role=" + role + "]";
	}

}