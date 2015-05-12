package Beans;
import java.io.*;

import modeltmp.User;
/**
 *
 * @author adria
 */
public class SessionBean implements Serializable{

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
