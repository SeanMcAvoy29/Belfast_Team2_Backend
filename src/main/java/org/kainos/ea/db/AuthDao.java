package org.kainos.ea.db;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Role;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean validLogin(Login login) {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();

            String sql = "SELECT Password FROM `User` WHERE Username = ?;";

            PreparedStatement preparedStatement = c.prepareStatement(sql);

            preparedStatement.setString(1, login.getEmail());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return rs.getString("Password").equals(login.getPassword());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public String generateToken(String email) throws SQLException {
        String token = generateJwtToken(email);

        return token;
    }

    public String generateJwtToken(String email) throws SQLException {
        String token = UUID.randomUUID().toString();
        Date expiry = DateUtils.addHours(new Date(), 8);

        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Token(Username, Token, Expiry) Values (?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1, email);
        st.setString(2, token);
        st.setTimestamp(3, new Timestamp(expiry.getTime()));

        st.executeUpdate();
        return token;
    }

    public void register(String email, String password, Role roleID) throws SQLException {
        Connection c = databaseConnector.getConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO `User` (Username, Password, RoleID) VALUES (?, ?, ?);");

        ps.setString(1, email);
        ps.setString(2, password);
        ps.setInt(3, roleID.getRole());

        ps.executeUpdate();
    }

    public String getHashedPassword(String email) {
        try (Connection c = databaseConnector.getConnection()) {
            String sql = "SELECT Password FROM `User` WHERE Username = ?;";

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

    public void setTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

    }
}

