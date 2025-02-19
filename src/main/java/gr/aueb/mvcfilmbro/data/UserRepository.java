package gr.aueb.mvcfilmbro.data;

import gr.aueb.mvcfilmbro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

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

        String query = "SELECT * FROM appuser WHERE " +
                        "user_name=? and pass_word = ?;";

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
     * Registers a new user into the database.
     * Checks if the username or email already exist before insertion.
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email
     * @param accountStatus the user's account status (active/inactive)
     * @throws Exception if the username/email already exists
     */
    public void register(String username, String password, String email, int accountStatus) throws Exception {
        // Check if the user already exists
        String checkQuery = "SELECT COUNT(*) FROM appuser WHERE user_name = ? OR email = ?;";

        int userExists = jdbcTemplate.queryForObject(checkQuery, Integer.class, username, email);

        if (userExists > 0) {
            throw new Exception("Username or email is already used. Choose something different.");
        }

        // Insert user
        String insertQuery = "INSERT INTO appuser (user_name, pass_word, email, account_status) VALUES (?, ?, ?, ?);";

        try {
            jdbcTemplate.update(insertQuery, username, password, email, accountStatus);
        } catch (DataAccessException e) {
            throw new Exception("Something went wrong while inserting the user: " + e.getMessage());
        }
    }
}
