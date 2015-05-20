package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),	
	@NamedQuery(name="User.loginUser", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String mail;

	private String name;
	
	private String password;

	private String username;

	//bi-directional many-to-one association to ForumMessage
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST) 
	private List<ForumMessage> forumMessages;

	//bi-directional many-to-one association to ForumThread
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	private List<ForumThread> forumThreads;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="userSender", cascade=CascadeType.PERSIST)
	private List<Operation> operationsSended;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="userReceived", cascade=CascadeType.PERSIST)
	private List<Operation> operationsReceived;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	private List<Post> posts;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_address")
	private Address address;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_role")
	private Role role;

	//uni-directional many-to-many association to User
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

	public User() {	}
	
	public User(Role aRole){
		this.role = aRole;
	}

	public User(String aUsername, String aPassword) {
		super();
		this.username = aUsername;
		this.password = aPassword;		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ForumMessage> getForumMessages() {
		return this.forumMessages;
	}

	public void setForumMessages(List<ForumMessage> forumMessages) {
		this.forumMessages = forumMessages;
	}

	public ForumMessage addForumMessage(ForumMessage forumMessage) {
		getForumMessages().add(forumMessage);
		forumMessage.setUser(this);

		return forumMessage;
	}

	public ForumMessage removeForumMessage(ForumMessage forumMessage) {
		getForumMessages().remove(forumMessage);
		forumMessage.setUser(null);

		return forumMessage;
	}

	public List<ForumThread> getForumThreads() {
		return this.forumThreads;
	}

	public void setForumThreads(List<ForumThread> forumThreads) {
		this.forumThreads = forumThreads;
	}

	public ForumThread addForumThread(ForumThread forumThread) {
		getForumThreads().add(forumThread);
		forumThread.setUser(this);

		return forumThread;
	}

	public ForumThread removeForumThread(ForumThread forumThread) {
		getForumThreads().remove(forumThread);
		forumThread.setUser(null);

		return forumThread;
	}

	public List<Operation> getOperationsSended() {
		return this.operationsSended;
	}

	public void setOperationsSended(List<Operation> operationsSended) {
		this.operationsSended = operationsSended;
	}

	public Operation addOperationsSended(Operation operationsSended) {
		getOperationsSended().add(operationsSended);
		operationsSended.setUserSender(this);

		return operationsSended;
	}

	public Operation removeOperationsSended(Operation operationsSended) {
		getOperationsSended().remove(operationsSended);
		operationsSended.setUserSender(null);

		return operationsSended;
	}

	public List<Operation> getOperationsReceived() {
		return this.operationsReceived;
	}

	public void setOperationsReceived(List<Operation> operationsReceived) {
		this.operationsReceived = operationsReceived;
	}

	public Operation addOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().add(operationsReceived);
		operationsReceived.setUserReceived(this);

		return operationsReceived;
	}

	public Operation removeOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().remove(operationsReceived);
		operationsReceived.setUserReceived(null);

		return operationsReceived;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowings() {
		return this.followings;
	}

	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}

	public List<Videogame> getVideogames() {
		return this.videogames;
	}

	public void setVideogames(List<Videogame> videogames) {
		this.videogames = videogames;
	}
	
	public void addVideogame(Videogame aVideogame){
		if(this.getVideogames() == null)
			this.videogames = new ArrayList<Videogame>();
		
		this.videogames.add(aVideogame);
	}
	
	public void addFollower(User anUser){
		if(this.getFollowers() == null)
			this.followers = new ArrayList<User>();
		
		this.followers.add(anUser);
	}
	
	public void addFollowing(User anUser){
		if(this.getFollowings() == null)
			this.followings = new ArrayList<User>();
		
		this.followings.add(anUser);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", name=" + name + ", password=" + password + ", username=" + username	+ ", address=" + address + ", role=" + role + "]";
	}

}