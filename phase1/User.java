package phase1;

import java.util.List;
import java.io.Serializable;

abstract class User implements Serializable{

    private String username;
    private String password;
    private String type;
    private Inbox inbox;

    //Need empty user as placeholder in UserCatalogue findUserByName method
    public User(){

    }

    public User(String username, String password, String type, Inbox inbox){
        this.username = username;
        this.password = password;
        // User type is either "admin" or "trader"
        this.type = type;
        this.inbox = inbox;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    // Create or change username
    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    // Will be used to create or change password?
    public void setPassword(String password) {
        this.password = password;
    }

    public Inbox getInbox() {
        return inbox;
    }
}
