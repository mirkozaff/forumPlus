package utility_package;


public class User {
    
    private String username;
    private String password;
    private String imageURL;
    private boolean moderatore;

    
    public User() {}    
    
    public User(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isModeratore() {
        return moderatore;
    }

    public void setModeratore(boolean moderatore) {
        this.moderatore = moderatore;
    }
     
  
}
