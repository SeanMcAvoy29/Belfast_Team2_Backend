package org.kainos.ea.db;

import org.kainos.ea.cli.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JobspecDAO {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public Job getJobspecById(int id) throws SQLException {

        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM JobRole where JobID =" +id);

        while(rs.next()){
            return new Job(
                    rs.getInt("JobID"),
                    rs.getString("JobRole"),
                    rs.getString("Band"),
                    rs.getString("Specifications"),
                    rs.getString("Responsibilities")
            );
        }
        return null;
    }
}
