package gr.aueb.mvcfilmbro.data;

import gr.aueb.mvcfilmbro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*authentication method
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
}
