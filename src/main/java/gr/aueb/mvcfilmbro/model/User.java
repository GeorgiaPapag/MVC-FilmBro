package gr.aueb.mvcfilmbro.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User {

    private int userId;
    private String user_name;
    private String pass_word;
    private String email;
    private String country;
    private String profile_pic;
    private int account_status;
    private String bio;

    // Explicit constructor
    public User(int userId, String user_name, String pass_word, String email,
                String country, String profile_pic, int account_status, String bio) {
        this.userId = userId;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.email = email;
        this.country = country;
        this.profile_pic = profile_pic;
        this.account_status = account_status;
        this.bio = bio;
    }
}

