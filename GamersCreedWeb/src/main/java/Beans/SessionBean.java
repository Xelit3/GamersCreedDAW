package Beans;
import java.io.*;

import com.project.gamerscreed.model.dto.User;


/**
 *
 * @author adria
 */
public class SessionBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

    public SessionBean(){
    
    }
    public SessionBean(User user){
        setUser(user);

    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }   

}
