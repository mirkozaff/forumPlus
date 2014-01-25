package utility_package;


public class User {
    
    private static String username;
    private static String password;
    private static String imageURL;

    
    public User() {}    
    
    public User(String name) {
        this.username = name;
    }
    
    /**
     * @return the name
     */
    public static String getName() {
        return username;
    }

    /**
     * @param aName the name to set
     */
    public static void setName(String aName) {
        username = aName;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the imageURL
     */
    public static String getImageURL() {
        return imageURL;
    }

    /**
     * @param aImageURL the imageURL to set
     */
    public static void setImageURL(String aImageURL) {
        imageURL = aImageURL;
    }
}
