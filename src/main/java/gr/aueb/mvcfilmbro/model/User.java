package gr.aueb.mvcfilmbro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class User {

    /** params **/
    private final int id;
    @Setter private String username;
    @Setter private String password;
    private String email;
    private String country;
    private String profilePic;
    private int account_status;
    private String bio;
}
