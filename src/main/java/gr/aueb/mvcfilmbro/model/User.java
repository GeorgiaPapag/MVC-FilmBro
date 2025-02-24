package gr.aueb.mvcfilmbro.model;

public class User {

    private int userId;
    private String userName; // Changed to camelCase
    private String passWord;
    private String email;
    private String country;
    private String profilePic;  // Changed to camelCase
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
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;


//@Getter
//@Setter
//@NoArgsConstructor
//public class User {
//
//    private int userId;
//    private String user_name;
//    private String pass_word;
//    private String email;
//    private String country;
//    private String profile_pic;
//    private int account_status;
//    private String bio;
//
//    // Explicit constructor
//    public User(int userId, String user_name, String pass_word, String email,
//                String country, String profile_pic, int account_status, String bio) {
//        this.userId = userId;
//        this.user_name = user_name;
//        this.pass_word = pass_word;
//        this.email = email;
//        this.country = country;
//        this.profile_pic = profile_pic;
//        this.account_status = account_status;
//        this.bio = bio;
//    }
//}