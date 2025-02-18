package gr.aueb.mvcfilmbro.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import gr.aueb.mvcfilmbro.model.User;

/*implementation of User*/
public class UserMapper implements RowMapper<User> {
    /*Map the user table to class table*/

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("country"),
                rs.getString("profilePic"),
                rs.getInt("profileVisibility"),
                rs.getString("bio")
        );
    }
}
