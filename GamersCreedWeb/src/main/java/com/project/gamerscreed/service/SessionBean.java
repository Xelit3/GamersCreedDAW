package com.project.gamerscreed.service;
import java.io.*;

import com.project.gamerscreed.model.dto.User;


/**
 * The Class SessionBean.
 *
 * @author Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class SessionBean implements Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user. */
	private User user;

    /**
     * Instantiates a new session bean.
     */
    public SessionBean(){
    
    }
    
    /**
     * Instantiates a new session bean.
     *
     * @param user the user
     */
    public SessionBean(User user){
        setUser(user);

    }
    
    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
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
