package org.kainos.ea.db;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.kainos.ea.cli.Role;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;


public class AuthDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public void register(String email, String password, Role roleID) throws SQLException {
        Connection c = databaseConnector.getConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO `User` (Email, Password, RoleID) VALUES (?, ?, ?);");

        ps.setString(1, email);
        ps.setString(2, password);
        ps.setInt(3, roleID.getRole());

        ps.executeUpdate();
    }

    public String getHashedPassword(String email) {
        try (Connection c = databaseConnector.getConnection()) {
            String sql = "SELECT Password FROM `User` WHERE Email = ?;";

            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("Password");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public String generateJwtToken(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject(email).signWith(key).compact();
        return jws;
    }


}
