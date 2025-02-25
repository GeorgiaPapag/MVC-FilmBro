package gr.aueb.mvcfilmbro.model;

public class User {

    private int userId;
    private String userName;
    private String passWord;
    private String email;
    private String country;
    private String profilePic;
    private int accountStatus;
    private String bio;

    // Default constructor
    public User() {
    }

    // Explicit constructor
    public User(int userId, String userName, String passWord, String email,
                String country, String profilePic, int accountStatus, String bio) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.country = country;
        this.profilePic = profilePic;
        this.accountStatus = accountStatus;
        this.bio = bio;
    }

    // Getters and Setters for each field

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() { // Changed to camelCase
        return userName;
    }

    public void setUserName(String userName) { // Changed to camelCase
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePic() {  // Changed to camelCase
        return profilePic;
    }

    public void setProfilePic(String profilePic) {  // Changed to camelCase
        this.profilePic = profilePic;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
