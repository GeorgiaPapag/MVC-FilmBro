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

    /** authentication method
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
//    /**
//     * Registers a new user into the database.
//     * Checks if the username or email already exist before insertion.
//     * @param username the user's username
//     * @param password the user's password
//     * @param email the user's email
//     * @param accountStatus the user's account status (active/inactive)
//     * @throws Exception if the username/email already exists
//     */
//    public void register(String username, String password, String email, int accountStatus) throws Exception {
//        // Check if the user already exists
//        String checkQuery = "SELECT COUNT(*) FROM appuser WHERE user_name = ? OR email = ?;";
//
//        int userExists = jdbcTemplate.queryForObject(checkQuery, Integer.class, username, email);
//
//        if (userExists > 0) {
//            throw new Exception("Username or email is already used. Choose something different.");
//        }
//
//        // Insert user
//        String insertQuery = "INSERT INTO appuser (user_name, pass_word, email, account_status) VALUES (?, ?, ?, ?);";
//
//        try {
//            jdbcTemplate.update(insertQuery, username, password, email, accountStatus);
//        } catch (DataAccessException e) {
//            throw new Exception("Something went wrong while inserting the user: " + e.getMessage());
//        }
//    }
//
//
    /** update the fields of the user
     * @param user is the user object containing users details
     * @throws Exception if an error occurs while updating
     */

    public void updateUser(User user) throws Exception {

        // update the user details
        String sql = "UPDATE appuser SET user_name = ?, email = ?, country = ?, profile_pic = ?, account_status = ?, bio = ? WHERE userId = ?";

        try {
            jdbcTemplate.update(sql,
                    user.getUserName(),
                    user.getEmail(),
                    user.getCountry(),
                    user.getProfilePic(),
                    user.getAccountStatus(),
                    user.getBio(),
                    user.getUserId()
            );
        } catch (DataAccessException e) {

            // exception that may occur form the database
            throw new Exception("Error while updating:" + e.getMessage());
        }
    }
//
//    /** get the followers of the user
//    * @param id is the user id
//    */
//    public ArrayList<User> getFollowers(int id) throws Exception {
//        ArrayList<User> followers = new ArrayList<>();
//
//        String sql = "SELECT * FROM appuser JOIN follow ON follow.followerId = appuser.userId WHERE follow.followingId = ?";
//
//        try {
//            List<User> result = jdbcTemplate.query(sql, new Object[]{id}, new UserMapper());
//            followers.addAll(result);
//        } catch (DataAccessException e) {
//            throw new Exception("Error fetching followers: " + e.getMessage());
//        }
//
//        return followers;
//    }
//
//    /**  list of users that the specified user is following
//     * @param id the id of the user whose following list is to be retrieved
//     */
//
//    public ArrayList<User> getFollowing(int id) throws Exception {
//        ArrayList<User> following = new ArrayList<>();
//
//        String sql = "SELECT * FROM AppUser JOIN Follow ON Follow.followingId = AppUser.userId WHERE followerId=?";
//
//        try {
//            List<User> result = jdbcTemplate.query(sql, new Object[]{id}, new UserMapper());
//            following.addAll(result);
//        } catch (DataAccessException e) {
//            throw new Exception("Error fetching followers: " + e.getMessage());
//        }
//
//        return following;
//    }
//
//    /** find user by id
//     * @param userId the id of the user
//     */
//
//    public User findUserById(int userId) throws Exception {
//
//        String query = "SELECT * FROM appuser WHERE userId=?";
//
//        try {
//            return jdbcTemplate.queryForObject(
//                    query,
//                    new UserMapper(),
//                    userId
//            );
//        } catch (EmptyResultDataAccessException e) {
//            throw new Exception("No user found");
//        }
//    }
//
//    /** is following checking if a user is following another user
//     * @param user the user who might be following
//     * @param otherUser the user who might be followed
//     */
//
//    public boolean isFollowing(User user, User otherUser) {
//
//        String query = "SELECT 1 FROM Follow WHERE followerId = ? AND followingId = ?";
//
//        try {
//            Integer result = jdbcTemplate.queryForObject(query, Integer.class, user.getId(), otherUser.getId());
//           // return result != null;
//            return true; // If query succeeds, user is following
//        } catch (EmptyResultDataAccessException e) {
//            return false; // // If no match, user is not following
//        }
//    }
}
