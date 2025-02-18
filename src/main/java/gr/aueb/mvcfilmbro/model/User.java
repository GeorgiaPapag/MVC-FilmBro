package gr.aueb.mvcfilmbro.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String country;
    private String profilePic;
    private int profileVisibility;
    private String bio;
}
