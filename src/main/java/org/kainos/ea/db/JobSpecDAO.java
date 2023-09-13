package org.kainos.ea.db;

import org.kainos.ea.cli.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobSpecDAO {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public Job getJobspecById(int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT JobID, JobRoleName, Band, Specification, Responsibilities FROM JobRole where JobID = ?";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            return new Job(
                    rs.getInt("JobID"),
                    rs.getString("JobRoleName"),
                    rs.getString("Band"),
                    rs.getString("Specification"),
                    rs.getString("Responsibilities")
            );
        }

        return null;
    }
}
