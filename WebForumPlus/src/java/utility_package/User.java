package utility_package;

import java.sql.Timestamp;


public class User {
    
    private String username;
    private String password;
    private String imageURL;
    private boolean moderatore;
    private Timestamp ultimo_accesso;
    private int id;
    private String email;

    
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

    public Timestamp getUltimo_accesso() {
        return ultimo_accesso;
    }

    public void setUltimo_accesso(Timestamp ultimo_accesso) {
        this.ultimo_accesso = ultimo_accesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
  
}
