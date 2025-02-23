//package gr.aueb.mvcfilmbro.data;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import lombok.NonNull;
//import org.springframework.jdbc.core.RowMapper;
//
//import gr.aueb.mvcfilmbro.model.User;
//
///*implementation of User*/
//public class UserMapper implements RowMapper<User> {
//    /*Map the user table to class table*/
//
//    @Override
//    public @NonNull User mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//        return new User(
//                rs.getInt("userId"),
//                rs.getString("user_name"),
//                rs.getString("pass_word"),
//                rs.getString("email"),
//                rs.getString("country"),
//                rs.getString("profile_pic"),
//                rs.getInt("account_status"),
//                rs.getString("bio")
//        );
//    }
//}
