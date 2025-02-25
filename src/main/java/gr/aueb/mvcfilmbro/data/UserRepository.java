package gr.aueb.mvcfilmbro.data;

import gr.aueb.mvcfilmbro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * authentication method
     *
     * @param username should match with database username
     * @param password should match with database password
     */

    public User authentication(String username, String password) {

        String query = "SELECT * FROM appuser WHERE user_name=? and pass_word = ?;";

        try {
            User user = jdbcTemplate.queryForObject(
                    query,
                    new UserMapper(),
                    username,
                    password
            );
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Wrong username or password");
        }
    }

    /**
     * update the fields of the user
     *
     * @param user is the user object containing users details
     * @throws Exception if an error occurs while updating
     */

    public void updateUser(User user) throws Exception {
//        Debugging
//        System.out.println("Executing update query for user: " + user);
//        System.out.println("User Data - Username: " + user.getUserName());
//        System.out.println("Email: " + user.getEmail());
//        System.out.println("Country: " + user.getCountry());
//        System.out.println("Profile Pic: " + user.getProfilePic());
//        System.out.println("Account Status: " + user.getAccountStatus());
//        System.out.println("Bio: " + user.getBio());
//        System.out.println("User ID: " + user.getUserId());

        String sql = "UPDATE appuser SET user_name = ?, email = ?, country = ?, profile_pic = ?, account_status = ?, bio = ? WHERE userId = ?";

        try {
            int rowsUpdated = jdbcTemplate.update(sql,
                    user.getUserName(),
                    user.getEmail(),
                    user.getCountry(),
                    user.getProfilePic(),
                    user.getAccountStatus(),
                    user.getBio(),
                    user.getUserId()
            );
            System.out.println("Rows updated: " + rowsUpdated);
            if (rowsUpdated == 0) {
                throw new Exception("No rows updated. User ID may not exist.");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error while updating: " + e.getMessage());
        }
    }

}

